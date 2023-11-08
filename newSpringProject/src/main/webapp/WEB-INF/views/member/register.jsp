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
<jsp:include page="../common/nav.jsp"/>
<div class="signUpBox">
<form action="/member/signUp" method="post" enctype="multipart/form-data">

<div class="mb-3 input-group-lg">
<label for="e" class="form-label">Email</label>
  <input type="text" class="form-control" id="e" name="email" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
</div>

<div class="mb-3 input-group-lg">
<label for="p" class="form-label">PassWord</label>
  <input type="text" class="form-control" id="p" name="pwd" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
</div>

<div class="mb-3 input-group-lg">
<label for="n" class="form-label">NickName</label>
  <input type="text" class="form-control" id="n" name="nickName" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
</div>

<div class="mb-3">
 <label for="f" class="form-label">Profile Img</label>
  <input class="form-control" type="file" id="f" name="profile">
</div>

<div class="btnContainer">
	<button type="submit" class="btn btn-outline-primary">회원가입</button>
</div>

</form>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>