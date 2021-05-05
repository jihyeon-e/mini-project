package member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.ZipcodeDTO;
import member.dao.MemberDAO;

public class CheckPostService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//데이터받아올때 한글처리안해도 됨->ControlServlet에서 이미 한글처리했기 때문
		//데이터
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String roadname = request.getParameter("roadname");
		
		System.out.println(sido+","+sigungu+","+roadname);
		
		//DB
		List<ZipcodeDTO> list = null;
		if(sido!=null && roadname!=null){ //우편번호검색 버튼을 눌렀을 때 데이터 없으면 검색하지 마라
			if(sido!="" && roadname!=""){ //데이터를 입력 안하고 검색 버튼을 눌렀을 때
			MemberDAO memberDAO = MemberDAO.getInstance();
			list = memberDAO.getZipcodeList(sido, sigungu, roadname);
			}
		}
		
		//응답
		request.setAttribute("list", list);
		return "/member/checkPost.jsp";

	}

}
