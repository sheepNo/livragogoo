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
    <c:out value="${action} ${book.title}" />
  </h3>

  <form method="POST" action="${destination}" enctype="multipart/form-data">

    <!--This is super important. -->
    <%-- <input type="hidden" name="oldRating" id="oldRating" value="${book.rating}" /> --%>
    <input type="hidden" name="id" value="${book.id}" />
    <input type="hidden" type="text" name="title" id="title" value="${fn:escapeXml(book.title)}"/>
    <input type="hidden"  type="text" name="author" id="author" value="${fn:escapeXml(book.author)}"/>
    <input type="hidden"  type="text" name="publishedDate" id="publishedDate" value="${fn:escapeXml(book.publishedDate)}" />
    <input type="hidden" type="text" name="numberVotes" id="numberVotes" value="${fn:escapeXml(book.numberVotes)}" />
    <textarea style="display:none;" name="description" id="description">${fn:escapeXml(book.description)}</textarea>
    <input type="hidden" type="text" name="rating" id="rating" value="${fn:escapeXml(book.rating)}" />

    <%-- <div class="form-group">
      <label for="bufRating">Rating</label>
      <input type="text" name="bufRating" id="bufRating" placeholder="Enter your rating here" class="form-control" />
    </div> --%>

    <select name="bufRating" id="bufRating" class="custom-select custom-select-lg">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3" selected>3</option>
      <option value="4">4</option>
      <option value="5">5</option>
    </select>

    <%-- <select id="rating" name="${book.rating}" class="custom-select">
      <option selected>Open this select menu</option>
      <option value="1">1</option>
      <option value="2">2</option>
    </select> --%>


    <button type="submit" class="btn btn-success">
        <i class="glyphicon glyphicon-ok"></i>
    </button>

    <a href="/rate?id=${book.id}" class="btn btn-danger">
        <i class="glyphicon glyphicon-remove"></i>
    </a>

  </form>
</div>
<!-- [END form] -->
