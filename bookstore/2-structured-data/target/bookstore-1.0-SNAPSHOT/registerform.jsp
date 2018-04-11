<!-- [START form] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
  <h3>
    <c:out value="${action}: ${book.title}" />
  </h3>

  <form method="POST" action="${destination}" enctype="multipart/form-data">

    <div class="form-group">
      <label for="bufComments"></label>
      <textarea style="min-height: 400px;" name="bufComments" id="bufComments" class="form-control">${fn:escapeXml(book.bufComments)}</textarea>
    </div>

    <button type="submit" class="btn btn-success">
        <i class="fas fa-check"></i>
        Save
    </button>

    <a href="/read?id=${book.id}" class="btn btn-danger">
        <i class="fas fa-times"></i>
        Cancel
    </a>

  </form>
</div>
<!-- [END form] -->
