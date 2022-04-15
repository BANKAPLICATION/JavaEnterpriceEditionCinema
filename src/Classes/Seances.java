package Classes;

import java.sql.Timestamp;

public class Seances {
    private Long id;
    private Movies movie;
    private Cinema cinema;
    private Timestamp date;


    public Seances() {
    }

    public Seances(Long id, Movies movie, Cinema cinema, Timestamp date) {
        this.id = id;
        this.movie = movie;
        this.cinema = cinema;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Seances{" +
                "id=" + id +
                ", movie=" + movie +
                ", cinema=" + cinema +
                ", date=" + date +
                '}';
    }
}
