package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class CheckIdService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String id = request.getParameter("id");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		boolean exist = memberDAO.isExistId(id); //만약 id가 있으면 true(사용 불가능), 없으면 false(사용 가능)로 가져온다

		//응답
		request.setAttribute("id", id); //request에 데이터 싣기
		
		if(exist) {
			return "/member/checkIdFail.jsp"; //사용 불가능
		} else {
			return "/member/checkIdOk.jsp";	//사용 가능
		}
	}

}
