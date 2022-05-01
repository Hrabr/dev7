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
    <form action="/customers" method="post">
        <div style="margin: 10px">
         <c:choose>
                 <c:when test = "${Name !=null}">
                   <h2>Developer <c:out value="${Name}"/></h2>
                 </c:when>
                  <c:otherwise>
                           <h2>Create new customer</h2>
                          </c:otherwise>
                          </c:choose>

        </div>
     <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                               <div class="btn-group me-2" role="group" aria-label="Second group">
                                                 <a href = "/customers" type="button" class="btn btn-primary">Back</a>
                                                 <a href = "" type="reset" class="btn btn-success">Reset</a>
                                              </div>
                                           </div>
      <br>
        <form>
          <div class="form-group row">
            <label for="Name_customers" class="col-sm-2 col-form-label">Name customers</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="Name_customers" placeholder="Input name customers" name="Name_customer">
            </div>
          </div>
          <br>
          <div class="form-group row">
            <label for="Country_customers" class="col-sm-2 col-form-label">Country customers</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="Country_customers" placeholder="Input country customers" name="Country_customer">
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