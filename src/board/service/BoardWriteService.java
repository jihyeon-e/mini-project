package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardWriteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		/*
		 HttpSession session = request.getSession(); 
		 String id = (String)session.getAttribute("memId"); 
		 String name = (String)session.getAttribute("memName"); 
		 String email = (String)session.getAttribute("memEmail");
		 */
		
		String id = "zzz";
		String name = "지현";
		String email = "zzz@gamil.com";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		map.put("name",name);
		map.put("email",email);
		map.put("subject",subject);
		map.put("content",content);
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardWrite(map);
		
		return "/board/boardWrite.jsp";
	}

}
