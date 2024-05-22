package hr.tvz.sustic.rentacarapp.repository;


import hr.tvz.sustic.rentacarapp.model.Vozilo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component

public interface VoziloRepository {
    List<Vozilo> findAll();

    List<Vozilo> findFiltered(String f,String p);

    Optional<Vozilo> saveVozilo(Vozilo foodItem);

    Optional<Vozilo> changeVozilo(Vozilo vozilo);
    Optional<Vozilo> findVoziloByCode(Integer code);

    Optional<Vozilo> findVoziloByRegistration(String registration);
    Optional<Vozilo> findVoziloByChassis(String chassis);

    void deleteVozilo(String registration);


}
