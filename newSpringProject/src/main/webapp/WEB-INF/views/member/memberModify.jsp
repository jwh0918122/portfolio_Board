<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Modify Page</title>
<style type="text/css">
#box{
display: flex;
justify-content: center;
}
#box2{
width: 500px;

}
</style>
</head>
<body>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

	<div id="box">
	<div id="box2">
	<form action="/member/Modify" method="post">
		<div class="mb-3">
			<label for="e" class="form-label">Email</label> 
			<input type="text" class="form-control" id="e" value="${mvo.email}"
				readonly="readonly" name="email">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">NickName</label> <input type="text"
				class="form-control" id="n" value="${mvo.nickName}"
				name="nickName">
		</div>
		<div class="mb-3">
			<label for="e" for="r" class="form-label">RegAt</label> 
			<input type="text" id="r" class="form-control" value="${mvo.regAt}"
				readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="e" for="l" class="form-label">LastLogin</label> 
			<input type="text" id="l" class="form-control" value="${mvo.lastLogin}"
				readonly="readonly">
		</div>

		<div class="btnContainer">
				<button type="submit" class="btn btn-outline-primary detailBtn">수정 완료</button>
		</div>
		</form>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />

</body>
</html>