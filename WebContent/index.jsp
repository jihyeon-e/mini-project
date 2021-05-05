<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
   margin: 0;
   padding: 0;
      height: 100%;
}

#header {
   width: 100%;
   height: 10%;
   text-align: center;
   border: 1px solid lightgray;
}

#container {
   width: 100%;
   height: 500px;
   border: 1px solid lightgray;
}

#container:after {
   content: '';
   display: block;
   clear: both;
   float: none;
}

#nav{
   width: 20%;
   height: 100%;
   float: left;
   border: 1px solid red;
}

#section{
   width: 79%; 
   height: 100%;
   float: left;
   border: 1px solid blue;
}

#footer{
   width: 100%;
   height: 10%;
   border: 1px solid lightgray;
}


</style>
</head>
<body>
<div id="header">
   <h1>
   <img style="cursor: pointer;" src="/miniProject/img/59.jpg" width="70" height="70" alt="하트오구" onclick="location.href='/miniProject/index.jsp'" >
       MVC 기반의 미니 프로젝트
   </h1>
</div>

<div id="container">
   <div id="nav">
      <jsp:include page="/main/menu.jsp" />
   </div>
   <div id="section">
   <c:if test="${empty display }">
         <h1>
          홈페이지를 방문해주셔서 감사합니다.<br>
         <img src="/miniProject/img/2.jpg" width="650" height="410" alt="카카오프렌즈">
         
      </h1>
   </c:if>
   <c:if test="${not empty display }">
      
   </c:if>
      <c:if test="${not empty display }">
         <jsp:include page="${display }" />
      </c:if>
      <!-- display 값이 들어올 때가 있고 없을 때가 있다. -->
   </div>
</div>

<div id="footer">
   <p>비트캠프</p>
</div>
</body>
</html>