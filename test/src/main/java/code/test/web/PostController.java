package code.test.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import code.test.PostService;
import code.test.dto.Post;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody Post post) {
        postService.addPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post added successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable("userId") Long userId, int i) {
        List<Post> userPosts = postService.getUserPosts(userId, i);
        return ResponseEntity.ok(userPosts);
    }

    @GetMapping("/top")
    public ResponseEntity<List<Post>> getTopPosts(int i) {
        List<Post> topPosts = postService.getTopPosts(i);
        return ResponseEntity.ok(topPosts);
    }
}
