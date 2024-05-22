package hr.tvz.sustic.rentacarapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class VoziloDTO {
    private Integer maxPassengers;
    private String gearBox;
    private Boolean airConditioning;
    private Integer doors;
    private String fuel;
    private Integer mileage;
    private Boolean newForSale;
    private String registration;
    private String chassisNumber;
}
