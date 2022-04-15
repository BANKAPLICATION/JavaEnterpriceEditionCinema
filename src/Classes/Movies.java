package Classes;

import java.sql.Timestamp;

public class Movies {
    private Long id;
    private String name;
    private Genre genre;
    private Timestamp date;
    private String img;
    private String video;

    public Movies() {
    }

    public Movies(Long id, String name, Genre genre, Timestamp date, String img, String video) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.date = date;
        this.img = img;
        this.video = video;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }


    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", date=" + date +
                ", img='" + img + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
