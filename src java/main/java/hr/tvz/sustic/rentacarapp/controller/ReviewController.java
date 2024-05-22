package hr.tvz.sustic.rentacarapp.controller;

import hr.tvz.sustic.rentacarapp.command.VoziloCommand;
import hr.tvz.sustic.rentacarapp.dto.ReviewDTO;
import hr.tvz.sustic.rentacarapp.dto.VoziloDTO;
import hr.tvz.sustic.rentacarapp.model.Review;
import hr.tvz.sustic.rentacarapp.service.ReviewService;
import hr.tvz.sustic.rentacarapp.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
@Slf4j
    @CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    private ReviewService reviewService;

    @GetMapping("/all")
    public List<ReviewDTO> getAllVozila() {

        log.info("Get all reviews called");

        return reviewService.findAll();
    }



    @GetMapping("/user/{username}")
    public List<ReviewDTO> getReviews(@PathVariable String username){
        log.info("Get vozilo by registration called");

        return reviewService.findByVozilo(username);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{naslov}")
    public void delete(@PathVariable String naslov){
        reviewService.deleteReview(naslov);
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<ReviewDTO> saveVozilo(@RequestBody Review review,@PathVariable String username){
        System.out.println("asdasdsadasd:     " + review.getNaslov());
        Optional<ReviewDTO> reviewDTO = reviewService.saveReview(review,username);

        return reviewDTO.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PutMapping("/change/{naslov}")
    public ResponseEntity<ReviewDTO> changeVozilo(@RequestParam Review review,@PathVariable String naslov){

        Optional<ReviewDTO> reviewDTO = reviewService.changeReview(review,naslov);

        return reviewDTO.map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/find/{naslov}")
    public ResponseEntity<ReviewDTO> getVoziloByChassis(@PathVariable String naslov){
        log.info("Get review by naslov called");
        Optional<ReviewDTO>  review=  reviewService.findByNaslov(naslov);

        return review.map(reviewDTO -> ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
}
