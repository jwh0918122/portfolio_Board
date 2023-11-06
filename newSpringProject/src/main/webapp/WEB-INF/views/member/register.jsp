<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign_Up Page</title>
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
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>
<div class="signUpBox">
<form action="/member/signUp" method="post">
<div class="input-group input-group-lg">
  <input type="text" class="form-control" name="email" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="Email">
</div>
<div class="input-group input-group-lg">
  <input type="text" class="form-control" name="pwd" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="PassWord">
</div>
<div class="input-group input-group-lg">
  <input type="text" class="form-control" name="nickName" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="NickName">
</div>
<div class="btnContainer">
	<button type="submit" class="btn btn-outline-primary">회원가입</button>
</div>
</form>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>