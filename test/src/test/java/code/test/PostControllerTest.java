package code.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import code.test.dto.Post;
import code.test.dto.Review;
import code.test.web.PostController;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPost() throws Exception {
        Post postDto = new Post();
        postDto.setTitle("Test Title");
        postDto.setBody("Test Body");

        when(postService.addPost(postDto)).thenReturn(postDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("Test Body"));
    }

    @Test
    public void testListUserPosts() throws Exception {
        List<Post> posts = Arrays.asList(
                new Post(1L, "Post 1", "Body 1"),
                new Post(2L, "Post 2", "Body 2")
        );

        when(postService.getUserPosts(1L,10)).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/user/1?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[0].title").value("Post 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[0].body").value("Body 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[1].title").value("Post 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[1].body").value("Body 2"));
    }

    @Test
    public void testListTopPosts() throws Exception {
        List<Post> topPosts = Arrays.asList(
                new Post(1L, "Top Post 1", "Top Body 1"),
                new Post(2L, "Top Post 2", "Top Body 2")
        );

        when(postService.getTopPosts(10)).thenReturn(topPosts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/top?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[0].title").value("Top Post 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[0].body").value("Top Body 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[1].title").value("Top Post 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[1].body").value("Top Body 2"));
    }

    @Test
    public void testAddReviewToPost() throws Exception {
        Review reviewDto = new Review();
        reviewDto.setRating(4);
        reviewDto.setComment("Nice post!");

        when(postService.addReviewToPost(1L, reviewDto)).thenReturn(reviewDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/posts/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reviewDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("Nice post!"));
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
