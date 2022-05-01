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
<form action="/companies" method="post">
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
                          <a href = "/companies?projects=<c:out value="${Back}"/>" type="button" class="btn btn-primary">Back</a>
                           <a href = "" type="reset" class="btn btn-success">Reset</a>
                 </div>
        </div>
         <input type="hidden"  value="<c:out value="${Back}"/>"   name="CompaniesId">
        <table class="table">
            <thead>
            <tr>
            <th scope="col">Choose</th>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Start project</th>
               <th scope="col">Cost project</th>

            </tr>
            </thead>
            <tbody>
             <tr>

            <c:forEach items="${Projects}" var="p">
          <td>  <input type="checkbox" name="projectId" value="${p.getId_projects()}"></td>
            <td> <c:out value="${p.getId_projects()}"/> </td>
           <td>  <c:out value="${p.getName_projects()}"/>  </td>
            <td>  <c:out value="${p.getStart_project()}"/> </td>
            <td>  <c:out value="${p.getCost_project()}"/> </td>




                <td>


                </tr>
              </c:forEach>
            </tbody>
        </table>
        <div class="form-group row">
                    <div class="col-sm-10">
                      <button type="submit" class="btn btn-warning">Save</button>
                    </div>
                  </div>
    </div>
     </form>
</div>

</body>
</html>