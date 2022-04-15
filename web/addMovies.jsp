<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Movies" %>
<%@ page import="Classes.Genre" %>
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
    <script type = "text/javascript" src="linksboot/tinymce/tinymce.min.js"></script>
    <script type = "text/javascript" src="linksboot/tinymce/plugin.min.js"></script>
    <script>tinymce.init({selector:'textarea'});</script>
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
            String passworderror = request.getParameter("passworderror");
            String emailerror = request.getParameter("emailerror");
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
                }if(emailerror != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                This email already exist!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <%
                }if(passworderror!=null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Passwords are not same!x`
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }if(success != null) {
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Succesfully movies added!
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
                        <form action="/addMovies" method="post">
                <div class="form-group">
                    <label for="" style="color: white;">NAME OF MOVIE</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                 <div class="form-group">
                                <label for="" style="color: white;">IMAGE OF MOVIE</label>
                                <textarea name="img"></textarea>
                 </div>
                <div class="form-group">
                    <label for="" style="color: white;">VIDEO</label>
                    <input name="video" class="form-control">
                </div>
                            <select name="genre"  class="form-control" id="">
                            <%
                                ArrayList<Genre> genres = (ArrayList<Genre>)request.getAttribute("genres");
                                if (genres!=null) {
                                    for (Genre g: genres) {
                            %>
                                <option value="<%=g.getId()%>"><%=g.getName()%></option>
                            <%

                                    }
                                }
                            %>
                            </select>
                <div class="form-group mt-3">
                    <button class="btn btn-outline-light btn-sm">ADD MOVIE</button>
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
