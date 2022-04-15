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
    <div class="container">
      <div class="row">
        <div class="col-sm-12 mx-auto">
        <%@include file="vendor/navbar.jsp"%>
        </div>
      </div>
      <div class="container" style="background-color: #111;">
        <%
          User user = (User)request.getAttribute("user");
          if(user!=null) {
        %>
        <div class="jumbotron m-5" style="margin-top: 20px; color: white; width: 500px; background-color: #111;">
          <h1 class="display-4"><%=user.getFull_name()%></h1>
          <p class="lead"><%=user.getRole().getName()%></p>
          <hr class="my-4">
          <p>PASSWORD: <%=user.getPassword()%></p>
          <p>EMAIL: <%= user.getEmail()%></p>
          <a class="btn btn-primary btn-sm" href="/editAdmin?id=<%=user.getId()%>">EDIT</a>
          <button type="button" class="btn btn-danger btn-sm float-right" data-toggle="modal" data-target="#delete">
            DELETE
          </button>

        </div>

        <!-- Button trigger modal -->


        <!-- Modal -->
        <div class="modal fade" id="delete" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
          <div class="modal-dialog">

            <div class="modal-content">
              <form action="/deleteUser" method="post">
                <input type="hidden" value="<%=user.getId()%>" name="id">
              <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">DELETE USER</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                Are you sure?
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary">YES</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">NO</button>
              </div>
            </form>
            </div>
          </div>
        </div>

        <%
          }
        %>
      </div>
      </div>
      <%@include file="vendor/footer.jsp"%>
  </div>

  </body>
</html>
