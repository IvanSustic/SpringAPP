package hr.tvz.sustic.rentacarapp.service;

import hr.tvz.sustic.rentacarapp.dto.ReviewDTO;
import hr.tvz.sustic.rentacarapp.model.Review;
import hr.tvz.sustic.rentacarapp.model.Vozilo;
import hr.tvz.sustic.rentacarapp.repository.ReviewRepositoryJPA;
import hr.tvz.sustic.rentacarapp.repository.UserRepository;
import hr.tvz.sustic.rentacarapp.repository.VoziloRepositoryJPA;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewImpl implements ReviewService {
    ReviewRepositoryJPA reviewRepositoryJPA;

    UserRepository userRepository;

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepositoryJPA.findAll()
                .stream().map(this::convertReviewToReviewDTO).toList();
    }

    @Override
    public List<ReviewDTO> findByVozilo(String registration) {

        return reviewRepositoryJPA.findAllByUserInfo(userRepository.findByUsername(registration))
                .stream().map(this::convertReviewToReviewDTO).toList();
    }

    @Override
    public Optional<ReviewDTO> findByNaslov(String naslov) {
        Optional<Review> review = reviewRepositoryJPA.findByNaslov(naslov);
        if (review.isPresent()){
            return Optional.of(convertReviewToReviewDTO(reviewRepositoryJPA.findByNaslov(naslov).get()) );
        } else return Optional.empty();
    }

    @Override
    public Optional<ReviewDTO> saveReview(Review review,String username) {
        review.setUserInfo( userRepository.findByUsername(username));
        Optional<Review> review1 = Optional.of(reviewRepositoryJPA.save(review));
        return review1.map(this::convertReviewToReviewDTO);
    }

    @Override
    public Optional<ReviewDTO> changeReview(Review review, String naslov) {
        Optional<Review> review1 = reviewRepositoryJPA.findByNaslov(naslov);
        Review review2 = review1.get();
        if (review1.isPresent()){

            review2.setNaslov(review.getNaslov());
            review2.setOcjena(review.getOcjena());
            review2.setTekst(review.getTekst());
        }


        Optional<Review> review3 = Optional.of(reviewRepositoryJPA.save(review2));

        return review3.map(this::convertReviewToReviewDTO);
    }

    @Override
    @Transactional
    public void deleteReview(String naslov) {
        reviewRepositoryJPA.deleteByNaslov(naslov);
    }

    private ReviewDTO convertReviewToReviewDTO(Review review){
        return  new ReviewDTO(review.getNaslov(),review.getTekst(),review.getOcjena() );
    }
}
