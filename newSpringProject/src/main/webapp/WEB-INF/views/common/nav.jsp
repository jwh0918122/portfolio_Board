<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nav</title>
<style type="text/css">
h1{
text-align: center;
}
.navContainer{
display: flex;
justify-content: center;
margin-bottom: 10px;
}
.container{
display: flex;
justify-content: center;
flex-direction: column;
}
.btnContainer{
display: flex;
justify-content: center;
}
</style>
</head>
<body>

<div class="navContainer">
<div>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      
        <li class="nav-item">
          <a class="nav-link" href="/">HOME</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/list">BOARD_LIST</a>
        </li>
        
        
        <sec:authorize access="isAuthenticated()">
       
        <sec:authentication property="principal.mvo.email" var="authEmail"/>
        <sec:authentication property="principal.mvo.nickName" var="authNick"/>
        <sec:authentication property="principal.mvo.authList" var="auths"/>
        
        	<c:choose>
        		
        		<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
        		 <li class="nav-item">
         			 <a class="nav-link" href="/member/list">관리자>${authNick}님(${authEmail})</a>
       			 </li>
        		</c:when>
        		
        		<c:otherwise>
        		<li class="nav-item">
         			 <a class="nav-link" href="/member/detail?email=${authEmail}">${authNick}님(${authEmail})</a>
       			 </li>
        		</c:otherwise>
        	
        	</c:choose>
        		
        		<li class="nav-item">
         		 <a class="nav-link" href="/board/register">WRITE</a>
        		</li>
        
        
        		<li class="nav-item">
          		 <a class="nav-link" href="/member/logout" id="logoutLink">LOG_OUT</a>
        		</li>
        		<form action="member/logout" method="post" id="logoutForm">
        		<input type="hidden" name="email" value="${authEmail}">
        		</form>
        		
        </sec:authorize>
        
        <sec:authorize access="isAnonymous()">       
        <li class="nav-item">
          <a class="nav-link" href="/member/register">SIGN_UP</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/login">LOG_IN</a>
        </li>
        </sec:authorize>  
      </ul>
    </div>
  </div>
</nav>
</div>
</div>
<script type="text/javascript">
document.getElementById('logoutLink').addEventListener('click',(e)=>{
	e.preventDefault();
	document.getElementById('logoutForm').submit();
})
</script>


</body>
</html>