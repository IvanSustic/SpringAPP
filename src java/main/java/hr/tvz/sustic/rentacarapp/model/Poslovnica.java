package hr.tvz.sustic.rentacarapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poslovnica {
    private Integer code;
    private String name;
    private List<Vozilo> vozila;
    private String adresa;
    private String owner;
}
