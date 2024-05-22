package hr.tvz.sustic.rentacarapp.dto;

import hr.tvz.sustic.rentacarapp.model.Vozilo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PoslovnicaDTO {

    private String name;
    private List<Vozilo> vozila;
    private String adresa;

}
