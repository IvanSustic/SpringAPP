package hr.tvz.sustic.rentacarapp.command;

import hr.tvz.sustic.rentacarapp.model.Vozilo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PoslovnicaCommand {
    @NotNull(message = "Code must not be empty")
    @Positive(message = "Code must be positive")
    private Integer code;
    @NotBlank(message = "Name must be entered")
    private String name;
    @NotEmpty
    private List<Vozilo> vozila;
    @NotBlank(message = "Adress must be entered")
    private String adresa;
    @NotEmpty(message = "Owner must be entered")
    private String owner;
}
