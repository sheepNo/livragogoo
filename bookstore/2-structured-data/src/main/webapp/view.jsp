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
  <h3>Book</h3>
  <div class="btn-group">
    <a href="/update?id=${book.id}" class="btn btn-primary btn-sm">
      <i class="glyphicon glyphicon-edit"></i>
      Edit
    </a>
    <a href="/delete?id=${book.id}" class="btn btn-danger btn-sm">
      <i class="glyphicon glyphicon-trash"></i>
      Delete
    </a>
    <a href="/rate?id=${book.id}" class="btn btn-primarysecondary btn-sm">
      <i class="glyphicon glyphicon-edit"></i>
        Rate this book
    </a>
  </div>

  <div class="media">
    <div class="media-left">
      <img class="book-image" src="${fn:escapeXml(not empty book.imageUrl?book.imageUrl:'http://placekitten.com/g/256/192')}">
    </div>
    <div class="media-body">
      <h4 class="book-title">
        ${fn:escapeXml(book.title)}
        <small>${fn:escapeXml(book.publishedDate)}</small>
      </h4>
      <h5 class="book-author">By ${fn:escapeXml(not empty book.author?book.author:'Unknown')}</h5>
      <p>rating: ${fn:escapeXml(book.rating)}</p>
      <p class="book-description">${fn:escapeXml(book.description)}</p>
      <small class="book-added-by">Added by
        ${fn:escapeXml(not empty book.createdBy?book.createdBy:'Anonymous')}</small>
    </div>
  </div>

  <%-- <c:choose>
  <c:when test="${empty comments}">
  <p>No comments found</p>
  </c:when>
  <c:otherwise>
  <c:forEach items="${comments}" var="comment">
  <div class="media">
    <a href="/read?id=${book.id}">
      <div class="media-left">
        <img alt="ahhh" src="${fn:escapeXml(not empty book.imageUrl?book.imageUrl:'http://placekitten.com/g/128/192')}">
      </div>
      <div class="media-body">
        <h4>${fn:escapeXml(book.title)}</h4>
        <p>${fn:escapeXml(book.author)}</p>
        <p>${fn:escapeXml(book.rating)}</p>
      </div>
    </a>
  </div>
  </c:forEach>   --%>

</div>
<!-- [END view] -->
