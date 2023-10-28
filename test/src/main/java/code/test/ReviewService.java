package code.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.test.postRepository.ReviewRepository;
import code.test.dto.Review;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
