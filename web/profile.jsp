<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Movies" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
  <%@include file="vendor/links.jsp"%>
  </head>
  <style>
    body {
      height:100%;
      background: url("linksboot/images/olimp.jpg");
    }

  </style>
  <body>

  <div class="zxc">
    <div class="container">
      <div class="row">
        <div class="col-sm-12 mx-auto">
    <%@include file="vendor/navbar.jsp"%>
        </div>
      </div>
      <div class="container" style="background-color: #111;">
        <div class="row mt-5 p-5">

         <div class="col-sm-4 mx-auto">
           <div class="card" style="width: 18rem;">
             <img src="linksboot/images/<%=currentUser.getImg()%>" height="300px" class="card-img-top" alt="...">
             <div class="card-body">
               <h5 class="card-title"><%=currentUser.getFull_name()%></h5>
               <p class="card-text"><%=currentUser.getEmail()%></p>
             </div>
             <div class="card-body">
               <a href="/edit?id=<%=currentUser.getId()%>" class="card-link">EDIT</a>
               <a href="/mytickets?id=<%=currentUser.getId()%>" class="card-link">MY SEANSES</a>
             </div>
           </div>
         </div>
        </div>
      </div>


      </div>
      <%@include file="vendor/footer.jsp"%>
  </div>



  </body>
</html>
