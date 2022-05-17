<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Developers page</title>
   <jsp:include page = "headers.jsp"/></head>
<body>

<jsp:include page = "navigators.jsp"/>
<% ua.goit.dto.CustomerDto  customers = (ua.goit.dto.CustomerDto) request.getAttribute("customer");%>
<div class="container">
    <form action="/customers" method="post">
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
                                                 <a href = "/customers" type="button" class="btn btn-primary">Back</a>

                                              </div>
                                           </div>
      <br>
        <form>
          <input type="hidden"  value="<%=customers.getId_customers() %>"   name="customerId">
          <div class="form-group row">
            <label for="Name_customer" class="col-sm-2 col-form-label">Name customer</label>
            <div class="col-sm-10">
              <input type="text" class="form-control"  value = "<%= customers.getName_customers() %>" id="Name_customer"  placeholder="Input name customer" name="Name_customer">
            </div>
          </div>
          <br>
          <div class="form-group row">
            <label for="Country_customer" class="col-sm-2 col-form-label">Country customer</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" value = "<%= customers.getCountry_customers() %>" id="Country_customer" placeholder="Input country customer" name="Country_customer">
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