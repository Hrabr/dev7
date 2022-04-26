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
                           <h2>Customers page</h2>
                          </c:otherwise>
                          </c:choose>

        </div>
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                     <a href = "/" type="button" class="btn btn-primary">Back</a>
                                     <a href = "/customers/New" type="button" class="btn btn-success">New</a>
                            </div>
                   </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Customers country</th>
                <th scope="col">Projects</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
             <tr>

            <c:forEach items="${Customers}" var="c">
            <td> <c:out value="${c.getId_customers()}"/> </td>
           <td>  <c:out value="${c.getName_customers()}"/>  </td>
            <td>  <c:out value="${c.getCountry_customers()}"/> </td>
<td>   <a href="/customers?projects=${c.getId_customers()}">
    <c:out value="${c.getProjectsDto().size()}"/> </a>  </td>



                <td>

                              <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                  <div class="btn-group me-2" role="group" aria-label="Second group">
                                    <a href = "/customers?edit=<c:out value="${c.getId_customers()}"/>"" type="button" class="btn btn-warning">Edit</a>
                                    <a href = "/customers?remove=<c:out value="${c.getId_customers()}"/>" type="button" class="btn btn-danger">Remove</a>
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