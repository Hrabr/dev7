<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Developers page</title>
   <jsp:include page = "headers.jsp"/></head>
<body>

<jsp:include page = "navigators.jsp"/>

<div class="container">
    <div class="row">
        <div style="margin: 10px">
            <h2>Developers page</h2>
        </div>
          <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                          <div class="btn-group me-2" role="group" aria-label="Second group">
                                            <a href = "/" type="button" class="btn btn-primary">Back</a>
                                            <a href = "/developers/New" type="button" class="btn btn-success">New</a>
                                         </div>
                                      </div>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Age</th>
                <th scope="col">Gender</th>
                <th scope="col">Salary</th>
                <th scope="col">Skills</th>
                <th scope="col">Projects</th>
               <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
             <tr>

            <c:forEach items="${developers}" var="dev">
            <td> <c:out value="${dev.getId_developers()}"/> </td>
           <td>  <c:out value="${dev.getName()}"/>  </td>
            <td>  <c:out value="${dev.getAge()}"/> </td>
            <td>  <c:out value="${dev.getGender()}"/> </td>
             <td> <c:out value="${dev.getSalary()}"/> </td>
    <td>   <a href="/developers?skills=${dev.getId_developers()}">
    <c:out value="${dev.getSkillDto().size()}"/> </a>  </td>
    <td>   <a href="/developers?projects=${dev.getId_developers()}">
    <c:out value="${dev.getProjectsDto().size()}"/> </a>  </td>

                <td>

                              <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                  <div class="btn-group me-2" role="group" aria-label="Second group">
                                    <a href = "/developers?Edit=${dev.getId_developers()}" type="button" class="btn btn-warning">Edit</a>
                                    <a href = "/developers/remove?Remove=${dev.getId_developers()}" type="button" class="btn btn-danger">Remove</a>
                                 </div>
                              </div>
                      </td>
                </tr>
              </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>