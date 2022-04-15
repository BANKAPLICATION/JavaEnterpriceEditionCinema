<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Movies" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8">
  <%@include file="vendor/links.jsp"%>
  <style>
    body {
      height:100%;
      background: url("linksboot/images/olimp.jpg");
    }

  </style>
  </head>
  <body>
  <div class="zxc">
    <div class="container">
      <div class="row">
        <div class="col-sm-12 mx-auto">
        <%@include file="vendor/navbar.jsp"%>
        </div>
      </div>
      <div class="container" style="background-color: #111;">

        <ul class="nav justify-content-center">
          <li class="nav-item">
            <a class="nav-link active" href="/byGenre?genre=fantasy">Fantasy</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/byGenre?genre=horror">Horror</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/byGenre?genre=triller">Triller</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/byGenre?genre=commedy">Commedy</a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="/byGenre?genre=western">Western</a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="/byGenre?genre=cartoon">Cartoon</a>
          </li>
        </ul>
        <div class="row mt-5">
          <%
            ArrayList<Movies> movies = (ArrayList<Movies>) request.getAttribute("movies");
            if (movies!=null) {
              for (Movies m: movies) {
          %>
          <div class="col-sm-4">
            <div class="card m-5" style="width: 18rem; background-color: #33322d;" >
              <div style="width: 200px; height: 200px;" class="text-center ml-5"><%=m.getImg()%></div>
              <div class="card-body" style="color: whitesmoke;">
                <h5 class="card-title"><%=m.getName()%></h5>
                <p class="card-text">Genre is <%=m.getGenre().getName()%></p>
                <a href="/details?id=<%=m.getId()%>" class="btn btn-primary">Go Watch</a>
              </div>
            </div>
          </div>
          <%
              }
            }
          %>
        </div>
      </div>


      </div>
      <%@include file="vendor/footer.jsp"%>
  </div>

  </body>
</html>
