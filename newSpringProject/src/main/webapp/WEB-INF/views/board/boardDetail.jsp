<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

.cmtBtn {
	margin-left: 10px;
}
.accordion-item {
	display: flex;
	width: 900px;
	flex-direction: column;
}
#collapseOne{
	display: flex;
	flex-direction: row;
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
				class="form-control" id="w" value="${bvo.writer}"
				readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="t" class="form-label">TITLE</label> <input type="text"
				class="form-control" id="t" value="${bvo.title}" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="c" class="form-label">CONTENT</label>
			<textarea class="form-control" id="c" rows="3" readonly="readonly">${bvo.content}</textarea>
		</div>
		<div class="mb-3">
			<label for="f" class="form-label">FILE_UPLOAD</label> <input
				class="form-control" type="file" id="f">
		</div>
		<div class="btnContainer">
			<a href="/board/modify?bno=${bvo.bno}"><button type="submit"
					class="btn btn-outline-primary detailBtn">수정</button></a> <a
				href="/board/remove?bno=${bvo.bno}"><button type="button"
					class="btn btn-outline-primary detailBtn">삭제</button></a> <a href="/"><button
					type="button" class="btn btn-outline-primary detailBtn"
					type="button">HOME</button></a>
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
	<!-- 댓글 입력란 끝 -->

	<!-- 댓글 출력란 -->
	<div class="box">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<div id="collapseOne" class="accordion-collapse collapse show"
					data-bs-parent="#accordionExample">
					<div>
					사람 이모티콘, 닉네임(writer...?), regDate
					</div>
					<div class="btnBox">
						<button type="button" class="btn btn-outline-primary detailBtn2">수정</button>
						<button type="button" class="btn btn-outline-primary detailBtn2">삭제</button>
					</div>
				</div>
				<div id="collapseOne" class="accordion-collapse collapse show"
					data-bs-parent="#accordionExample">
					<input type="text">
				</div>
			</div>
		</div>
	</div>

	<div>
		<button type="button" id="moreBtn" data-page="1"
			class="btn btn-outline-dark" style="visibility: hidden">+MORE</button>
	</div>


	<!-- 댓글 출력란 끝-->

	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript">
		let bnoVal = `<c:out value="${bvo.bno}"/>`;
		console.log("bnoVal>>" + bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/comment.js"></script>
	<script type="text/javascript">
		commentListPrint(bnoVal);
	</script>
</body>
</html>