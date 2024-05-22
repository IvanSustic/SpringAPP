package hr.tvz.sustic.rentacarapp.repository;

import hr.tvz.sustic.rentacarapp.model.Vozilo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
@Component

public class DevVoziloRepository implements VoziloRepository{

    private static final List<Vozilo> vozila = new ArrayList<>();

    static {
        Vozilo prvoVozilo =
                new Vozilo(3,5,"Automatik",
                        true,4,"Dizel",
                        new Date(2023, Calendar.NOVEMBER,10),new Date(2025, Calendar.NOVEMBER,10),
                        2000,"ZG123-WK","41234123412341243");
        Vozilo drugoVozilo =
                new Vozilo(4,6,"6 brzina",
                        true,4,"Benzin",
                        new Date(2023, Calendar.JUNE,20),new Date(2024, Calendar.DECEMBER,23),
                        10000,"ZG125-ZK","52352351234123413");
        vozila.add(prvoVozilo);
        vozila.add(drugoVozilo);
    }
    @Override
    public List<Vozilo> findAll() {

        return vozila;
    }

    @Override
    public List<Vozilo> findFiltered(String f, String p) {
        return null;
    }

    @Override
    public Optional<Vozilo> saveVozilo(Vozilo vozilo) {
        if(vozila.stream().anyMatch(vozilo1 -> Objects.equals(vozilo1.getRegistration(), vozilo.getRegistration()) ||
                Objects.equals(vozilo1.getChassisNumber(), vozilo.getChassisNumber()))){

            return Optional.empty();
        } else {

            vozila.add(vozilo);
            return Optional.of(vozilo);
        }
    }

    @Override
    public Optional<Vozilo> changeVozilo(Vozilo vozilo) {

            vozila.forEach(vozilo1 -> {
                if (vozilo1.getRegistration().equals(vozilo.getRegistration())){
                    vozilo1.setFuel(vozilo.getFuel());
                    vozilo1.setDoors(vozilo.getDoors());
                    vozilo1.setGearBox(vozilo.getGearBox());
                    vozilo1.setMileage(vozilo.getMileage());
                    vozilo1.setAirConditioning(vozilo.getAirConditioning());
                    vozilo1.setRegistration(vozilo.getRegistration());
                    vozilo1.setChassisNumber(vozilo.getChassisNumber());
                    vozilo1.setMaxPassengers(vozilo.getMaxPassengers());
                    System.out.println(vozilo1);
                }
            });
        return Optional.of(vozilo);

    }

    @Override
    public void deleteVozilo(String registration) {
        for (int i=0; i<=vozila.size(); i++){
            if (vozila.get(i).getRegistration().equals(registration)){
                vozila.remove(i);
            }
        }
    }

    @Override
    public Optional<Vozilo> findVoziloByCode(Integer code) {
        return vozila.stream().filter(fi -> fi.getCode().equals(code)).findAny();
    }

    @Override
    public Optional<Vozilo> findVoziloByRegistration(String registration) {
        return vozila.stream().filter(fi -> fi.getRegistration().equals(registration)).findAny();
    }

    @Override
    public Optional<Vozilo> findVoziloByChassis(String chassis) {
        return vozila.stream().filter(fi -> fi.getChassisNumber().equals(chassis)).findAny();
    }


}
