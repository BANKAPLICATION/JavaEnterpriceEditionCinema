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
                }if(passworderror!=null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Passwords are not same
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }if(success!=null) {
            %>

            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Successfully saved
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%

                }
            %>
            <div class="row mt-5 p-5">
                <div class="col-sm-6 mx-auto">
                    <h2 style="color: white;">EDIT PAGE</h2>
                    <div class="forwhite">
                        <%
                        User user = (User)request.getAttribute("user");
                        if (user!=null) {
                        %>
                        <form action="/editAdmin" method="post">
                            <input type="hidden" value="<%=user.getId()%>" name="id">
                            <div class="form-group">
                                <label for="" style="color: white;">FULL_NAME</label>
                                <input type="text" name="full_name"  class="form-control" value="<%=user.getFull_name()%>">
                            </div>
                            <div class="form-group">
                                <label for="" style="color: white;">PASSWORD</label>
                                <input type="password" name="password"  class="form-control" value="<%=user.getPassword()%>">
                            </div>
                            <div class="form-group">
                                <label for="" style="color: white;">RE_PASSWORD</label>
                                <input type="password" name="re_password"  class="form-control" value="<%=user.getPassword()%>">
                            </div>

                            <div class="form-group">
                                <label for="" style="color: white;">RE_PASSWORD</label>
                                <input type="password" name="re_password"  class="form-control" value="<%=user.getPassword()%>">
                            </div>
                            <div class="form-group">
                                <button class="btn btn-outline-light btn-sm">SAVE </button>
                            </div>

                            <%
                                }
                            %>
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
