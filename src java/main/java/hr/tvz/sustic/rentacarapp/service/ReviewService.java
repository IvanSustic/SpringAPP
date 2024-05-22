package hr.tvz.sustic.rentacarapp.service;

import hr.tvz.sustic.rentacarapp.command.VoziloCommand;
import hr.tvz.sustic.rentacarapp.dto.ReviewDTO;
import hr.tvz.sustic.rentacarapp.dto.VoziloDTO;
import hr.tvz.sustic.rentacarapp.model.Review;
import hr.tvz.sustic.rentacarapp.model.Vozilo;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO> findAll();
    List<ReviewDTO> findByVozilo(String  registration);
    Optional<ReviewDTO> findByNaslov(String naslov);

    Optional<ReviewDTO> saveReview(Review review,String username);
    Optional<ReviewDTO> changeReview(Review review, String naslov);
    void deleteReview(String naslov);
}
