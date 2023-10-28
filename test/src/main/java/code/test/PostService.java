package code.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import code.test.postRepository.PostRepository;
import code.test.dto.Post;
import code.test.dto.Review;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getUserPosts(Long userId, int i) {
        return postRepository.findByUserId(userId, i);
    }

    public List<Post> getTopPosts(int i) {
        return postRepository.findTopPosts(i);
    }

	public Object addReviewToPost(long l, Review reviewDto) {
		
		return null;
	}
}
