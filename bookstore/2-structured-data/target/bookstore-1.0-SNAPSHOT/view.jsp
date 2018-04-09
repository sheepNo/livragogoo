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
  </h2>


  <div style="margin-bottom: 2.5em" class="media">
    <div class="media-left">
      <img class="book-image" src="${fn:escapeXml(not empty book.imageUrl?book.imageUrl:'http://placekitten.com/g/256/192')}">
    </div>
    <div class="media-body">
      <h3 class="book-title">
        ${fn:escapeXml(book.title)}
        <small>${fn:escapeXml(book.publishedDate)}</small>
    </h3>
      <h4 class="book-author">By ${fn:escapeXml(not empty book.author?book.author:'Unknown')}</h4>
      <p>rating:
          <%-- <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${fn:escapeXml(book.rating)}"/> --%>
      ${fn:escapeXml(book.rating)} 
      (${fn:escapeXml(book.numberVotes)} votes)</p>
      <p class="book-description">${fn:escapeXml(book.description)}</p>
      <small class="book-added-by">Added by
        ${fn:escapeXml(not empty book.createdBy?book.createdBy:'Anonymous')}</small>
    </div>
</div>


      <div style="margin-bottom:2.5em" class="btn-group">
          <a href="/rate?id=${book.id}" class="btn btn-warning btn-sm">
            <i class="glyphicon glyphicon-star"></i>
              Rate
          </a>
        <a href="/update?id=${book.id}" class="btn btn-primary btn-sm">
          <i class="glyphicon glyphicon-edit"></i>
          Edit
        </a>
        <a href="/delete?id=${book.id}" class="btn btn-danger btn-sm">
          <i class="glyphicon glyphicon-trash"></i>
          Delete
        </a>
      </div>



  <%-- <c:choose>
  <c:when test="${empty comments}"> --%>
  <p>No comments found</p>
  <%-- </c:when>
  <c:otherwise>
  <c:forEach items="${comments}" var="comment">
  <p>${fn:escapeXml(comment.message)}</p>
  <p>${fn:escapeXml(comment.rating)}</p>
  </c:forEach> --%>

</div>
<!-- [END view] -->
