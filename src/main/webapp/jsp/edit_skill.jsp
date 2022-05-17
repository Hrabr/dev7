<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Developers page</title>
   <jsp:include page = "headers.jsp"/></head>
<body>

<jsp:include page = "navigators.jsp"/>

<% ua.goit.dto.SkillDto  skill = (ua.goit.dto.SkillDto) request.getAttribute("SkillDto");%>

<div class="container">
    <form action="/skill/edit" method="post">
        <div style="margin: 10px">
         <c:choose>
                 <c:when test = "${Name !=null}">
                   <h2>Developer <c:out value="${Name}"/></h2>
                 </c:when>
                  <c:otherwise>
                           <h2>Edit skill</h2>
                          </c:otherwise>
                          </c:choose>


        </div>
     <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                               <div class="btn-group me-2" role="group" aria-label="Second group">
                                                 <a href = "/developers?skills=<c:out value="${skills}"/>" type="button" class="btn btn-primary">Back</a>

                                              </div>
                                           </div>
      <br>
        <form>

<input type="hidden"  value="<%=skill.getId_skill() %>"   name="skills">
<input type="hidden"  value="<c:out value="${skills}"/>"   name="back">


               <div class="form-group row">
                                   <label for="Gender" class="col-sm-2 col-form-label">Skill</label>
                                   <div class="col-sm-10">
                                   <select class="custom-select col-sm-2" id="level" name="level">
                                     <option selected value = "<%= skill.getLevel_skill() %>" >Choose level</option>
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
                                  <option selected value = "<%= skill.getLanguage() %>">Choose language</option>
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
              <button type="submit" class="btn btn-warning">Edit</button>
              <h2>${Save}</h2>

            </div>
          </div>
        </form>
    </div>
</div>

</body>
</html>