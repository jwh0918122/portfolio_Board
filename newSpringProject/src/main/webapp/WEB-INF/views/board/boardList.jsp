<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

		<form action="/board/list" method="get">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${ph.pgvo.qty}">		
			<c:set value="${ph.pgvo.type}" var="typed" />
			<div class="box">
			<div>
			<select name="type" class="form-select">
				<option ${typed eq null? 'selected':''}>choose...</option>
				<option value='t' ${typed eq 't'? 'selected':''}>title</option>
				<option value='w' ${typed eq 'w'? 'selected':''}>writer</option>
				<option value='c' ${typed eq 'c'? 'selected':''}>content</option>
				<option value='twc' ${typed eq 'twc'? 'selected':''}>all</option>
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
				<th scope="col">BNO</th>
				<th scope="col">WRITER</th>
				<th scope="col">TITLE</th>
				<th scope="col">MOD_DATE</th>
				<th scope="col">READ_CNT</th>
				<th scope="col">COMMENT_CNT</th>
				<th scope="col">FILE_CNT</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="bvo">
				
				<tr>
				<th scope="row"><a href="/board/detail?bno=${bvo.bno}">${bvo.bno}</a></th>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.writer}</a></td>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title}</a></td>
					<td>${bvo.modAt}</td>
					<td>${bvo.readCount}</td>
					<td>${bvo.cmtQty}</td>
					<td>${bvo.hasFile}</td>
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