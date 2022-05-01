<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Developers page</title>
   <jsp:include page = "headers.jsp"/></head>
<body>

<jsp:include page = "navigators.jsp"/>

<% ua.goit.dto.ProjectsDto  project = (ua.goit.dto.ProjectsDto) request.getAttribute("ProjectDto");

%>

<div class="container">
    <form action="/project/edit" method="post">
        <div style="margin: 10px">
         <c:choose>
                 <c:when test = "${Name !=null}">
                   <h2>Developer <c:out value="${Name}"/></h2>
                 </c:when>
                  <c:otherwise>
                           <h2>Edit project</h2>
                          </c:otherwise>
                          </c:choose>


        </div>
     <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                               <div class="btn-group me-2" role="group" aria-label="Second group">
                                                 <a href = "/developers?projects=<c:out value="${proj}"/>" type="button" class="btn btn-primary">Back</a>

                                              </div>
                                           </div>
      <br>
        <form>

<input type="hidden"  value="<%=project.getId_projects() %>"   name="project">

 <div class="form-group row">
            <label for="Name" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" value = "<%= project.getName_projects() %>" id="Name" placeholder="Input name project" name="Name">
            </div>
          </div>
<br>
                  <div class="form-group row">
                             <label for="Start_project" class="col-sm-2 col-form-label">Start project</label>
                             <div class="col-sm-10">
                               <input type="text" class="form-control"value = "<%= project.getStart_project() %>" id="Start_project" placeholder="dd-mm-yyyy" name="Start_project" >
                             </div>
                           </div>
           <br>
            <div class="form-group row">
                                 <label for="Cost_project" class="col-sm-2 col-form-label">Cost project</label>
                                 <div class="col-sm-10">
                                   <input type="number" class="form-control"value ="<%= project.getCost_project() %>" id="Cost_project" placeholder="Input cost project" name="Cost_project">
                                 </div>
                               </div>
                    <br>

          <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-warning">Edit</button>
              <h2>${Save}</h2>

            </div>
          </div>
        </form>
    </div>
</div>

</body>
</html>