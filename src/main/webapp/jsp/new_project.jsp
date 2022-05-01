<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Developers page</title>
   <jsp:include page = "headers.jsp"/></head>
<body>

<jsp:include page = "navigators.jsp"/>

<%  String Id = (String) request.getAttribute("Id");
  String In = (String) request.getAttribute("In");
  String Ie="";
  if(Id==null){
  Ie=In;
  }else{Ie=Id;}

 %>

<div class="container">
    <form action="/project/new" method="post">
        <div style="margin: 10px">
         <c:choose>
                 <c:when test = "${Name !=null}">
                   <h2>Developer <c:out value="${Name}"/></h2>
                 </c:when>
                  <c:otherwise>
                           <h2>Create new project</h2>
                          </c:otherwise>
                          </c:choose>

        </div>
     <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                               <div class="btn-group me-2" role="group" aria-label="Second group">
                                                 <a href = "/developers?projects=<%=Ie%>" type="button" class="btn btn-primary">Back</a>
                                                 <a href = "" type="reset" class="btn btn-success">Reset</a>
                                              </div>
                                           </div>
      <br>
        <form>

        <input type="hidden"  value="<%=Id%>"   name="Id">


          <div class="form-group row">
            <label for="Name" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="Name" placeholder="Input name project" name="Name">
            </div>
          </div>
          <br>
          <div class="form-group row">
            <label for="Start_project" class="col-sm-2 col-form-label">Start project</label>
            <div class="col-sm-10">
              <input type="date" class="form-control" id="Start_project" placeholder="dd-mm-yyyy" name="Start_project" >
            </div>
          </div>

<br>
           <div class="form-group row">
                      <label for="Cost_project" class="col-sm-2 col-form-label">Cost project</label>
                      <div class="col-sm-10">
                        <input type="number" class="form-control" id="Cost_project" placeholder="Input cost project" name="Cost_project">
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