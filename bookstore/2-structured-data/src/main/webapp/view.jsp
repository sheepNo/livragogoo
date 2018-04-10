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

  <div style="margin-bottom: 0.5em" class="media">
    <div style="margin-right: 1em" class="media-left">
      <img style="max-height:400px" class="book-image" src="${fn:escapeXml(not empty book.imageUrl?book.imageUrl:'http://placekitten.com/g/256/192')}">
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
      <a href="/rate?id=${book.id}" class="btn btn-warning btn-sm">
        <i class="fa fa-star"></i>
          Rate
      </a>
      <c:set var="doubleRating" value="${book.numberVotes}" />
      <fmt:parseNumber var="intRating" integerOnly="true" type="number" value="${doubleRating}" />
        <%-- <span class="badge badge-primary badge-pill">${intRating} votes</span> --%>
      (${intRating} votes)
  </p>
    <div style="max-width: 300px;" class="progress">
          <div style="width:${fn:escapeXml(book.rating)}%" class="progress-bar bg-info" role="progressbar" aria-valuenow="${fn:escapeXml(book.rating)}" aria-valuemin="0" aria-valuemax="100"></div>
      </div>


<%--
      <div class="card" style="width: 100%;">
        <div class="card-header">
          Featured
        </div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">
              <h3 class="book-title">
                ${fn:escapeXml(not empty book.title?book.title:'Unknown')}
                <small>${fn:escapeXml(book.publishedDate)}</small>
            </h3>
        </li>
          <li class="list-group-item">
                    <h4 class="book-author">By ${fn:escapeXml(not empty book.author?book.author:'Unknown')}</h4>
        </li>
        <li class="list-group-item">
            <p><small class="book-added-by">
            (added by ${fn:escapeXml(not empty book.createdBy?book.createdBy:'Anonymous')})
        </small>
    </p>
</li>
          <li class="list-group-item">
              <p>
              <a href="/rate?id=${book.id}" class="btn btn-warning btn-sm">
                <i class="fa fa-star"></i>
                  Rate
              </a>
              (${book.numberVotes} votes)
          </p>
              <div style="max-width: 300px;" class="progress">
                    <div style="width:${fn:escapeXml(book.rating)}%" class="progress-bar bg-info" role="progressbar" aria-valuenow="${fn:escapeXml(book.rating)}" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
          </li>
        </ul>
      </div> --%>

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

  <div  style="margin-top: 1em; width: 100%;">
      <div class="card">
      <div  style="display: flex; justify-content: space-between;" class="card-header" class="card-header">
          <div>
              Comments
              <span class="badge badge-primary badge-pill">14</span>
          </div>
          <div>
            <a href="/comments?id=${book.id}" class="btn btn-info btn-sm">
              <i class="fa fa-comment"></i>
              Add a comment
            </a>
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
