package hr.tvz.sustic.rentacarapp.repository;

import hr.tvz.sustic.rentacarapp.model.Poslovnica;
import hr.tvz.sustic.rentacarapp.model.Vozilo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface PoslovnicaRepository {
    List<Poslovnica> findAll();
    Optional<Poslovnica> savePoslovnica(Poslovnica poslovnica);
    Optional<Poslovnica> findPoslovnicaByCode(Integer code);
    Optional<Poslovnica> findPoslovnicaByAdress(String adress);
    void deletPoslovnica(Integer code);
}
