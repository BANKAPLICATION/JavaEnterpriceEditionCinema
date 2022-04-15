package Classes;

public class Ticket {
    private Long id;
    private User user;
    private Seances seanc;
    private Integer count;


    public Ticket() {
    }

    public Ticket(Long id, User user, Seances seanc, Integer count) {
        this.id = id;
        this.user = user;
        this.seanc = seanc;
        this.count = count;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seances getSeanc() {
        return seanc;
    }

    public void setSeanc(Seances seanc) {
        this.seanc = seanc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", seanc=" + seanc +
                ", count=" + count +
                '}';
    }
}
