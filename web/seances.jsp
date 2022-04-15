  <%@ page import="java.util.ArrayList" %>
<%@ page import="Classes.Movies" %>
  <%@ page import="Classes.Seances" %>
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
  <body onload="loadComments()">

  <div class="zxc">
    <div class="container ">
      <div class="row">
        <div class="col-sm-12 mx-auto">
      <%@include file="vendor/navbar.jsp"%>
        </div>
      </div>
      <div class="container" style="background-color: #111; min-height: 1200px;">
          <%
              String success = request.getParameter("success");
              if(success!=null) {
          %>

          <div class="alert alert-success alert-dismissible fade show" role="alert">
              Successfully bought ticket!
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
              </button>
          </div>
          <%

              }
          %>
        <div class="row mt-5">
          <%
           Movies movies = (Movies) request.getAttribute("movie");
            if (movies!=null) {
          %>
          <div class="col-sm-4" >
            <div class="card m-5" style="width: 18rem; background-color: #33322d;" >
              <h1 class="text-center ml-5"><%=movies.getImg()%></h1><%--<img src="linksboot/images/<%=movies.getImg()%>"  class="card-img-top" height="360px" alt="...">--%>
              <div class="card-body" style="color: whitesmoke;">
                <h5 class="card-title"><%=movies.getName()%></h5>
                <p class="card-text">Genre is <%=movies.getGenre().getName()%></p>
              </div>
            </div>
          </div>

          <div class="col-sm-6 p-5" style="color: white;">
            <h2 class="text-center">SEANCES</h2>
            <table class="table table-active" style="color: white;" cellpadding="40">
             <thead>
              <tr>
               <th>TIME</th>
               <th>MOVIE</th>
               <th>CINEMA</th>
                <th>BUY TICKET FOR THIS SEANC</th>
             </tr>
             </thead>
              <tbody>
              <%
                ArrayList<Seances> seances = (ArrayList<Seances>)request.getAttribute("seances");
                if (seances!=null) {
                  for(Seances s: seances) {
              %>
                <tr>
                  <td><%=s.getDate()%></td>
                <td><%=s.getMovie().getName()%></td>
                <td><%=s.getCinema().getName()%></td>
                <td>
                  <input type="hidden" value="<%=s.getId()%>" id="id">
                  <button onclick="go()" type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#buyticket">
                    BUY TICKET
                  </button>
                 <%-- <a href="/buyseans" class="btn btn-success btn-sm">BUY TICKET</a>--%>
                </td>
              </tr>
              <%
                  }
                }
              %>
              </tbody>
            </table>
            <!-- Button trigger modal -->


            <!-- Modal -->
            <div class="modal fade" id="buyticket" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog">
                <script type="text/javascript">
                  function  go() {
                    let sas = document.getElementById('id').value;
                    document.getElementById('seans_id').value = sas;
                  }
                </script>
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">BUY TICKET FOR THIS SEANCE</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <form action="/buyticket" method="post">
                    <input type="hidden" id="seans_id" name="id">
                    <label for="">ENTER COUNT OF TICKETS</label>
                    <div class="form-group">

                      <input type="number" name="count" class="form-control" placeholder="Enter count of tickets">
                    </div>
                    <div class="form-group">
                    <button class="btn btn-success btn-sm">BUY TICKET</button>
                    </div>
                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">NO UNBUY</button>
                    </form>
                  </div>
<%--                  <div class="modal-footer">

                    <button type="button" class="btn btn-primary">YES</button>
                  </div>--%>
                </div>
              </div>
            </div>
          </div>
        </div>




        <div class="row">
          <div class="col-sm-12">
            <div id="commentIdList">

            </div>
            <script type="text/javascript">
              function loadComments() {
                  $.get("/getCommentByMovie",{
                    id: <%=movies.getId()%>
                  }, function data(data) {
                    commentsArray = JSON.parse(data);
                    commentsHTML = "";
                    for (let i = 0; i < commentsArray.length ; i++) {
                      deleteBtn = "";
                      if(commentsArray[i].user.id == <%=currentUser!=null?currentUser.getId():0%>) {
                        deleteBtn = "<button class='btn btn-danger btn-sm ml-3' onclick = 'deleteComment("+ commentsArray[i].id +")'>X</button>"
                      }
                      commentsHTML += "<div class='list-group'>";
                      commentsHTML += " <a href='Javascript:void(0)' class='list-group-item list-group-item-action'>";
                      commentsHTML += "<div class='d-flex w-100 justify-content-between'>";
                      commentsHTML += "<h5 class='mb-1'>" + commentsArray[i].user.full_name+  " </h5>";
                      commentsHTML += "<small>" + commentsArray[i].datePost + deleteBtn + "</small>";
                      commentsHTML += "</div>";
                      commentsHTML += "<p class='mb-1'>" + commentsArray[i].comment + "</p>";
                      commentsHTML += "</a>";
                      commentsHTML += "</div>";
                    }
                    document.getElementById('commentIdList').innerHTML = commentsHTML;
                  })
              }
              function deleteComment(id) {
                $.post("/deleteComment", {
                  id: id,
                  movie_id: <%=movies.getId()%>
                }, function data(data) {
                  loadComments();
                })
              }
            </script>




          </div>
      </div>
        <div class="row mt-5">
          <div class="col-sm-12">
            <div class="form-group">
              <%
                User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
                if (current_user!=null) {
              %>
            <textarea id="comment" class="form-control">
            </textarea>
              <button class="btn btn-outline-primary mt-3" onclick="addComment()">add Comment</button>
            </div>
            <%
              }else {
            %>
            <a href="/login"><h3 class="text-center">FOR LEAVE A COMMENT LOG IN</h3></a>
            <%
              }
            %>
            <script type="text/javascript">
              function addComment() {
                let comment = document.getElementById('comment');
                  $.post("/addComment", {
                    id: <%=movies.getId()%>,
                    comment: comment.value
                  }, function data() {
                    loadComments();
                    comment.value = "";
                  })
              }
            </script>
          </div>
        </div>
      </div>
    </div>
      <%@include file="vendor/footer.jsp"%>
  </div>
  <%
    }
  %>


  </body>
</html>
