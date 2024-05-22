package hr.tvz.sustic.rentacarapp.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class VoziloCommand {
    
    private Integer code;
    @NotNull(message = "Number of max passengers must not be empty")
    @Positive(message = "Number of max passengers must be 1 or larger")
    private Integer maxPassengers;
    @NotBlank(message = "Type of gear box must be entered")
    private String gearBox;
    @NotNull(message = "Number of max passengers must not be empty")
    private Boolean airConditioning;
    @NotNull(message = "Number doors must not be empty")
    @Positive(message = "Number of doors must be 1 or larger")
    private Integer doors;
    @NotBlank(message = "Fuel type must not be empty")
    private String fuel;
    @JsonFormat(pattern = "dd.MM.yyyy.")
    @NotNull(message = "Date of inspection must be entered")
    @PastOrPresent(message = "Date of inspection must be in the past")
    private Date lastInspection;
    @JsonFormat(pattern = "dd.MM.yyyy.")
    @NotNull(message = "Date of inspection must be entered")
    @FutureOrPresent(message = "Date of inspection must be in the future")
    private Date nextInspection;
    @NotNull(message = "Mileage must be entered")
    @DecimalMin(value="0",message = "Mileage must be 0 or larger")
    private Integer mileage;
    @NotEmpty(message = "Registration must not empty")
    @Pattern(message = "Registration must have 8 characters", regexp="^[A-Z]{2}[A-Z0-9-]{6}")
    private String registration;
    @NotBlank(message = "Chassis number must be entered")
    @Size(min = 17,max = 17,message = "Chassis number must have 17 characters")
    private String chassisNumber;
}
