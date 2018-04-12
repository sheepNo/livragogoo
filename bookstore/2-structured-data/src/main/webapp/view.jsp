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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">

  <div style="margin-bottom: 2em" class="media">
    <div style="margin-right: 2em" class="media-left">
      <img style="max-height:400px" class="book-image" src="${fn:escapeXml(not empty book.imageUrl?book.imageUrl:'http://placekitten.com/g/128/192')}">
    </div>
    <div class="media-body">
      <h3 class="book-title">
        ${fn:escapeXml(not empty book.title?book.title:'Unknown')}
        <small>${fn:escapeXml(book.publishedDate)}</small>
    </h3>
      <h4 class="book-author">By ${fn:escapeXml(not empty book.author?book.author:'Unknown')}</h4>
      <p><small class="book-added-by">
          (added by ${fn:escapeXml(not empty book.createdBy?book.createdBy:'Anonymous')})
      </small>
  </p>
  <p>
      <c:if test="${not empty currentSessionUser}">
      <a href="/rate?id=${book.id}" class="btn btn-warning btn-sm">
        <i class="fa fa-star"></i>
          Rate
      </a>
        </c:if>
        <c:if test="${empty currentSessionUser}">
        <button class="btn btn-light btn-sm">
          <i class="fa fa-star"></i>
            Rate
        </button>
          </c:if>
      <c:set var="doubleRating" value="${book.numberVotes}" />
      <fmt:parseNumber var="intRating" integerOnly="true" type="number" value="${doubleRating}" />
      (${intRating} votes)
  </p>
    <div style="max-width: 300px;" class="progress">
          <div style="width:${fn:escapeXml(book.rating)}%" class="progress-bar bg-info" role="progressbar" aria-valuenow="${fn:escapeXml(book.rating)}" aria-valuemin="0" aria-valuemax="100"></div>
      </div>

    </div>
</div>

<div class="card bg-light mb-3" style="max-width: 100%">
    <div style="display: flex; justify-content: space-between;" class="card-header">
        <div>
            Description
        </div>
        <div >
            <a href="/update?id=${book.id}" class="btn btn-primary btn-sm" role="button">
                <i class="fa fa-edit"></i>
                Edit
            </a>
            <a href="/delete?id=${book.id}" class="btn btn-danger btn-sm">
                <i class="fas fa-trash-alt"></i>
                Delete
            </a>
        </div>
    </div>

    <div class="card-body">
        <p class="card-text">
            ${fn:escapeXml(book.description)}
        </p>
    </div>
</div>

  <div  style="margin-top: 1em; margin-bottom: 4em; width: 100%;">
      <div class="card">
      <div  style="display: flex; justify-content: space-between;" class="card-header" class="card-header">
          <div>
              Comments
              <span class="badge badge-primary badge-pill">${numberComments}</span>
          </div>
          <div>
              <c:if test="${not empty currentSessionUser}">
                <a href="/comments?id=${book.id}" class="btn btn-info btn-sm">
                  <i class="fa fa-comment"></i>
                  Add a comment
                </a>
            </c:if>
            <c:if test="${empty currentSessionUser}">
                <button class="btn btn-light btn-sm">
                  <i class="fa fa-comment"></i>
                  Add a comment
              </button>
            </c:if>
          </div>
      </div>
      <c:forEach items="${listComments}" var="comment">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">${fn:escapeXml(comment)}</li>
          </ul>
      </c:forEach>
      </div>
</div>
</div>
<!-- [END view] -->
