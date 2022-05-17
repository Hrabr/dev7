<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Developers page</title>
   <jsp:include page = "headers.jsp"/></head>
<body>

<jsp:include page = "navigators.jsp"/>
<% ua.goit.dto.DeveloperDto  developers = (ua.goit.dto.DeveloperDto) request.getAttribute("Developers");%>




<div class="container">
    <form action="/developers/edit" method="post">
        <div style="margin: 10px">
         <c:choose>
                 <c:when test = "${Name !=null}">
                   <h2>Developer <c:out value="${Name}"/></h2>
                 </c:when>
                  <c:otherwise>
                           <h2>Edit developer</h2>
                          </c:otherwise>
                          </c:choose>

        </div>
     <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                               <div class="btn-group me-2" role="group" aria-label="Second group">
                                                 <a href = "/developers" type="button" class="btn btn-primary">Back</a>

                                              </div>
                                           </div>
      <br>
        <form>

         <input type="hidden"  value="<%=developers.getId_developers() %>"   name="developersId">

          <div class="form-group row">
            <label for="Name" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" value = "<%= developers.getName() %>" id="Name" placeholder="Input name developer"name="Name">
            </div>
          </div>
          <br>
          <div class="form-group row">
            <label for="Age" class="col-sm-2 col-form-label">Age</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" value = "<%=developers.getAge()  %>" id="Age" placeholder="Input age developer"name="Age">
            </div>
          </div>
          <br>
          <div class="form-group row">
               <label for="Gender" class="col-sm-2 col-form-label">Gender</label>
               <div class="col-sm-10">
               <select class="custom-select col-sm-2" value = "<%= developers.getGender()  %>" id="Gender"name="Gender">
                 <option value="male">male</option>
                 <option value="female">female</option>
               </select>
             </div>
             </div>
<br>
           <div class="form-group row">
                      <label for="Salary" class="col-sm-2 col-form-label">Salary</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" value = "<%= developers.getSalary() %>"id="Salary" placeholder="Input salary developer"name="Salary">
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