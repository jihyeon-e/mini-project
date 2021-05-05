<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#subjectA:link{ color: white; text-decoration: none; }
#subjectA:visited { color: white; text-decoration: none; }
#subjectA:hover { color: purple; text-decoration: underline; }
#subjectA:active { color: white; text-decoration: none; }

#currentPaging {
	color: red;
	text-decoration: underline;
}

#paging {
	color: white;
	text-decoration: none;
}

</style>
</head>
<body style="background: linear-gradient(to right, #654ea3, #eaafc8); color:white;">
	
	<h3 style="text-align: center; width: 650px;">게시판</h3>
	<table border="1" bordercolor="yellow" width="720" cellpadding="5" cellspacing="0" frame="hsides" rules="rows">
	  <tr>
	  	<th align="center" width="70">글번호</th>
	  	<th align="center" width="350">제목</th>
	  	<th align="center" width="100">작성자</th>
	  	<th align="center" width="100">작성일</th>
	  	<th align="center" width="100">조회수</th>
	  </tr>
	  
	  <!-- 목록 -->
	  <%-- <c:if test="${requestScope.list != null }"> --%>
	  <c:if test="${list != null }">
		  <c:forEach var="boardDTO" items="${list}">
			  <tr>
			  	<td align="center" height="20">${boardDTO.seq}</td>
			  	<td>
			  		<a href="javascript:void(0)" id="subjectA" onclick="isLogin('${sessionScope.memId}', ${boardDTO.seq}, ${param.pg})">
			  			${boardDTO.subject}  
			  		</a>
		  			<!-- 링크 눌렀을 때 #이 안나오게 하기 위해 -->
		  		</td>
			  	<td align="center">${boardDTO.id}</td>
			  	<td align="center">
			  		<fmt:formatDate pattern="yyyy.MM.dd" value="${boardDTO.logtime}" /><br>
			  	</td>
			  	<td align="center">${boardDTO.hit}</td>
			  </tr>
		  </c:forEach>
	  </c:if>
	</table>
<br>

<div style="float: left; width: 650px; text-align: center">${boardPaging.pagingHTML}</div>
<script type="text/javascript">
function isLogin(memId, seq, pg){
	if(memId=='null') {
		alert("먼저 로그인하세요");
	} else {
		//location.href="boardView.jsp?seq="+seq+"&pg="+pg;
	}
}
</script>

</body>
</html>