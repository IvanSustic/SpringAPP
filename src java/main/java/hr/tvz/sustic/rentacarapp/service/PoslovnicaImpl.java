package hr.tvz.sustic.rentacarapp.service;

import hr.tvz.sustic.rentacarapp.command.PoslovnicaCommand;
import hr.tvz.sustic.rentacarapp.command.VoziloCommand;
import hr.tvz.sustic.rentacarapp.dto.PoslovnicaDTO;
import hr.tvz.sustic.rentacarapp.dto.VoziloDTO;
import hr.tvz.sustic.rentacarapp.model.Poslovnica;
import hr.tvz.sustic.rentacarapp.model.Vozilo;
import hr.tvz.sustic.rentacarapp.repository.PoslovnicaRepository;
import hr.tvz.sustic.rentacarapp.repository.VoziloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class PoslovnicaImpl  implements  PoslovniceService {
    private PoslovnicaRepository poslovnicaRepository;
    @Override
    public List<PoslovnicaDTO> findAll() {
        return poslovnicaRepository.findAll().stream().map(this::convertPoslovnicaToPoslovnicaDTO).toList();
    }

    @Override
    public Optional<PoslovnicaDTO> savePoslovnica(PoslovnicaCommand poslovnica) {
        Optional<Poslovnica> poslovnicaOptional = poslovnicaRepository.savePoslovnica(convertPoslovnicaCommandToPoslovnica(poslovnica));
        return poslovnicaOptional.map(this::convertPoslovnicaToPoslovnicaDTO);
    }

    @Override
    public Optional<PoslovnicaDTO> findPoslovnicaByCode(Integer code) {
        Optional<Poslovnica> poslovnica = poslovnicaRepository.findPoslovnicaByCode(code);
        if (poslovnica.isPresent()){
            return Optional.of(convertPoslovnicaToPoslovnicaDTO(poslovnicaRepository.findPoslovnicaByCode(code).get()));
        } else return Optional.empty();
    }

    @Override
    public Optional<PoslovnicaDTO> findPoslovnicaByAdress(String adress) {
        Optional<Poslovnica> poslovnica = poslovnicaRepository.findPoslovnicaByAdress(adress);
        if (poslovnica.isPresent()){
            return Optional.of(convertPoslovnicaToPoslovnicaDTO(poslovnicaRepository.findPoslovnicaByAdress(adress).get()));
        } else return Optional.empty();
    }

    @Override
    public void deletPoslovnica(Integer code) {
        poslovnicaRepository.deletPoslovnica(code);
    }

    private PoslovnicaDTO convertPoslovnicaToPoslovnicaDTO(Poslovnica poslovnica){
        return  new PoslovnicaDTO(poslovnica.getName(),poslovnica.getVozila(),poslovnica.getAdresa() );
    }

    private Poslovnica convertPoslovnicaCommandToPoslovnica(PoslovnicaCommand poslovnicaCommand){
        return  new Poslovnica(poslovnicaCommand.getCode(),poslovnicaCommand.getName(),
                poslovnicaCommand.getVozila(),poslovnicaCommand.getAdresa(),poslovnicaCommand.getOwner());
    }

}
