package hr.tvz.sustic.rentacarapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "vozila")
public class Vozilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    @Column(name = "maxPassengers")
    private Integer maxPassengers;
    @Column(name = "gearBox")
    private String gearBox;
    @Column(name = "airConditioning")
    private Boolean airConditioning;
    @Column(name = "doors")
    private Integer doors;
    @Column(name = "fuel")
    private String fuel;
    @Column(name = "lastInspection")
    private Date lastInspection;
    @Column(name = "nextInspection")
    private Date nextInspection;
    @Column(name = "mileage")
    private Integer mileage;
    @Column(name = "registration")
    private String registration;
    @Column(name = "chassisNumber")
    private String chassisNumber;

}
