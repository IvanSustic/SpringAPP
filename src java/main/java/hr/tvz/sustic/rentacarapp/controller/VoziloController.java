package hr.tvz.sustic.rentacarapp.controller;

import hr.tvz.sustic.rentacarapp.command.VoziloCommand;
import hr.tvz.sustic.rentacarapp.dto.VoziloDTO;
import hr.tvz.sustic.rentacarapp.service.VoziloService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/voziloStore")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class VoziloController {
    private VoziloService voziloService;

    @GetMapping("/all")
    public List<VoziloDTO> getAllVozila() {

        log.info("Get all vozila called");

        return voziloService.findAll();
    }



    @GetMapping("/old")
    public List<VoziloDTO> getOldVozila() {
        log.info("Get old vozila called");
        List<VoziloDTO> list =  voziloService.findOld();
        System.out.println(list);
        return list;
    }

    @GetMapping("/vozilo/{code}")
    public VoziloDTO getVoziloByCode(@PathVariable Integer code){
        log.info("Get vozilo by code called");
        return voziloService.findVoziloByCode(code);
    }

    @GetMapping("/filtered")
    public List<VoziloDTO>  getFiltered(@RequestParam String f,String p){
        log.info("Get vozilo filtered");

        return voziloService.findFiltered(f,p);
    }

    @GetMapping("/vozilo/registracija/{registracija}")
    public ResponseEntity<VoziloDTO> getVoziloByRegistration(@PathVariable String registracija){
        log.info("Get vozilo by registration called");
        Optional<VoziloDTO>  vozilo= voziloService.findVoziloByRegistration(registracija);
        return vozilo.map(voziloDTO -> ResponseEntity.status(HttpStatus.ACCEPTED).body(voziloDTO)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @GetMapping("/vozilo/sasija/{chassis}")
    public ResponseEntity<VoziloDTO> getVoziloByChassis(@PathVariable String chassis){
        log.info("Get vozilo by chassis called");
        Optional<VoziloDTO>  vozilo=  voziloService.findVoziloByChassis(chassis);

        return vozilo.map(voziloDTO -> ResponseEntity.status(HttpStatus.ACCEPTED).body(voziloDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("vozilo/{registracija}")
    public void delete(@PathVariable String registracija){
        voziloService.deleteVozilo(registracija);
    }

    @PostMapping("/vozilo")
    public ResponseEntity<VoziloDTO> saveVozilo(@Valid @RequestBody final VoziloCommand command){

        Optional<VoziloDTO> voziloDTO = voziloService.saveVozilo(command);

        return voziloDTO.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PutMapping("/voziloChange")
    public ResponseEntity<VoziloDTO> changeVozilo(@Valid @RequestBody final VoziloCommand command){

        Optional<VoziloDTO> voziloDTO = voziloService.changeVozilo(command);

        return voziloDTO.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
