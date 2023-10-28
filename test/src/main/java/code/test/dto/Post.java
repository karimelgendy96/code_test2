package code.test.dto;

import javax.annotation.processing.Generated;


public class Post {
   // @Id
   // @Generated(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

//    @Column(columnDefinition = "TEXT")
    private String body;

//    @ManyToOne
  //  @JoinColumn(name = "user_id")
    private User user;

	public Post(long l, String title, String body) {
		this.id=l;
		this.title=title;
		this.body=body;
	}

	public Post() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
