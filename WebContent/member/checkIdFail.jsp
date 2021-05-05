<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="" method="" action="checkId.do">
<%-- <%=request.getParameter("id") %>는 중복된 아이디 입니다.<br><br>
el로 바꾸기 --%>

${ requestScope.id}는 중복된 아이디 입니다.<br><br>

아이디 <input type="text" name="id"> 
<input type="submit" value="중복체크">
</form>

</body>
</html>