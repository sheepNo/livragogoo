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
<!-- [START list] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
<div style="display:flex; justify-content: space-between">
  <div>
      <h3>Books</h3>
  </div>
  <div>
  <a href="/create" class="btn btn-success btn-sm">
    <i class="fas fa-plus"></i>
    Add book
</a>
</div>
</div>
  <div style="display: flex; flex-wrap: wrap; justify-content: space-evenly">
  <c:choose>
  <c:when test="${empty books}">
      <i style="color: #dcdcdc; font-size: 20em;" class="fas fa-band-aid"></i>
  </c:when>
  <c:otherwise>
  <c:forEach items="${books}" var="book">

      <div class="card" style="width: 40%; max-width: 240px; min-width: 180px; margin:1em;">
        <a href="/read?id=${book.id}">
            <img  class="card-img-top" src="${fn:escapeXml(not empty book.imageUrl?book.imageUrl:'http://placekitten.com/g/128/192')}" alt="Card image cap">
        </a>

        <div style="display: flex; justify-content: space-between; flex-direction: column;" class="card-body">
            <div>
                <h5>${fn:escapeXml(not empty book.title?book.title:'Unknown')}</h5>
                <small>by ${fn:escapeXml(not empty book.author?book.author:'Unknown')}</small>
            </div>
            <%-- <div class="progress">
                <div style="width:${fn:escapeXml(book.rating)}%" class="progress-bar bg-info" role="progressbar" aria-valuenow="${fn:escapeXml(book.rating)}" aria-valuemin="0" aria-valuemax="100"></div>
            </div> --%>
            <%-- <div style="display: flex-direction: row; margin-top: 0.1em;" > --%>
                <div style="display: flex-direction: row; margin-top: 0.7em; justify-content: flex-end;" class="btn-group" role="group" aria-label="Basic example">
                    <a href="/update?id=${book.id}" class="btn btn-light" role="button">
                    <i class="fas fa-plus"></i>
                    </a>
                    <a href="/update?id=${book.id}" class="btn btn-light" role="button">
                    <i class="fas fa-edit"></i>
                    </a>
                    <a href="/delete?id=${book.id}" class="btn btn-light">
                    <i class="fas fa-trash-alt"></i>
                    </a>
                </div>
            <%-- </div> --%>
        </div>
      </div>

    </c:forEach>
    <%-- <c:if test="${not empty cursor}">
    <nav>
    <ul class="pager">
        <li><a href="?cursor=${fn:escapeXml(cursor)}">More</a></li>
    </ul>
    </nav>
    </c:if> --%>
    </c:otherwise>
    </c:choose>
</div>
</div>


<!-- [END list] -->
