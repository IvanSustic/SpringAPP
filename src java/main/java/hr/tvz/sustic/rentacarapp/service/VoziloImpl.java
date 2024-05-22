package hr.tvz.sustic.rentacarapp.service;


import hr.tvz.sustic.rentacarapp.command.VoziloCommand;
import hr.tvz.sustic.rentacarapp.dto.VoziloDTO;
import hr.tvz.sustic.rentacarapp.model.Vozilo;
import hr.tvz.sustic.rentacarapp.repository.JdbcVoziloRepository;
import hr.tvz.sustic.rentacarapp.repository.VoziloRepository;
import hr.tvz.sustic.rentacarapp.repository.VoziloRepositoryJPA;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoziloImpl implements VoziloService{

    private VoziloRepositoryJPA voziloRepositoryJPA;
    @Override
    public List<VoziloDTO> findAll() {

        return voziloRepositoryJPA.findAll().stream().map(this::convertVoziloToVoziloDTO).toList();
    }

    @Override
    public List<VoziloDTO> findOld() {
        return voziloRepositoryJPA.findAll().stream().map(this::convertVoziloToVoziloDTO).filter(voziloDTO -> !voziloDTO.getNewForSale()).toList();
    }

    @Override
    public Optional<VoziloDTO> saveVozilo(VoziloCommand voziloCommand) {
        Optional<Vozilo> vozilo = Optional.of(voziloRepositoryJPA.save(convertVoziloCommandToVozilo(voziloCommand)));
        return vozilo.map(this::convertVoziloToVoziloDTO);
    }

    @Override
    public VoziloDTO findVoziloByCode(Integer code) {
        Optional<Vozilo> vozilo = voziloRepositoryJPA.findByCode(code);
        if (vozilo.isPresent()){
            return convertVoziloToVoziloDTO(voziloRepositoryJPA.findByCode(code).get());
        } else return new VoziloDTO();

    }

    @Override
    public List<VoziloDTO> findFiltered(String f, String p) {
        return voziloRepositoryJPA.findAllByFuelContainsAndRegistrationContains(f,p)
                .stream().map(this::convertVoziloToVoziloDTO).toList();
    }

    @Override
    @Transactional
    public void deleteVozilo(String registration) {
        voziloRepositoryJPA.deleteByRegistration(registration);
    }

    @Override
    public Optional<VoziloDTO> findVoziloByRegistration(String registration) {
        Optional<Vozilo> vozilo = voziloRepositoryJPA.findVoziloByRegistration(registration);
        if (vozilo.isPresent()){
            return Optional.of(convertVoziloToVoziloDTO(voziloRepositoryJPA.findVoziloByRegistration(registration).get()) );
        } else return Optional.empty();
    }

    @Override
    public Optional<VoziloDTO> findVoziloByChassis(String chassis) {
        Optional<Vozilo> vozilo = voziloRepositoryJPA.findByChassisNumber(chassis);
        if (vozilo.isPresent()){
            return Optional.of(convertVoziloToVoziloDTO(voziloRepositoryJPA.findByChassisNumber(chassis).get()) );
        } else return Optional.empty();
    }

    @Override
    public Optional<VoziloDTO> changeVozilo(VoziloCommand vozilo) {

        Optional<Vozilo> vozilo2 = voziloRepositoryJPA.findVoziloByRegistration(vozilo.getRegistration());
        Vozilo vozilo3 = vozilo2.get();
        if (vozilo2.isPresent()){

            vozilo3.setMaxPassengers(vozilo.getMaxPassengers());
            vozilo3.setDoors(vozilo.getDoors());
            vozilo3.setGearBox(vozilo.getGearBox());
            vozilo3.setFuel(vozilo.getFuel());
            vozilo3.setMileage(vozilo.getMileage());
            vozilo3.setRegistration(vozilo.getRegistration());
            vozilo3.setAirConditioning(vozilo.getAirConditioning());
            vozilo3.setChassisNumber(vozilo.getChassisNumber());
        }


        Optional<Vozilo> vozilo1 = Optional.of(voziloRepositoryJPA.save(vozilo3));

        return vozilo1.map(this::convertVoziloToVoziloDTO);
    }


    private VoziloDTO convertVoziloToVoziloDTO(Vozilo vozilo){
        return  new VoziloDTO(vozilo.getMaxPassengers(),vozilo.getGearBox(),vozilo.getAirConditioning(),vozilo.getDoors(),vozilo.getFuel(),vozilo.getMileage(),vozilo.getMileage()<5000,vozilo.getRegistration(),vozilo.getChassisNumber() );
    }

    private Vozilo convertVoziloCommandToVozilo(VoziloCommand vozilo){
        return  new Vozilo(vozilo.getCode(),vozilo.getMaxPassengers(),
                vozilo.getGearBox(),vozilo.getAirConditioning(),vozilo.getDoors(),
                vozilo.getFuel(),vozilo.getLastInspection(),vozilo
                .getNextInspection(),vozilo.getMileage(),vozilo.getRegistration(),
                vozilo.getChassisNumber());
    }

}
