<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
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
#b1{
width: 100%
}
.custom1{
display: flex;
  
}

.bb{
	
margin-left: 700px;
}
#logo{
width: 50px;
left: 0px;

}
.container-fluid{
height: 50px;
}
.navContainer{
margin-bottom: 50px;
}
</style>
</head>
<body>

<div class="navContainer">
<div id="b1">
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <div class="collapse navbar-collapse custom1" id="navbarSupportedContent">
     <div>
     	<img src="/resources/img/logo.png" id="logo">
     </div>
     
     <div class="bb">
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
        		<form action="/member/logout" method="post" id="logoutForm">
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