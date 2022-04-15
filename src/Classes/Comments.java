package Classes;

import java.sql.Timestamp;

public class Comments {
    private Long id;
    private String comment;
    private Movies movie;
    private User user;
    private Timestamp datePost;


    public Comments() {
    }

    public Comments(Long id, String comment, Movies movie, User user, Timestamp datePost) {
        this.id = id;
        this.comment = comment;
        this.movie = movie;
        this.user = user;
        this.datePost = datePost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDatePost() {
        return datePost;
    }

    public void setDatePost(Timestamp datePost) {
        this.datePost = datePost;
    }


    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", movie=" + movie +
                ", user=" + user +
                ", datePost=" + datePost +
                '}';
    }
}
