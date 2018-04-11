<!-- [START form] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
  <h3>
    Register
  </h3>

  <form method="POST" action="${destination}" enctype="multipart/form-data">

    <div class="form-group">
      <label for="username"></label>
        <input type="text" name="username" id="username" placeholder="username" class="form-control" />
    </div>

    <div class="form-group">
      <label for="password"></label>
      <input type="text" name="password" id="password" placeholder="password" class="form-control" />
    </div>

    <!-- <div class="form-group">
      <label for="valid"></label>
      <textarea style="display:none;" name="valid" id="valid" class="form-control" placeholder="valid">0</textarea>
    </div> -->

    <button type="submit" class="btn btn-success">
        <i class="fas fa-check"></i>
        Save
    </button>

    <a href="/" class="btn btn-danger">
        <i class="fas fa-times"></i>
        Cancel
    </a>

  </form>
</div>
<!-- [END form] -->
