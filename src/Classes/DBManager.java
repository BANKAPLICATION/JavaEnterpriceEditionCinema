package Classes;

import com.sun.xml.internal.ws.util.ReadAllStream;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    public static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/film?userUnicode=true&serverTimezone=UTC", "root", "");
            System.out.println("connection was succesfully established!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> movies = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT m.id, m.name, m.genre_id, m.date, m.img, m.video, g.name as genreName " +
                    "FROM movies m " +
                    "INNER JOIN genres g ON m.genre_id = g.id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                movies.add(new Movies(
                   resultSet.getLong("id"),
                   resultSet.getString("name"),
                   new Genre(
                     resultSet.getLong("genre_id"),
                     resultSet.getString("genreName")
                   ),
                   resultSet.getTimestamp("date"),
                   resultSet.getString("img"),
                        resultSet.getString("video")
                ));
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static ArrayList<Movies> getMoviesByGenre(String genre) {
        ArrayList<Movies> movies = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT m.id, m.name, m.genre_id, m.date, m.img, m.video, g.name as genreName " +
                    "FROM movies m " +
                    "INNER JOIN genres g ON m.genre_id = g.id " +
                    "WHERE g.name = ?");
            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                movies.add(new Movies(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new Genre(
                                resultSet.getLong("genre_id"),
                                resultSet.getString("genreName")
                        ),
                        resultSet.getTimestamp("date"),
                        resultSet.getString("img"),
                        resultSet.getString("video")
                ));
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static ArrayList<Movies>getMoviesBySearch(String word) {
        ArrayList<Movies> movies = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT m.id, m.name, m.genre_id, m.date, m.img, m.video, g.name as genreName " +
                    "FROM movies m " +
                    "INNER JOIN genres g ON m.genre_id = g.id " +
                    "WHERE LOWER(m.name) LIKE ? OR LOWER(g.name) LIKE ? ");
                statement.setString(1, "%" + word.toLowerCase() + "%");
                statement.setString(2, "%" + word.toLowerCase() + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                movies.add(new Movies(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new Genre(
                                resultSet.getLong("genre_id"),
                                resultSet.getString("genreName")
                        ),
                        resultSet.getTimestamp("date"),
                        resultSet.getString("img"),
                        resultSet.getString("video")
                ));
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static Movies getOneMovie(Long id) {
        Movies movie = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT m.id, m.name, m.genre_id, m.date, m.img, m.video, g.name as genreName " +
                    "FROM movies m " +
                    "INNER JOIN genres g ON m.genre_id = g.id " +
                    "WHERE m.id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movie = (new Movies(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new Genre(
                                resultSet.getLong("genre_id"),
                                resultSet.getString("genreName")
                        ),
                        resultSet.getTimestamp("date"),
                        resultSet.getString("img"),
                        resultSet.getString("video")
                ));
            }
            statement.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT u.id, u.full_name,u.email, u.password, u.role_id, u.img, r.name as roleName " +
                    "FROM users u " +
                    "INNER JOIN roles r on r.id = u.role_id " +
                    "ORDER BY u.full_name ASC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        new Roles(
                                resultSet.getLong("role_id"),
                                resultSet.getString("roleName")
                        ),
                        resultSet.getString("img")
                ));
            }

            statement.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getOneUser(Long id) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT u.id, u.full_name,u.email, u.password, u.role_id, u.img, r.name as roleName " +
                    "FROM users u " +
                    "INNER JOIN roles r on r.id = u.role_id " +
                    "WHERE u.id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = (new User(
                        resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        new Roles(
                                resultSet.getLong("role_id"),
                                resultSet.getString("roleName")
                        ),
                        resultSet.getString("img")
                ));
            }

            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public static User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT u.id, u.full_name,u.email, u.password, u.role_id, u.img, r.name as roleName " +
                    "FROM users u " +
                    "INNER JOIN roles r on r.id = u.role_id " +
                    "WHERE email =  ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                  resultSet.getLong("id"),
                  resultSet.getString("full_name"),
                  resultSet.getString("email"),
                  resultSet.getString("password"),
                  new Roles(
                          resultSet.getLong("role_id"),
                          resultSet.getString("roleName")
                  ),
                  resultSet.getString("img")
                );
            }
            statement.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public static boolean addUser(User user) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (full_name, email, password, role_id) VALUES(?,?,?,?)");
            statement.setString(1, user.getFull_name());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getRole().getId());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;

    }

    public static boolean editUser(User user) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET full_name = ?, password = ? WHERE id = ?");
            statement.setString(1, user.getFull_name());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  rows > 0;
    }


    public static boolean addComment(Long userId, String comment, Long movieId) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO comments(movie_id, comment, user_id, datePost) VALUES(?, ?, ?, NOW())");
            statement.setLong(1, movieId);
            statement.setString(2, comment);
            statement.setLong(3, userId);
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


    public static ArrayList<Comments>getAllCommentsByMovie(Long movie_id) {
        ArrayList<Comments> comments = new ArrayList<>();
        try {
                PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.movie_id, c.user_id, c.datePost, c.comment, u.full_name, u.img " +
                        "FROM comments c " +
                        "INNER JOIN users u ON u.id = c.user_id " +
                        "WHERE movie_id = ? " +
                        "ORDER BY c.datePost DESC");
                statement.setLong(1, movie_id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    comments.add(new Comments(
                       resultSet.getLong("id"),
                       resultSet.getString("comment")  ,
                       new Movies(
                               resultSet.getLong("movie_id"),
                               null,
                               null,
                               null,
                               null,
                               null
                       ),
                          new User(
                              resultSet.getLong("user_id"),
                              resultSet.getString("full_name"),
                                  null,
                                  null,
                                  null,
                                  resultSet.getString("img")
                          ),
                            resultSet.getTimestamp("datePost")
                    ));
                }
                statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }



    public static Comments getOneComment(Long comment_id, Long movie_id) {
        Comments comment = null;
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.movie_id, c.user_id, c.datePost, c.comment, u.full_name, u.img " +
                    "FROM comments c " +
                    "INNER JOIN users u ON u.id = c.user_id " +
                    "WHERE movie_id = ? AND c.id = ? " +
                    "ORDER BY c.datePost DESC");
            statement.setLong(1, movie_id);
            statement.setLong(2,comment_id );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comment = (new Comments(
                        resultSet.getLong("id"),
                        resultSet.getString("comment")  ,
                        new Movies(
                                resultSet.getLong("movie_id"),
                                null,
                                null,
                                null,
                                null,
                                null
                        ),
                        new User(
                                resultSet.getLong("user_id"),
                                resultSet.getString("full_name"),
                                null,
                                null,
                                null,
                                resultSet.getString("img")
                        ),
                        resultSet.getTimestamp("datePost")
                ));
            }
            statement.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

    public static boolean deleteComment(Comments comment) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE id = ?");
            statement.setLong(1, comment.getId());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


    public static ArrayList<Genre>getGenres() {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM genres");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                genres.add(new Genre(
                   resultSet.getLong("id"),
                   resultSet.getString("name")
                ));
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
                return genres;
    }


    public static Genre getOneGenre(Long id) {
        Genre genre = null;
        try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM genres WHERE id = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    genre = new Genre(
                      resultSet.getLong("id"),
                      resultSet.getString("name")
                    );
                }
                statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return genre;
    }

    public static boolean addMovie(Movies movie) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO movies(name, genre_id, img, video,date) VALUES(?,?,?,?,NOW())");
            statement.setString(1, movie.getName());
            statement.setLong(2, movie.getGenre().getId());
            statement.setString(3, movie.getImg());
            statement.setString(4, movie.getVideo());
           rows =  statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  rows > 0;
    }
    public static boolean deleteUser(User user) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setLong(1, user.getId());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Seances> getAllSeancesByMovie(Long id) {
        ArrayList<Seances>seances = new ArrayList<>();
        try {
                PreparedStatement statement = connection.prepareStatement("SELECT s.id, s.movie_id, s.cinema_id, s.date, m.name, m.genre_id, g.name as genreName, m.date as movieDate, m.img, m.video, c.id, c.name as cinemaName, c.city_id, ci.name as cityName " +
                        "FROM seances s " +
                        "LEFT OUTER JOIN cinema c on c.id = s.cinema_id " +
                        "LEFT OUTER JOIN movies m on m.id = s.movie_id " +
                        "INNER JOIN genres g on g.id = m.genre_id " +
                        "INNER JOIN city ci on ci.id = c.city_id " +
                        "WHERE s.movie_id = ? " +
                        "ORDER BY s.date DESC ");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    seances.add(new Seances(
                       resultSet.getLong("id"),
                       new Movies(
                               resultSet.getLong("movie_id"),
                               resultSet.getString("name"),
                               new Genre(
                                       resultSet.getLong("genre_id"),
                                       resultSet.getString("genreName")
                               ),
                               resultSet.getTimestamp("movieDate"),
                               resultSet.getString("img"),
                               resultSet.getString("video")
                       ),
                          new Cinema(
                                  resultSet.getLong("cinema_id"),
                                  resultSet.getString("cinemaName"),
                                  new City(
                                            resultSet.getLong("city_id"),
                                          resultSet.getString("cityName")
                                  )
                          ),
                            resultSet.getTimestamp("date")
                    ));
                }
                statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return seances;
    }

    public static Seances getOneSeanc(Long id) {
        Seances seance = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT s.id, s.movie_id, s.cinema_id, s.date, m.name, m.genre_id, g.name as genreName, m.date as movieDate, m.img, m.video, c.id, c.name as cinemaName, c.city_id, ci.name as cityName " +
                    "FROM seances s " +
                    "LEFT OUTER JOIN cinema c on c.id = s.cinema_id " +
                    "LEFT OUTER JOIN movies m on m.id = s.movie_id " +
                    "INNER JOIN genres g on g.id = m.genre_id " +
                    "INNER JOIN city ci on ci.id = c.city_id " +
                    "WHERE s.id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                seance = (new Seances(
                        resultSet.getLong("id"),
                        new Movies(
                                resultSet.getLong("movie_id"),
                                resultSet.getString("name"),
                                new Genre(
                                        resultSet.getLong("genre_id"),
                                        resultSet.getString("genreName")
                                ),
                                resultSet.getTimestamp("movieDate"),
                                resultSet.getString("img"),
                                resultSet.getString("video")
                        ),
                        new Cinema(
                                resultSet.getLong("cinema_id"),
                                resultSet.getString("cinemaName"),
                                new City(
                                        resultSet.getLong("city_id"),
                                        resultSet.getString("cityName")
                                )
                        ),
                        resultSet.getTimestamp("date")
                ));
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return seance;
    }





    public static boolean addTicket(Ticket ticket) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ticket(user_id, seans_id, count) VALUES(?,?,?)");
            statement.setLong(1, ticket.getUser().getId());
            statement.setLong(2, ticket.getSeanc().getId());
            statement.setInt(3, ticket.getCount());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Ticket> getTicketsByUser(User user) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT t.id, t.user_id, u.full_name, t.seans_id, s.movie_id, m.name as movieName, m.img, s.cinema_id, c.name as cinemaName, s.date, t.count " +
                    "FROM ticket t " +
                    "INNER JOIN users u on u.id = t.user_id " +
                    "INNER JOIN seances s on s.id = t.seans_id " +
                    "INNER JOIN movies m on s.movie_id = m.id " +
                    "INNER JOIN cinema c on s.cinema_id = c.id " +
                    "WHERE u.id = ?");
                statement.setLong(1, user.getId());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    tickets.add(new Ticket(
                       resultSet.getLong("id"),
                       new User(
                             resultSet.getLong("user_id"),
                             resultSet.getString("full_name"),
                               null,
                               null,
                               null,
                               null
                       ),
                            new Seances(
                                resultSet.getLong("seans_id"),
                                new Movies(
                                        resultSet.getLong("movie_id"),
                                        resultSet.getString("movieName"),
                                        null,
                                        null,
                                        resultSet.getString("img"),
                                        null
                                ),
                                 new Cinema(
                                         resultSet.getLong("cinema_id"),
                                         resultSet.getString("cinemaName"),
                                        null
                                 ) ,
                                  resultSet.getTimestamp("date")
                            ),
                            resultSet.getInt("count")
                    ));
                }
                statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }


    public static ArrayList<Cinema> getAllCinema() {
        ArrayList<Cinema> cinemas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.name, c.city_id, ci.name as cityName " +
                    "FROM cinema c " +
                    "INNER JOIN city ci on ci.id = c.city_id " +
                    "ORDER BY c.name ASC ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cinemas.add(new Cinema(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    new City(
                            resultSet.getLong("city_id"),
                            resultSet.getString("cityName")
                    )
                ));
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return cinemas;
    }

    public static Cinema getOneCinema(Long id) {
        Cinema cinema = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.name, c.city_id, ci.name as cityName " +
                    "FROM cinema c " +
                    "INNER JOIN city ci on ci.id = c.city_id " +
                    "WHERE c.id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cinema = (new Cinema(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new City(
                                resultSet.getLong("city_id"),
                                resultSet.getString("cityName")
                        )
                ));
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return cinema;
    }

    public static boolean addSeance(Seances seances) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO seances(movie_id, cinema_id, date) VALUES(?,?,?)");
            statement.setLong(1, seances.getMovie().getId());
            statement.setLong(2, seances.getCinema().getId());
            statement.setTimestamp(3, seances.getDate());
            rows  = statement.executeUpdate();
            statement.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
}
