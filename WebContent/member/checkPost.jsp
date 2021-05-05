<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
td {
	font-size: 9pt;
}
#addressA:link {color: black; text-decoration: none;}
#addressA:visited {color: black; text-decoration: none;}
#addressA:hover {color: red; text-decoration: underline; font-weight: bold;}
#addressA:active {color: black; text-decoration: none;}
</style>
</head>
<body>
<form name="checkPost" method="post" action="checkPost.do"> <!-- 무조건 자바를 거쳐야 CheckPostService를 처리함 -->
<table border="1" cellpadding="5" cellspacing="0">
	<tr>
		<td align="center" width="100">시도</td>
		<td>
			<select name="sido">
			<option value="">시도선택</option>
			<option value="서울">서울</option>
            <option value="인천">인천</option>
            <option value="대전">대전</option>
            <option value="대구">대구</option>
            <option value="울산">울산</option>
            <option value="세종">세종</option>
            <option value="광주">광주</option>
            <option value="경기">경기</option>
            <option value="강원">강원</option>
            <option value="전남">전남</option>
            <option value="경남">경남</option>
            <option value="경북">경북</option>
            <option value="충남">충남</option>
            <option value="충북">충북</option>
            <option value="부산">부산</option>
            <option value="제주">제주</option>
			</select>
		</td>
		<td width="100" align="center">시.군.구</td>
		<td>
			<input type="text" name="sigungu" size="10">
		</td>
	</tr>
	
	<tr>
		<td align="center">도로명</td>
		<td colspan="3">
			<input type="text" name="roadname">
 			<input type="submit" value="검색">
 		</td>
	</tr>
	
	<tr>
		<td align="center" style="font-size: 8pt;">우편번호</td>
		<td colspan="3" align="center" style="font-size: 8pt;">주소</td>
	</tr>
	
	<%-- <c:if test="조건"></c:if> //else if 가 없어서 if 조건을 다시 해야한다. --%>
	<c:if test="${requestScope.list != null }" >	 <!-- requestScope는 안써도 됨 -->
		<c:forEach var="zipcodeDTO" items="${list}">	 <!-- 자바는 자료형이 있지만 웹은 자료형이 없음 -->
			
				<!-- =zipcodeDTO.getSido() 함수명을 변수명처럼 쓸 수 있음, 옆으로 붙여야 읽음, 괄호 안을 엔터치면 읽을 수 있음-->
			<c:set var="address">
				${zipcodeDTO.sido
			  } ${zipcodeDTO.sigungu
			  } ${zipcodeDTO.yubmyundong
			  } ${zipcodeDTO.ri
			  } ${zipcodeDTO.roadname
			  } ${zipcodeDTO.buildingname}
			</c:set>

			<tr>
				<td align="center">${zipcodeDTO.zipcode}</td>
				<td colspan="3">
					<a href="#" id="addressA" onclick="checkPostClose('${zipcodeDTO.zipcode }','${address}')">${address}</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>

</table>
</form>
<script type="text/javascript">
function checkPostClose(zipcode, address){
	/* opener.writeForm.zipcode.value = zipcode;
	opener.writeForm.addr1.value = address;
	window.close();
	opener.writeForm.addr2.focus(); */
	//----------------
	/* opener.document.getElementById("zipcode").value = zipcode;
	opener.document.getElementById("addr1").value = address;
	window.close();
	opener.document.getElementById("addr2").focus(); */
	//----------------
	opener.document.forms[0].zipcode.value = zipcode;
	opener.document.forms[0].addr1.value = address;
	window.close();
	opener.document.forms[0].addr2.focus();
}
</script>
</body>
</html>

