package code.test.dto;



public class Review {
   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private int rating;

   // @Column(columnDefinition = "TEXT")
    private String comment;

  //  @ManyToOne
//    @JoinColumn(name = "post_id")
    private Post post;

   // @ManyToOne
    //@JoinColumn(name = "user_id")
    private User user;

}
