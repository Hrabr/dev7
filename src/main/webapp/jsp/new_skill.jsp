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
    <form action="/skill/new" method="post">
        <div style="margin: 10px">
         <c:choose>
                 <c:when test = "${Name !=null}">
                   <h2>Developer <c:out value="${Name}"/></h2>
                 </c:when>
                  <c:otherwise>
                           <h2>Create new skill</h2>
                          </c:otherwise>
                          </c:choose>


        </div>
     <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                               <div class="btn-group me-2" role="group" aria-label="Second group">
                                                 <a href = "/developers?skills=<%=Ie%>" type="button" class="btn btn-primary">Back</a>
                                                 <a href = "" type="reset" class="btn btn-success">Reset</a>
                                              </div>
                                           </div>
      <br>
        <form>

              <input type="hidden"  value="<%=Id%>"   name="Id">


               <div class="form-group row">
                                   <label for="Gender" class="col-sm-2 col-form-label">Skill</label>
                                   <div class="col-sm-10">
                                   <select class="custom-select col-sm-2" id="level" name="level">

                                     <option value="Junior">Junior</option>
                                     <option value="Middle">Middle</option>
                                     <option value="Senior">Senior</option>

                                   </select>
                                 </div>
                                 </div>
<br>
                 <br>
                      <div class="form-group row">
                                <label for="Gender" class="col-sm-2 col-form-label">Language</label>
                                <div class="col-sm-10">
                                <select class="custom-select col-sm-2" id="language"name="language">

                                  <option value="Java">Java</option>
                                  <option value="JS">JS</option>
                                  <option value="C#">C</option>
                                  <option value="C++">C++</option>
                                  <option value="Assembly">Assembly</option>
                                  <option value="Python">Python</option>
                                </select>
                              </div>
                              </div>

           <br>
                    <br>

          <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-warning">Sign in</button>
              <h2>${Save}</h2>

            </div>
          </div>
        </form>
    </div>
</div>

</body>
</html>