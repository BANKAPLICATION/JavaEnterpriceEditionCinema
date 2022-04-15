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
  <body onload="loadComments()">

  <div class="zxc">
    <div class="container ">
      <div class="row">
        <div class="col-sm-12 mx-auto">
    <%@include file="vendor/navbar.jsp"%>
        </div>
      </div>
      <div class="container" style="background-color: #111; min-height: 1200px;">
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
                <a href="/seances?id=<%=movies.getId()%>" class="btn btn-primary">SHOW SEANCES</a>
              </div>
            </div>
          </div>
          <div class="col-sm-6 p-5">
            <table class="table-striped"  cellpadding="40">
              <thead class="p-5" style="color:white;">
              <tr>
                <th>Year of issue </th>
                <th><%=movies.getDate()%></th>
              </tr>
              <tr>
                <th>NAME OF THE MOVIE</th>
                <th><%=movies.getName()%></th>
              </tr>
              <tr>
                <th>GENRE</th>
                <th><%=movies.getGenre().getName()%></th>
              </tr>
              <tr>
                <iframe width="560" height="315" src="<%=movies.getVideo()%>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
              </tr>
              </thead>

            </table>
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
                        deleteBtn = "<button class='btn btn-danger btn-sm ml-3' onclick = 'deleteComment("+ commentsArray[i].id +")'> X </button>"
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
