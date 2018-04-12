<!-- [START list] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
    <div style="display: flex; justify-content: flex-start;">
    <div>
        <h3>My list</h3>
    </div>
    </div>
  <div style="display: flex; flex-wrap: wrap; justify-content: space-evenly">

  <c:choose>
  <c:when test="${empty books}">
      <i style="color: #dcdcdc; font-size: 20em;" class="fas fa-band-aid"></i>
  </c:when>
  <c:otherwise>

  <div style="100%" class="list-group list-group-flush">
  <c:forEach items="${books}" var="book">

    <a href="/read?id=${book.id}" style="padding: 2em;" class="list-group-item list-group-item-action flex-column align-items-start">
    <div style="height: 160px; width: 100%; display: flex; flex-direction: flex-row;">
        <%-- <div style="height: auto; margin-right: 2em;"> --%>
            <img style="margin-right: 1em; width: 100px;" class="card-img-top" src="${fn:escapeXml(not empty book.imageUrl?book.imageUrl:'http://placekitten.com/g/128/192')}" alt="Card image cap">
        <%-- </div> --%>
        <div style="width: 100%;">
            <div class="d-flex w-100 justify-content-between">
                <h5>${fn:escapeXml(not empty book.title?book.title:'Unknown')}</h5>
                <small>by ${fn:escapeXml(not empty book.author?book.author:'Unknown')}</small>
            </div>
            <p style="width: 100%; word-break: break-all; height:80px; overflow: hidden; text-overflow: ellipsis;
    line-height: 1;" class="mb-1">${fn:escapeXml(book.description)}</p>
            <%-- <small>Donec id elit non mi porta.</small> --%>
            <div style="margin-top: 1em; max-width: 300px;" class="progress">
                  <div style="width:${fn:escapeXml(book.rating)}%" class="progress-bar bg-info" role="progressbar" aria-valuenow="${fn:escapeXml(book.rating)}" aria-valuemin="0" aria-valuemax="100"></div>
              </div>
        </div>
    </div>
    </a>


    </c:forEach>
    </div>

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
