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
                   <h2>Developer <c:out value="${Name.getName()}"/></h2>
                 </c:when>
                  <c:otherwise>
                           <h2>Skills page</h2>
                          </c:otherwise>
                          </c:choose>

        </div>
      <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                      <div class="btn-group me-2" role="group" aria-label="Second group">
                               <a href = "/<c:out value="${Back}"/>" type="button" class="btn btn-primary">Back</a>
                               <a href = "/skill/new?devId=${Name.getId_developers()}" type="button" class="btn btn-success">New</a>

                      </div>
             </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Skill</th>
                <th scope="col">Language</th>
               <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
             <tr>

            <c:forEach items="${Skills}" var="s">
            <td> <c:out value="${s.getId_skill()}"/> </td>
           <td>  <c:out value="${s.getLevel_skill()}"/>  </td>
            <td>  <c:out value="${s.getLanguage()}"/> </td>




                <td>

                              <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                  <div class="btn-group me-2" role="group" aria-label="Second group">
                                    <a href = "/skill/edit?skills=<c:out value="${i}"/>&edit=<c:out value="${s.getId_skill()}"/>" type="button" class="btn btn-warning">Edit</a>
                                    <a href = "/skill/delete?skills=<c:out value="${i}"/>&delete=<c:out value="${s.getId_skill()}"/>" type="button" class="btn btn-danger">Remove</a>
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