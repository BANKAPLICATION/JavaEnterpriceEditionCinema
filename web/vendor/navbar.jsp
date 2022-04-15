
<%@ page import="Classes.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Classes.Language" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #0b2e13">
    <%
            HashMap<String,String> lang = (HashMap<String, String>) request.getAttribute("lang");
        String langs = "en";
        if (lang== null) {
            Cookie []cookies = request.getCookies();
             for (Cookie c: cookies) {
                 if (c.getName().equals("lang")) {
                     langs = c.getValue();
                 }
             }
            if (langs.equals("ru")) {
                lang = Language.russian;
            }else {
                lang = Language.english;
            }
        }

    %>
    <meta charset="utf-8">
    <a class="navbar-brand" href="/home"><%=lang.get("kinogo")%></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <%
        User currentUser = (User)session.getAttribute("CURRENT_USER");
        if (currentUser!=null && currentUser.getRole().getName().equals("user")) {
    %>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/home"><%=lang.get("home")%><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile"><%=lang.get("profile")%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/home"><%=currentUser.getFull_name()%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><%=lang.get("logout")%></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%=lang.get("lang")%>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <meta charset="UTF-8">
                    <a class="dropdown-item" href="/lang?lang=en">English</a>
                    <a class="dropdown-item" href="/lang?lang=ru">Русский</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/search" method="get">
            <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0"><%=lang.get("search")%></button>
        </form>
    </div>
        <%
            }else if(currentUser!=null && currentUser.getRole().getName().equals("admin")) {
         %>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/admin"><%=lang.get("home")%><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/addUser"><%=lang.get("addUser")%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/home"><%=currentUser.getFull_name()%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><%=lang.get("logout")%></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%=lang.get("lang")%>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <meta charset="UTF-8">
                    <a class="dropdown-item" href="/lang?lang=en">English</a>
                    <a class="dropdown-item" href="/lang?lang=ru">Русский</a>

                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/search" method="get">
            <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0"><%=lang.get("search")%></button>
        </form>
    </div>

        <%

            }else if(currentUser!=null && currentUser.getRole().getName().equals("moderator")) {
        %>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/moderator"><%=lang.get("home")%><span class="sr-only">(current)</span></a>
            </li>
         <%--   <li class="nav-item">
                <a class="nav-link" href="/home"><%=currentUser.getFull_name()%></a>
            </li>--%>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><%=lang.get("logout")%></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/addMovies"><%=lang.get("addMovie")%></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/addSeance"><%=lang.get("addSeance")%></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%=lang.get("lang")%>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <meta charset="UTF-8">
                    <a class="dropdown-item" href="/lang?lang=en">English</a>
                    <a class="dropdown-item" href="/lang?lang=ru">Русский</a>

                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/search" method="get">
            <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0"><%=lang.get("search")%></button>
        </form>
    </div>
    <%

        }else {
    %>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/home"><%=lang.get("home")%><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/registr"><%=lang.get("registr")%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login"><%=lang.get("login")%></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%=lang.get("lang")%>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <meta charset="UTF-8">
                    <a class="dropdown-item" href="/lang?lang=en">English</a>
                    <a class="dropdown-item" href="/lang?lang=ru">Русский</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/search" method="get">
            <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0"><%=lang.get("search")%></button>
        </form>
    </div>
    <%
        }
    %>

</nav>

