<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Movies" %>
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
    <div class="container fixed-top">
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
                }
            %>
            <div class="row mt-5 p-5">
                <div class="col-sm-6 mx-auto">
                    <div class="forwhite">
                        <form action="/registr" method="post">
                <div class="form-group">
                    <label for="" style="color: white;">FULL_NAME</label>
                    <input type="text" name="full_name" required class="form-control">
                </div>
                <div class="form-group">
                    <label for="" style="color: white;">EMAIL</label>
                    <input type="email" name="email" required class="form-control">
                </div>
                <div class="form-group">
                    <label for="" style="color: white;">PASSWORD</label>
                    <input type="password" name="password" required class="form-control">
                </div>
                <div class="form-group">
                    <label for="" style="color: white;">RE_PASSWORD</label>
                    <input type="password" name="re_password" required class="form-control">
                </div>
                <div class="form-group">
                    <button class="btn btn-outline-light btn-sm">REGISTR</button>
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
