<!--
Copyright 2016 Google Inc.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
<!-- [START base] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
  <head>
    <title>Livragogoo - Online Library based on Google Cloud Platform </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css" integrity="sha384-5SOiIsAziJl6AWe0HWRKTXlfcSHKmYV4RBF18PPJ173Kzn7jzMyFuTtk8JA7QQG1" crossorigin="anonymous">
<%-- https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/css/bootstrap.min.css --%>
  </head>
  <body>
    <div style="margin-bottom: 1em" class="navbar nav-tabs">
      <div class="container">
        <div class="navbar-header">
          <div class="navbar-brand">Livragogoo</div>
        </div>
        <ul class="nav">
            <li class="nav-item"><a class="nav-link btn btn-light" href="/">Available books</a></li>
          <li class="nav-item"><a class="nav-link btn btn-light" href="/">Top 3</a></li>
	  <c:if test="${isAuthConfigured}"><li><a href="/books/mine">My Books</a></li></c:if>
        </ul>
        <p class="nav navbar-right"> <!--  -nav- navbar-text navbar-right -->
            <%-- <li> --%>
            <a class="nav-item nav-link btn btn-secondary" href="/">
                <i class="fas fa-shopping-cart"></i>
                My shopping cart
            </a>
            <%-- </li> --%>

          <c:choose>
          <c:when test="${not empty token}">
          <!-- using pageContext requires jsp-api artifact in pom.xml -->
          <a href="/logout">
            <c:if test="${not empty userImageUrl}">
              <img class="img-circle" src="${fn:escapeXml(userImageUrl)}" width="24">
            </c:if>
            ${fn:escapeXml(userEmail)}
          </a>
          </c:when>
	  <c:when test="${isAuthConfigured}">
          <a href="/login">Login</a>
          </c:when>
          </c:choose>
        </p>
      </div>
    </div>
    <c:import url="/${page}.jsp" />
  </body>
</html>
<!-- [END base]-->
