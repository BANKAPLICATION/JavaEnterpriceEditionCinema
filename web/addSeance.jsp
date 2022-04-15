<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Movies" %>
<%@ page import="Classes.Cinema" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            <%
            String error = request.getParameter("error");
            String success = request.getParameter("success");
            if (error!=null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Something went wrong!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <%
                }if(success != null) {
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Successfullu Seances added!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <%
                }
            %>
            <div class="row mt-5 p-5">
                <div class="col-sm-6 mx-auto">
                    <div class="forwhite">
                        <form action="/addSeance" method="post">
                <div class="form-group">
                    <label for="" style="color: white;">MOVIES</label>
                    <select name="movies" class="form-control">
                    <%
                    ArrayList<Movies> movies = (ArrayList<Movies>)request.getAttribute("movies");
                    if(movies!=null) {
                        for (Movies m: movies) {
                    %>
                    <option value="<%=m.getId()%>"><%=m.getName()%></option>
                    <%
                        }
                        }
                    %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="" style="color: white;">CINEMAS</label>
                    <select name="cinema" class="form-control">
                    <%
                        ArrayList<Cinema> cinemas = (ArrayList<Cinema>)request.getAttribute("cinemas");
                        if(cinemas!=null) {
                            for (Cinema c: cinemas) {
                    %>
                    <option value="<%=c.getId()%>"><%=c.getName()%></option>
                    <%
                        }
                        }
                    %>
                    </select>
                </div>

                  <div class="form-group">
                      <label for="" style="color:white;">DATE OF SEANCE OR RELEASE</label>
                      <input type="date" name="date" class="form-control">
                  </div>

                <div class="form-group mt-3">
                    <button class="btn btn-outline-light btn-sm">add Seance</button>
                </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </div>
    <%@include file="vendor/footer.jsp"%>
</div>



</body>
</html>
