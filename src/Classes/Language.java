package Classes;

import java.util.HashMap;

public class Language {
    public static HashMap<String, String> russian = new HashMap<>();
    public static HashMap<String, String> english = new HashMap<>();
    static {
        russian.put("kinogo", "Киного сайт");
        russian.put("home", "Дом");
        russian.put("profile", "Профиль");
        russian.put("logout", "Выйти");
        russian.put("search", "Поиск");
        russian.put("registr", "Регистрация");
        russian.put("login", "Логин");
        russian.put("addUser", "Добавить пользователя");
        russian.put("addMovie", "Добавить кино");
        russian.put("addSeance", "Добавить сеанс");
        russian.put("lang", "Язык");

        english.put("kinogo", "Kinogo website");
        english.put("home", "Home");
        english.put("profile", "Profile");
        english.put("logout", "Log out");
        english.put("login", "Login");
        english.put("search", "Search");
        english.put("addUser", "Add User");
        english.put("addMovie", "Add Movie");
        english.put("addSeance", "Add Seance");
        english.put("registr", "Registration");
        english.put("lang", "Language");
    }
}
