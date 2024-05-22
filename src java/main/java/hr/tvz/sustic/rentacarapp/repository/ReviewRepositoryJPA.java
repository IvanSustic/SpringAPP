package hr.tvz.sustic.rentacarapp.repository;

import hr.tvz.sustic.rentacarapp.model.Review;
import hr.tvz.sustic.rentacarapp.model.UserInfo;
import hr.tvz.sustic.rentacarapp.model.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryJPA  extends JpaRepository<Review, Integer> {
    List<Review> findAllByUserInfo(UserInfo user);

    Optional<Review> findByNaslov(String naslov);
    void deleteByNaslov(String naslov);
}
