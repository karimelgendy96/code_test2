package code.test.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import code.test.ReviewService;
import code.test.dto.Post;
import code.test.dto.Review;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<String> addReview(@PathVariable("postId") Long postId, @RequestBody Review review) {
        review.setPost(new Post()); 
        reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully");
    }
}
