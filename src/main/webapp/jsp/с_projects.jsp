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
              <c:choose>
                             <c:when test = "${Name !=null}">
                               <h2>Developer <c:out value="${Name}"/></h2>
                             </c:when>
                              <c:otherwise>
                                       <h2>Projects page</h2>
                                      </c:otherwise>
                                      </c:choose>
        </div>
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                 <div class="btn-group me-2" role="group" aria-label="Second group">
                          <a href = "/companies" type="button" class="btn btn-primary">Back</a>
                          <a href = "/companies/Back?new=<c:out value="${Back}"/>" type="button" class="btn btn-success">New</a>
                 </div>
        </div>

        <table class="table">
            <thead>
            <tr>

                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Start project</th>
               <th scope="col">Cost project</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
             <tr>

            <c:forEach items="${Projects}" var="p">

            <td> <c:out value="${p.getId_projects()}"/> </td>
           <td>  <c:out value="${p.getName_projects()}"/>  </td>
            <td>  <c:out value="${p.getStart_project()}"/> </td>
            <td>  <c:out value="${p.getCost_project()}"/> </td>




                <td>

                              <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                  <div class="btn-group me-2" role="group" aria-label="Second group">

                                    <a href = "/companies/remove?id=<c:out value="${p.getId_projects()}"/>&back=<c:out value="${Back}"/>" type="button" class="btn btn-danger">Remove</a>
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