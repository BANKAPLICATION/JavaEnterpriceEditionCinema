<%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Movies" %>
<%@ page import="Classes.Ticket" %>
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
        <div class="col-sm-12">
    <%@include file="vendor/navbar.jsp"%>
        </div>
      </div>

      <div class="container" style="background-color: #111;">
        <div class="row mt-5">
          <div class="col-sm-6 mx-auto">
          <h2 style="color: white;" class="text-center mt-5">YOUR TICKETS</h2>
          </div>
          </div>
        <div class="row mt-5 p-5">

          <%
            ArrayList<Ticket> tickets = (ArrayList<Ticket>)request.getAttribute("tickets");
            if (tickets!=null) {
              for (Ticket t: tickets) {
          %>
         <div class="col-sm-4 mt-5">
           <div class="card" style="width: 18rem; background-color: #a8b0bd;">
             <div style="width: 200px; height: 200px;" class="text-center ml-5"><%=t.getSeanc().getMovie().getImg()%></div>
             <div class="card-body">
               <h5 class="card-title">AT <%=t.getSeanc().getDate()%> IN <%=t.getSeanc().getCinema().getName()%></h5>
               <p class="card-text">FILM <%=t.getSeanc().getMovie().getName()%></p>
               <p class="card-text">COUNT OF TICKETS <%=t.getCount()%></p>
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
