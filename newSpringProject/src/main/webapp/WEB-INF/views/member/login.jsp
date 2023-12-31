<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style type="text/css">
.signUpBox{
display:flex;
justify-content:center;
}
.signUpBox div{
margin-top:30px;
width: 500px;
}	
</style>
</head>
<body>
<jsp:include page="../common/nav.jsp"/>

<div class="signUpBox">
<form action="/member/login" method="post">
<div class="input-group input-group-lg">
  <input type="text" class="form-control" name="email" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="Email">
</div>
<div class="input-group input-group-lg">
  <input type="text" class="form-control" name="pwd" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="PassWord">
</div>
<div class="btnContainer">
	<button type="submit" class="btn btn-outline-primary">로그인</button>
</div>
</form>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>