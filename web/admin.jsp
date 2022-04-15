<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Movies" %>
<%@ page import="Classes.Roles" %>
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
      <div class="container mt-5" style="background-color: white;">
        <table class="table">

        <thead>
        <tr>
          <th>
            ID
          </th>
          <th>
            FULL_NAME
          </th>
          <th>
            EMAIL
          </th>
            <th>
              PASSWORD
            </th>
          <th>
            ROLES
          </th>
          <th>
            DETAILS
          </th>
        </tr>
        </thead>
          <tbody>
            <%
            ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
            if (users!=null) {
              for (User u: users) {
                %>
            <tr>

              <td><%=u.getId()%></td>
              <td><%=u.getFull_name()%></td>
              <td><%=u.getEmail()%></td>
              <td><%=u.getPassword()%></td>
              <td><%=u.getRole().getName()%></td>
              <td><a href="/detailsadmin?id=<%=u.getId()%>" class="btn btn-primary btn-sm">DETAILS</a></td>
            </tr>
            <%
                }
            %>
            <%
                }
            %>
          </tbody>


        </table>
      </div>


      </div>
      <%@include file="vendor/footer.jsp"%>
  </div>

  </body>
</html>
