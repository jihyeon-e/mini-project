function checkWrite(){
	document.getElementById("nameDiv").innerText = "";
	document.getElementById("idDiv").innerText = "";
	document.getElementById("pwdDiv").innerText = "";
	document.getElementById("repwdDiv").innerText = "";
	
	if(document.writeForm.name.value=="") 
		document.getElementById("nameDiv").innerText = "이름을 입력하세요";
	else if(document.writeForm.id.value=="")
		document.getElementById("idDiv").innerText = "아이디를 입력하세요";
	else if(document.writeForm.pwd.value=="")
		document.getElementById("pwdDiv").innerText = "비밀번호를 입력하세요";	
	else if(document.writeForm.pwd.value != document.writeForm.repwd.value)
		document.getElementById("repwdDiv").innerText = "비밀번호가 일치하지 않습니다";	
	/* else if(document.writeForm.check.value == "")
		document.getElementById("idDiv").innerText = "중복체크 하세요";*/
	else if(document.writeForm.check.value != document.writeForm.check.value)
		document.getElementById("idDiv").innerText = "중복체크 하세요";
	else
		document.writeForm.submit();			
}

function checkId(){
	document.getElementById("idDiv").innerText = "";
	var id = document.writeForm.id.value;
	
	if(id=="") 
		//alert("먼저 아이디를 입력하세요");
		document.getElementById("idDiv").innerText = "먼저 아이디를 입력하세요";
	else
		window.open("/miniProject/member/checkId.do?id="+id, "checkId", "width=450 height=150 left=800 top=200");
}

function checkPost() {
    window.open("/miniProject/member/checkPost.do", "checkPost", "width=500 height=500 left=800 top=200 scrollbars=yes");
}

function checkLogin(){
	document.getElementById("idDiv").innerText="";
	document.getElementById("pwdDiv").innerText="";
	   
	if(document.loginForm.id.value==""){
		document.getElementById("idDiv").innerHTML="<font color='red' size='2'><strong>아이디를 입력하세요</strong></font>";
	
	}else if(document.loginForm.pwd.value==""){
		document.getElementById("pwdDiv").innerHTML="<font color='red' size='2'><strong>비밀번호 입력하세요</strong></font>";
	
	}else{
		document.loginForm.submit(); //submit하면 action을 찾아감!!
	}    
}


    