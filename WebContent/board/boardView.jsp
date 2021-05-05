<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="board.bean.BoardDTO"%>
<%@page import="board.dao.BoardDAO"%>


<%
request.setCharacterEncoding("UTF-8");
//데이터 받기
int seq = Integer.parseInt(request.getParameter("seq"));
int pg = Integer.parseInt(request.getParameter("pg"));

BoardDAO boardDAO = BoardDAO.getInstance();

//조회수 새로고침 방지
Cookie[] ar = request.getCookies(); //페이지의 모든 쿠키를 다 가져옴
if(ar != null) {
	for(int i=0; i<ar.length; i++) {
		if(ar[i].getName().equals("memHit")){
			boardDAO.addHit(seq); //조회수 증가
			ar[i].setMaxAge(0); //쿠키 삭제
			response.addCookie(ar[i]);
		}
	} //for
} //if

//1사람의 글을 가져오기
BoardDTO boardDTO = boardDAO.getBoard(seq);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
<form name="boardViewForm" >
<input type="hidden" name="seq" value="<%=seq%>">
<input type="hidden" name="pg" value="<%=pg%>">

	<table border="1" width="450" cellpadding="5" cellspacing="0" frame="hsides" rules="rows">
	  <tr>
	  	<td width="100" colspan="3"><%=boardDTO.getSubject()%></td>
  	  </tr>
  	  
  	  <tr>
	  	<td width="150">글번호 : <%=seq%></td>
	  	<td width="150">작성자 : <%=boardDTO.getId()%></td>
	  	<td width="150">조회수 : <%=boardDTO.getHit()%></td>
  	  </tr>
  	  
  	  <tr>
	  	<td width="100" height="300" colspan="3" valign="top" style="white-space: pre-line; word-break: break-all;">
	  		<%=boardDTO.getContent()%>
  		</td>
  	  </tr>
	
	</table>

	<br>
	<input type="button" value="목록" onClick="location.href='boardList.jsp?pg=<%=pg%>'">
<%if(session.getAttribute("memId").equals(boardDTO.getId())) { %>
	<input type="button" value="글수정" onClick="mode(1)">
	<input type="button" value="글삭제" onClick="mode(2)">
<%} %>	
	<input type="button" value="답글" onClick="mode(3)">	
</form>
<script type="text/javascript">
function mode(num){
	if(num==1){
		document.boardViewForm.method="post";
		document.boardViewForm.action="boardModifyForm.jsp"
		document.boardViewForm.submit();
	}else if(num==2){
		confirm("정말로 삭제하시겠습니까?");
		
	}else if(num==3){
		
	}
}
</script>
</body>
</html>