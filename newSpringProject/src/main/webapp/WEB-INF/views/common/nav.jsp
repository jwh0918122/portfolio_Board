<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
          <a class="nav-link" href="/member/register">SIGN_UP</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/login">LOG_IN</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/logout">LOG_OUT</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/list">BOARD_LIST</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/register">WRITE</a>
        </li>	
           
      </ul>
    </div>
  </div>
</nav>
</div>
</div>



</body>
</html>