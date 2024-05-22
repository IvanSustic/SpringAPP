package hr.tvz.sustic.rentacarapp.service;

import hr.tvz.sustic.rentacarapp.command.PoslovnicaCommand;
import hr.tvz.sustic.rentacarapp.dto.PoslovnicaDTO;


import java.util.List;
import java.util.Optional;

public interface PoslovniceService {
    List<PoslovnicaDTO> findAll();
    Optional<PoslovnicaDTO> savePoslovnica(PoslovnicaCommand poslovnica);
    Optional<PoslovnicaDTO> findPoslovnicaByCode(Integer code);
    Optional<PoslovnicaDTO> findPoslovnicaByAdress(String adress);
    void deletPoslovnica(Integer code);
}
