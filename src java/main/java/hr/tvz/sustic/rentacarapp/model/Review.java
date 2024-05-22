package hr.tvz.sustic.rentacarapp.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "naslov")
    private String naslov;
    @Column(name = "tekst")
    private String tekst;
    @Column(name = "ocjena")
    private Integer ocjena;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserInfo userInfo;
}
