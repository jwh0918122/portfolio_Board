<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Modify Page</title>
</head>
<body>
	<jsp:include page="../common/nav.jsp" />

	<form action="/board/modify" method="post" enctype="multipart/form-data">
		<div class="container">
			<div class="mb-3">
				<input type="hidden" name="bno" value="${bdto.bvo.bno}"> <label
					for="w" class="form-label">WRITER</label> <input type="text"
					class="form-control" id="w" name="writer"
					value="${bdto.bvo.writer}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="t" class="form-label">TITLE</label> <input type="text"
					class="form-control" id="t" name="title" value="${bdto.bvo.title}">
			</div>
			<div class="mb-3">
				<label for="c" class="form-label">CONTENT</label>
				<textarea class="form-control" id="c" rows="3" name="content">${bdto.bvo.content}</textarea>
			</div>
			<div class="mb-3">
				 <label for="f" class="form-label">FILE_UPLOAD</label>
  				<input class="form-control" type="file" id="files" name="files" multiple="multiple">
			</div>

			<div class="mb-3">
				<c:forEach items="${bdto.flist}" var="fvo">
					<c:choose>
						<c:when test="${fvo.fileType>0 }">
							<div>
								<img src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_th_${fvo.fileName}">
								<button type="button" class="btn btn-outline-primary fileDelBtn" data-uuid="${fvo.uuid}">X</button>
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
				<button type="submit" class="btn btn-outline-primary">수정 완료</button>
			</div>
		</div>
	</form>
	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript" src="/resources/js/file.js"></script>
<!-- 	<script type="text/javascript" src="/resources/js/fileUpload.js"/> -->
</body>
</html>