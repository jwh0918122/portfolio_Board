<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Register Page</title>
<style type="text/css">
.btn{
margin: 10px;
}
</style>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<div class="container">
<form action="/board/register" method="post">
<div class="mb-3">
  <label for="w" class="form-label">WRITER</label>
  <input type="text" class="form-control" id="w" name="writer">
</div>
<div class="mb-3">
  <label for="t" class="form-label">TITLE</label>
  <input type="text" class="form-control" id="t" name="title">
</div>
<div class="mb-3">
  <label for="c" class="form-label">CONTENT</label>
  <textarea class="form-control" id="c" rows="3" name="content"></textarea>
</div>
<div class="mb-3">
  <label for="f" class="form-label">FILE_UPLOAD</label>
  <input class="form-control" type="file" id="f" name="files">
</div>
<div class="btnContainer">
<button type="submit" class="btn btn-outline-primary">등록</button>
<a href="/"><button type="button" class="btn btn-outline-primary" type="button">HOME</button></a>
</div>
</form>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>