package hr.tvz.sustic.rentacarapp.repository;

import hr.tvz.sustic.rentacarapp.model.Vozilo;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Primary
@Repository
public interface VoziloRepositoryJPA  extends JpaRepository<Vozilo,Integer> {

    List<Vozilo> findAllByFuelContainsAndRegistrationContains(String fuel,String registration);

    Optional<Vozilo> findByCode(Integer code);

    List<Vozilo> getAllByMileageLessThan(Integer mileage);



    Optional<Vozilo> findVoziloByRegistration(String registration);
    Optional<Vozilo> findByChassisNumber(String chassis);

    void deleteByRegistration(String registration);
}
