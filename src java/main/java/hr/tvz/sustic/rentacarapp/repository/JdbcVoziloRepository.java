package hr.tvz.sustic.rentacarapp.repository;

import hr.tvz.sustic.rentacarapp.model.Vozilo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
@Component
@AllArgsConstructor

public class JdbcVoziloRepository implements  VoziloRepository{

    private static final String FETCH_ALL ="Select * from vozila";
    private static final String FETCH_BY_ID ="Select * from vozila where id = ?";
    private static final String FETCH_BY_REGISTRATION ="Select * from vozila where registration = ?";
    private static final String FETCH_BY_CHASSIS ="Select * from vozila where CHASSIS = ?";
    private static final String DELETE_BY_REGISTRATION ="DELETE from vozila where registration = ?";
    private static final String FIND_FILTERED ="Select * from vozila where registration ILIKE ? AND fuel LIKE ?";
    private static final String UPDATE_VOZILO_BY_REGISTRATION ="update vozila set maxPassengers = ?,fuel=?,mileage=?," +
            "registration=?,doors=?,ChassisNumber=?,gearbox=?,AirConditioning=? where registration = ?;";
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Vozilo> findAll() {
        return jdbcTemplate.query(FETCH_ALL, new VozilaMapper());
    }

    @Override
    public List<Vozilo> findFiltered(String f, String p) {
        f = "%"+f+"%";
        p = "%"+p+"%";
        System.out.println(f);
        System.out.println(p);
        System.out.println(jdbcTemplate.query(FIND_FILTERED, new VozilaMapper(),p,f));
        return jdbcTemplate.query(FIND_FILTERED, new VozilaMapper(),p,f) ;
    }

    @Override
    public Optional<Vozilo> saveVozilo(Vozilo foodItem) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("vozila").usingGeneratedKeyColumns("ID");;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("maxPassengers", foodItem.getMaxPassengers());
        parameters.put("fuel", foodItem.getFuel());
        parameters.put("mileage", foodItem.getMileage());
        parameters.put("registration", foodItem.getRegistration());
        parameters.put("doors", foodItem.getDoors());
        parameters.put("ChassisNumber", foodItem.getChassisNumber());
        parameters.put("gearbox", foodItem.getGearBox());
        parameters.put("AirConditioning", foodItem.getAirConditioning());
        parameters.put("LastInspection", foodItem.getLastInspection());
        parameters.put("NextInspection", foodItem.getNextInspection());


        if(simpleJdbcInsert.executeAndReturnKey(parameters).equals(0)){
            return Optional.empty();
        } else {
            return Optional.of(foodItem);
        }
    }

    @Override
    public Optional<Vozilo> changeVozilo(Vozilo vozilo) {
       if (jdbcTemplate.update(UPDATE_VOZILO_BY_REGISTRATION,vozilo.getMaxPassengers(),vozilo.getFuel(),
                vozilo.getMileage(),vozilo.getRegistration(),vozilo.getDoors(),vozilo.getChassisNumber(),vozilo.getGearBox(),
                vozilo.getAirConditioning(),vozilo.getRegistration())==0){
           return Optional.empty();
       }else {
           return Optional.of(vozilo);
       }


    }

    @Override
    public Optional<Vozilo> findVoziloByCode(Integer code) {
        return Optional.of(jdbcTemplate.queryForObject(FETCH_BY_ID, new VozilaMapper(),code)) ;
    }

    @Override
    public Optional<Vozilo> findVoziloByRegistration(String registration) {
        return Optional.of(jdbcTemplate.queryForObject(FETCH_BY_REGISTRATION, new VozilaMapper(),registration)) ;
    }

    @Override
    public Optional<Vozilo> findVoziloByChassis(String chassis) {
        return Optional.of(jdbcTemplate.queryForObject(FETCH_BY_CHASSIS, new VozilaMapper(),chassis)) ;

    }

    @Override
    public void deleteVozilo(String registration) {
        jdbcTemplate.update(DELETE_BY_REGISTRATION,registration);
    }

    private class VozilaMapper implements RowMapper<Vozilo>{
        public Vozilo mapRow(ResultSet resultSet, int i) throws SQLException{
            Vozilo vozilo = new Vozilo();
            vozilo.setCode(resultSet.getInt("ID"));
            vozilo.setMaxPassengers(resultSet.getInt("maxPassengers"));
            vozilo.setFuel(resultSet.getString("fuel"));
            vozilo.setMileage(resultSet.getInt("mileage"));
            vozilo.setRegistration(resultSet.getString("registration"));
            vozilo.setDoors(resultSet.getInt("doors"));
            vozilo.setChassisNumber(resultSet.getString("ChassisNumber"));
            vozilo.setGearBox(resultSet.getString("gearbox"));
            vozilo.setAirConditioning(resultSet.getBoolean("AirConditioning"));
            vozilo.setLastInspection(resultSet.getDate("LastInspection"));
            vozilo.setNextInspection(resultSet.getDate("NextInspection"));
           return vozilo;
        }
    }
}
