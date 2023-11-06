<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail Page</title>
<style type="text/css">
.box {
	display: flex;
	justify-content: center;
}

.detailBtn {
	margin: 10px;
}

.modBtn, .delBtn {
	margin-left: 10px;
}

#cmtDiv {
	display: flex;
	justify-content: center;
}

#cmtListContainer {
	width: 700px;
}

.div1 {
	display: flex;
	justify-content: space-between;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<div class="container">
		<div class="mb-3">
			<label for="w" class="form-label">WRITER</label> <input type="text"
				class="form-control" id="w" value="${bdto.bvo.writer}"
				readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="t" class="form-label">TITLE</label> <input type="text"
				class="form-control" id="t" value="${bdto.bvo.title}"
				readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="c" class="form-label">CONTENT</label>
			<textarea class="form-control" id="c" rows="3" readonly="readonly">${bdto.bvo.content}</textarea>
		</div>
		<div class="mb-3">
			<c:forEach items="${bdto.flist}" var="fvo">
				<c:choose>
					<c:when test="${fvo.fileType>0 }">
						<div>
							<img
								src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_th_${fvo.fileName}">
						</div>
					</c:when>
					<c:otherwise>
					이미지 없음 이모티콘
					</c:otherwise>
				</c:choose>
			 file_name : ${fvo.fileName} 
			 rag_date :  ${fvo.regAt}
			 file_size : ${fvo.fileSize}
				
			</c:forEach>
		</div>

		<div class="btnContainer">
			<a href="/board/modify?bno=${bdto.bvo.bno}"><button type="submit"
					class="btn btn-outline-primary detailBtn">수정</button></a> <a
				href="/board/remove?bno=${bdto.bvo.bno}"><button type="button"
					class="btn btn-outline-primary detailBtn">삭제</button></a> <a
				href="/board/list"><button type="button"
					class="btn btn-outline-primary detailBtn" type="button">LIST</button></a>
		</div>
	</div>
	<hr>
	<!-- 댓글 입력란 -->
	<div class="box">
		<div class="col-auto" style="width: 500px">
			<input type="text" class="form-control" placeholder="comment"
				id="cmtText">
		</div>
		<div class="col-auto">
			<button type="button" class="btn btn-primary mb-3 cmtBtn"
				id="cmtPostBtn">등록</button>
		</div>
	</div>

	<!-- 댓글 출력란 -->
	<div id="cmtDiv">	
		<ul class="list-group" id="cmtListContainer">
		</ul>
	</div>
	
	<!-- moreBtn -->
	<div class="box">	
			<button type="button" id="moreBtn" data-page="1"
				class="btn btn-outline-dark" style="visibility: hidden">+MORE</button>
	</div>
	

	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript">
		let bnoVal = `<c:out value="${bdto.bvo.bno}"/>`;
		console.log("bnoVal>>" + bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/comment.js"></script>
	<script type="text/javascript">
		commentListPrint(bnoVal);
	</script>
</body>
</html>