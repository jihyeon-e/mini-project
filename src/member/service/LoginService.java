package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberDTO memberDTO = memberDAO.login(id, pwd);
		
		HttpSession session = request.getSession();
		
		//응답
		if (memberDTO!=null) {
			//세션생성 - jsp는 세션이 내장객체로 가지고있었지만 이번에는 생성해야 함
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			
			request.setAttribute("display", "/member/loginOk.jsp");
			return "../index.jsp";
		}else {
			request.setAttribute("display", "/member/loginFail.jsp");
			return "../index.jsp"; //sendRedirect 로 직접 이동하면 안됨-> return시켜줘야 fowarding함
		}
	}

}
