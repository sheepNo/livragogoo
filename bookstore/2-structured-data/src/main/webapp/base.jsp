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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css" integrity="sha384-5SOiIsAziJl6AWe0HWRKTXlfcSHKmYV4RBF18PPJ173Kzn7jzMyFuTtk8JA7QQG1" crossorigin="anonymous">

  </head>
  <body>
    <div style="margin-bottom: 1em" class="navbar nav-tabs">
      <div class="container">
        <div class="navbar-header">
          <div class="navbar-brand">Livragogoo</div>
        </div>
        <ul class="nav">
            <li class="nav-item"><a class="nav-link btn btn-light" href="/">Available books</a></li>
            <li class="nav-item"><a class="nav-link btn btn-light" href="/top">Top books</a></li>
            <c:if test="${not empty currentSessionUser}">
                <li class="nav-item"><a class="nav-link btn btn-outline-info" href="/mylist?userid=${currentSessionUser.id}">
                    My list
                    <i class="fas fa-star"></i>
                </a></li>
            </c:if>
        </ul>
        <p class="nav navbar-right"> <!--  -nav- navbar-text navbar-right -->
            <c:if test="${not empty currentSessionUser}">
            <!-- <c:out value="logged in as ${currentSessionUser} !" /> -->
            <a class="nav-item nav-link btn btn-outline-danger" href="/logout">
                <i class="fas fa-sign-out-alt"></i>
                Log out
            </a>
            </c:if>

            <c:if test="${empty currentSessionUser}">
            <a class="nav-item nav-link btn btn-outline-primary" href="/register">
                <i class="fas fa-user-plus"></i>
                Register
            </a>
            <a class="nav-item nav-link btn btn-outline-info" href="/login">
                <i class="fas fa-sign-in-alt"></i>
                Log in
            </a>
            </c:if>

        </p>
      </div>
    </div>
    <!-- <input type="hidden"  type="text" name="userid" id="userid" value="${fn:escapeXml(currentSessionUser.id)}"/> -->
    <c:import url="/${page}.jsp" />
  </body>
</html>
<!-- [END base]-->
