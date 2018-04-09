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
<!-- [START form] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
  <h3>
    <c:out value="${action}" /> book
  </h3>

  <form method="POST" action="${destination}" enctype="multipart/form-data">

    <!--ceci est super important. -->
    <input type="hidden" name="id" value="${book.id}" />

    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" name="title" id="title" value="${fn:escapeXml(book.title)}" class="form-control" disabled/>
    </div>

    <div class="form-group">
      <label for="author">Author</label>
      <input type="text" name="author" id="author" value="${fn:escapeXml(book.author)}" class="form-control" disabled/>
    </div>

    <div class="form-group">
      <label for="publishedDate">Date Published</label>
      <input type="text" name="publishedDate" id="publishedDate" value="${fn:escapeXml(book.publishedDate)}" class="form-control" disabled/>
    </div>

    <div class="form-group">
      <label for="description">Description</label>
      <input type="text" name="description" id="description" value="${fn:escapeXml(book.description)}" class="form-control" disabled/>
    </div>

    <div class="form-group">
      <label for="rating">Rating</label>
      <input type="text" name="rating" id="rating" value="${fn:escapeXml(book.rating)}" class="form-control" disabled/>
    </div>

    <div class="form-group">
      <label for="comments">Comments</label>
      <input type="text" name="comments" id="comments" value="${fn:escapeXml(book.comments)}" class="form-control" />
    </div>

    <button type="submit" class="btn btn-success">Save</button>
  </form>
</div>
<!-- [END form] -->