<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Home</title>
<style type="text/css">
.card {
	width: 300px;
	height: 700px; margin-top : 50px;
}
.indexBody{
display: flex;
justify-content: space-between;
padding-left: 50px;
padding-right: 50px;
}
.latestArticle{
width: 30%;
}
</style>
</head>
<body>
	<jsp:include page="common/nav.jsp" />
	
	<div class="indexBody">
		<sec:authorize access="isAuthenticated()">

			<sec:authentication property="principal.mvo.email" var="authEmail" />
			<sec:authentication property="principal.mvo.nickName" var="authNick" />
			<sec:authentication property="principal.mvo.authList" var="auths" />

			<div class="card profile">
				<img src="/resources/img/logo.png" class="card-img-top" alt="...">

				<div class="card-body">
					<hr>
					<a class="nav-link" href="/member/detail?email=${authEmail}">
						<h5 class="card-title">${authNick}님(${authEmail})</h5>
					</a>

					<c:choose>
						<c:when
							test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
							<a class="nav-link" href="/member/list">
								<h5 class="card-title">User List</h5>
							</a>
						</c:when>
					</c:choose>
					<hr>
					<p class="card-text">자기 소개</p>
				</div>
			</div>
			
				
			<div class="card latestArticle">
			<div>
			<h5 class="card-text" style="text-align: center;">최신 글</h5>
				<table class="table table-hover">
				<thead>
				<tr>
				<th scope="col">BNO</th>
				<th scope="col">WRITER</th>
				<th scope="col">TITLE</th>
				<th scope="col">MOD_DATE</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="bvo">				
				<tr>
				<th scope="row"><a href="/board/detail?bno=${bvo.bno}">${bvo.bno}</a></th>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.writer}</a></td>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title}</a></td>
					<td>${bvo.modAt}</td>
				</tr>			
				</c:forEach> 		
				</tbody>
				</table>
				</div>			
			</div>
			
			<div class="card myArticle">
			<div>
			<h5 class="card-text" style="text-align: center;">내가 쓴 글</h5>
				<table class="table table-hover">
				<thead>
				<tr>
				<th scope="col">BNO</th>
				<th scope="col">WRITER</th>
				<th scope="col">TITLE</th>
				<th scope="col">MOD_DATE</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${myList}" var="myBvo">				
				<tr>
				<th scope="row"><a href="/board/detail?bno=${myBvo.bno}">${myBvo.bno}</a></th>
					<td><a href="/board/detail?bno=${myBvo.bno}">${myBvo.writer}</a></td>
					<td><a href="/board/detail?bno=${myBvo.bno}">${myBvo.title}</a></td>
					<td>${myBvo.modAt}</td>
				</tr>			
				</c:forEach> 		
				</tbody>
				</table>
				</div>
				
			</div>
		
		
		

	</sec:authorize>
	</div>
	<jsp:include page="common/footer.jsp" />
</body>
</html>
