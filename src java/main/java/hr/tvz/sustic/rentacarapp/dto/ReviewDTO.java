package hr.tvz.sustic.rentacarapp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDTO {
    private String naslov;
    private String tekst;
    private Integer ocjena;
}
