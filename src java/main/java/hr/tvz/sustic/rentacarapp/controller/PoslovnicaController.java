package hr.tvz.sustic.rentacarapp.controller;

import hr.tvz.sustic.rentacarapp.command.PoslovnicaCommand;
import hr.tvz.sustic.rentacarapp.command.VoziloCommand;
import hr.tvz.sustic.rentacarapp.dto.PoslovnicaDTO;
import hr.tvz.sustic.rentacarapp.dto.VoziloDTO;
import hr.tvz.sustic.rentacarapp.service.PoslovniceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/poslovnice")
@AllArgsConstructor
@Slf4j
public class PoslovnicaController {
    private PoslovniceService poslovniceService;

    @GetMapping("/all")
    public List<PoslovnicaDTO> getAllPoslovnice() {

        log.info("Get all poslovnice called");
        return poslovniceService.findAll();
    }





    @GetMapping("/poslovnica/{code}")
    public ResponseEntity<PoslovnicaDTO> getVoziloByCode(@PathVariable Integer code){
        log.info("Get poslovnice by code called");
        Optional<PoslovnicaDTO> poslovnica= poslovniceService.findPoslovnicaByCode(code);
        return poslovnica.map(poslovnicaDTO -> ResponseEntity.status(HttpStatus.FOUND).body(poslovnicaDTO)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

        @GetMapping("/poslovnica/adress/{adress}")
    public ResponseEntity<PoslovnicaDTO> getPoslovniceByAdress(@PathVariable String adress){
        log.info("Get poslovnice by adress called");
        Optional<PoslovnicaDTO> poslovnica= poslovniceService.findPoslovnicaByAdress(adress);
        return poslovnica.map(poslovnicaDTO -> ResponseEntity.status(HttpStatus.FOUND).body(poslovnicaDTO)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("poslovnica/{code}")
    public void delete(@PathVariable Integer code){
        poslovniceService.deletPoslovnica(code);
    }

    @PostMapping("/poslovnica")
    public ResponseEntity<PoslovnicaDTO> savePoslovnica(@Valid @RequestBody final PoslovnicaCommand command){
        Optional<PoslovnicaDTO> poslovnicaDTO = poslovniceService.savePoslovnica(command);

        return poslovnicaDTO.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
