<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List Page</title>

<style type="text/css">
.box{
display: flex;
justify-content: center;
}

</style>
</head>
<body>
<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<!-- 검색 라인 -->
	<div class="container-fluid">

		<form action="/member/list" method="get">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${ph.pgvo.qty}">		
			<c:set value="${ph.pgvo.type}" var="typed" />
			<div class="box">
			<div>
			<select name="type" class="form-select">
				<option ${typed eq null? 'selected':''}>choose...</option>
				<option value='e' ${typed eq 'e'? 'selected':''}>email</option>
				<option value='n' ${typed eq 'n'? 'selected':''}>nickName</option>
				<option value='en' ${typed eq 'en'? 'selected':''}>all</option>
			</select>
			</div>
			
			<div>
			<input type="search" name="keyword" value="${ph.pgvo.keyword}" class="form-control me-2">
			</div>
	
			<button class="btn btn-primary position-relative searchBtn" type="submit">
			Search
			 <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
   			${ph.totalCount}
    		 <span class="visually-hidden">unread messages</span>
  			</span>
			</button>
			</div>
			
				
		</form>
	</div>
	<!-- 검색 라인 끝 -->

	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Email</th>
				<th scope="col">NickName</th>
				<th scope="col">RegAt</th>
				<th scope="col">LastLogin</th>
				<th scope="col">Auth</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="mvo">
				
				<tr>
				<th scope="row"><a href="/board/adminDetail?email=${mvo.email}">${mvo.email}</a></th>
					<td><a href="/board/adminDetail?email=${mvo.email}">${mvo.nickName}</a></td>
					<td>${mvo.regAt}</td>
					<td>${mvo.lastLogin}</td>
					<td>
					<c:forEach items="${mvo.authList}" var="auth">
					${auth.auth}			
					</c:forEach>
					</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>

	<!-- 페이지네이션 라인 -->
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item ${(ph.prev ne true)? 'disabled':''}">
				<a class="page-link" href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous"> 
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
			<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
				<li class="page-item">
					<a class="page-link" href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
				</li>
			</c:forEach>
			<li class="page-item ${(ph.next ne true)? 'disabled':''}">
				<a class="page-link" href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
	<!-- 페이지네이션 라인 끝-->

	<jsp:include page="../common/footer.jsp" />
</body>
</html>