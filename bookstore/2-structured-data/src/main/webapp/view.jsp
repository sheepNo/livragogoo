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
<!-- [START view] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
  <h2>
      <c:out value="${book.title}" />
      <small class="book-added-by">(Added by
        ${fn:escapeXml(not empty book.createdBy?book.createdBy:'Anonymous')})</small>
  </h2>


  <div style="margin-bottom: 0.5em" class="media">
    <div style="margin-right: 1em" class="media-left">
      <img class="book-image" src="${fn:escapeXml(not empty book.imageUrl?book.imageUrl:'http://placekitten.com/g/256/192')}">
    </div>
    <div class="media-body">
      <h3 class="book-title">
        ${fn:escapeXml(book.title)}
        <small>${fn:escapeXml(book.publishedDate)}</small>
    </h3>
      <h4 class="book-author">By ${fn:escapeXml(not empty book.author?book.author:'Unknown')}</h4>
      <p>
      <a href="/rate?id=${book.id}" class="btn btn-warning btn-sm">
        <i class="fa fa-star"></i>
          Rate
      </a>
      (${fn:escapeXml(book.numberVotes)} votes)
    </p>
      <%-- <p>rating: --%>
    <div class="progress">
          <div style="width:${fn:escapeXml(book.rating)}%" class="progress-bar" role="progressbar" aria-valuenow="${fn:escapeXml(book.rating)}" aria-valuemin="0" aria-valuemax="100"></div>
      </div>
     <%-- </p> --%>

      <p class="book-description">${fn:escapeXml(book.description)}</p>
    </div>
</div>


      <%-- <div style="margin-bottom:2.5em" class="btn-group"> --%>
    <p>
        <a href="/update?id=${book.id}" class="btn btn-primary btn-sm" role="button">
            <i class="fa fa-edit"></i>
         Edit
        </a>
        <a href="/delete?id=${book.id}" class="btn btn-danger btn-sm">
          <i class="fas fa-trash-alt"></i>
          Delete
        </a>
    </p>
      <%-- </div> --%>



  <%-- <c:choose>
  <c:when test="${empty comments}"> --%>

  <div  style="margin-top: 1em; width: 100%;" class="container">

  <%-- <c:choose>
  <c:when test="${empty listComments}">
  <p>No comments found</p>
  </c:when>
  <c:otherwise>--%>
      <div class="card">
      <div class="card-header">
        Comments:
      </div>
          </ul>
          <a href="/comments?id=${book.id}" class="btn btn-info btn-sm">
              <i class="fa fa-comment"></i>
              Comments
          </a>
          <ul class="list-group list-group-flush">

      <c:forEach items="${listComments}" var="comment">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">${fn:escapeXml(comment)}</li>
          </ul>
      </c:forEach>

      </div>

  <%-- <c:if test="${not empty cursor}">
  <nav>
    <ul class="pager">
      <li><a href="?cursor=${fn:escapeXml(cursor)}">More</a></li>
    </ul>
  </nav>
  </c:if> --%>
<%--
  </c:otherwise>
  </c:choose> --%>

</div>
<!-- [END view] -->
