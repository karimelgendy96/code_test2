package code.test;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import code.test.dto.Post;
import code.test.dto.Review;

public interface postRepository {
	

	public interface UserRepository extends JpaRepository {
	}

	public interface PostRepository extends JpaRepository {

		List<Post> findTopPosts(int number);

		Post save(Post post);

		List<Post> findByUserId(Long userId, int i);
	}

	public interface ReviewRepository extends JpaRepository {

		Review save(Review review);
	}
}
