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
        <div style="margin: 10px">
         <c:choose>
                 <c:when test = "${Name !=null}">
                   <h2>Developer <c:out value="${Name}"/></h2>
                 </c:when>
                  <c:otherwise>
                           <h2>Create new company</h2>
                          </c:otherwise>
                          </c:choose>

        </div>
     <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                               <div class="btn-group me-2" role="group" aria-label="Second group">
                                                 <a href = "/companies" type="button" class="btn btn-primary">Back</a>
                                                 <a href = "" type="reset" class="btn btn-success">Reset</a>
                                              </div>
                                           </div>
      <br>
        <form>
          <div class="form-group row">
            <label for="Name_company" class="col-sm-2 col-form-label">Name company</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="Name_company" placeholder="Input name company" name="Name_company">
            </div>
          </div>
          <br>
          <div class="form-group row">
            <label for="Country_company" class="col-sm-2 col-form-label">Country company</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="Country_company" placeholder="Input country company" name="Country_company">
            </div>
          </div>

<br>

          <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-warning">Save</button>
              <h2>${Save}</h2>
            </div>
          </div>
        </form>
    </div>
</div>

</body>
</html>