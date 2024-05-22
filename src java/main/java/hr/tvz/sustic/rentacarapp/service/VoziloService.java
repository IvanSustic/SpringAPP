package hr.tvz.sustic.rentacarapp.service;



import hr.tvz.sustic.rentacarapp.command.VoziloCommand;
import hr.tvz.sustic.rentacarapp.dto.VoziloDTO;
import hr.tvz.sustic.rentacarapp.model.Vozilo;


import java.util.List;
import java.util.Optional;

public interface VoziloService {
    List<VoziloDTO> findAll();
    List<VoziloDTO> findOld();
    Optional<VoziloDTO> saveVozilo(VoziloCommand voziloCommand);
    VoziloDTO findVoziloByCode(Integer code);
    List<VoziloDTO> findFiltered(String f,String p);
    void deleteVozilo(String registration);

    Optional<VoziloDTO> findVoziloByRegistration(String registration);
    Optional<VoziloDTO> findVoziloByChassis(String chassis);

     Optional<VoziloDTO> changeVozilo(VoziloCommand vozilo);
}
