<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Modify Page</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<form action="/board/modify" method="post">
<div class="container">
<div class="mb-3">
  <input type="hidden" name="bno" value="${bvo.bno}">
  <label for="w" class="form-label">WRITER</label>
  <input type="text" class="form-control" id="w" name="writer" value="${bvo.writer}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="t" class="form-label">TITLE</label>
  <input type="text" class="form-control" id="t" name="title" value="${bvo.title}">
</div>
<div class="mb-3">
  <label for="c" class="form-label">CONTENT</label>
  <textarea class="form-control" id="c" rows="3" name="content">${bvo.content}</textarea>
</div>
<div class="mb-3">
  <label for="f" class="form-label">FILE_UPLOAD</label>
  <input class="form-control" type="file" id="f" name="files">
</div>
<div class="btnContainer">
<button type="submit" class="btn btn-outline-primary">수정 완료</button>
</div>
</div>
</form>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>