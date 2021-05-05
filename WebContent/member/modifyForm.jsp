<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div#nameDiv, div#pwdDiv, div#repwdDiv {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>

<body>
<h3>회원정보수정</h3>
<form name="modifyForm" method="post" action="modify.do">
		
 <table border="1" cellpadding="5" cellspacing="0">
 <!-- border는 선 갯수, cellpadding은 간격, cellspacing은 선과 선사이의 간격 -->
 
  <tr>
   <td align="center" width="100">이름</td>
   <td>
    <input type="text" name="name" value = "${memberDTO.name}">
    <div id="nameDiv"></div>
   </td>
  </tr>
 
  <tr>
   <td align="center">아이디</td>
   <td>
   <% %>
    <input type="text" name="id" value = "${memberDTO.id}" readonly>
    <input type="button"  id='btn1' value='중복체크' disabled='true'
                                    onclick="setDisable('btn1')"/><br>
   </td>
  </tr>
  
  <tr>
   <td align="center">비밀번호</td>
   <td>
    <input type="password" name="pwd">
    <div id="pwdDiv"></div>
   </td>
  </tr>
 
  <tr>
   <td align="center">재확인</td>
   <td>
    <input type="password" name="repwd">
    <div id="repwdDiv"></div>
   </td>
  </tr>
 
  <tr>
   <td align="center">성별</td>
   <td>
    <input type="radio" name="gender" value="0" checked="checked">남
    <input type="radio" name="gender" value="1">여
   </td>
  </tr>
 
  <tr>
   <td align="center">이메일</td>
  	  	<td>
  	  		<input type="text" name="email1" value="${memberDTO.email1}">@
  	  		<input type="email" list="email2" name="email2" value="${memberDTO.email2}">
	 		<datalist id="email2">
	 			<option value="gmail.com">
	 			<option value="hanmail.net">
	 			<option value="naver.com">
	 		</datalist>
  	  	</td>
  	  </tr>
  	  
  	  
  <tr>
 	<td align="center">핸드폰</td>
 	<td>
 		<select name="tel1" id="tel1" style="width:70px;">
 			<option value="010">010</option>
 			<option value="011">011</option>
 			<option value="019">019</option>
 		</select>-
 		<input type="text" name="tel2" size="5" value="${memberDTO.tel2}">-
 		<input type="text" name="tel3" size="5" value="${memberDTO.tel3}">
 	</td>
 </tr>
 
 
 <tr>
 	<td align="center">주소</td>
 	<td>
 		<input type="text" name="zipcode" id="zipcode" value="${memberDTO.zipcode}" readonly>
    <input type="button" value="우편번호검색" onclick="checkPost()"><br>
    <input type="text" name="addr1" id="addr1" placeholder="주소" size="50" value="${memberDTO.addr1}" readonly><br>
    <input type="text" name="addr2" id="addr2" placeholder="상세주소" value="${memberDTO.addr2}" size="50">
 	</td>
 </tr>
 
 
 <tr>
 	<td colspan="2" align="center">
 		<input type="button" value="회원정보수정" onclick="javascript:checkModify()">
 		<input type="reset" value="다시작성" onclick="location.reload()">
 	</td>
 </tr>
</table>
 </form>
 <script type="text/javascript" src="../js/member.js"></script>
<script type="text/javascript">
window.onload = function() {
	//document.modifyForm.gender[1].checked = true; // 0은 남자 1은여자로 들어감
	document.modifyForm.gender["${memberDTO.gender}"].checked = true;
	document.modifyForm.email2.value = "${memberDTO.email2}";
	document.modifyForm.tel1.value = "${memberDTO.tel1}";
}

</script>

 <script type="text/javascript">
 function checkModify(){
	document.getElementById("nameDiv").innerText = "";
	document.getElementById("pwdDiv").innerText = "";
	document.getElementById("repwdDiv").innerText = "";
	
	if(document.modifyForm.name.value=="") 
		document.getElementById("nameDiv").innerText = "이름을 입력하세요";
	else if(document.modifyForm.pwd.value=="")
		document.getElementById("pwdDiv").innerText = "비밀번호를 입력하세요";	
	else if(document.modifyForm.pwd.value != document.modifyForm.repwd.value)
		document.getElementById("repwdDiv").innerText = "비밀번호가 일치하지 않습니다";
	else
		document.modifyForm.submit();
 }
 </script>
</body>
</html>