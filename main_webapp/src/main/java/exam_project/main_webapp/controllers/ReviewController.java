package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.Review;
import exam_project.main_webapp.repositories.ReviewRepository;
import exam_project.main_webapp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/recensioni")
    public List<Review> getReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping("/recensioni")
    public Review addReview(@RequestParam String content, Authentication authentication) {
        String username = authentication.getName();
        return reviewService.saveReview(username, content);
    }
}
