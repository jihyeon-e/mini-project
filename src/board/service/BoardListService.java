package board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class BoardListService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		//데이터
		int pg = Integer.parseInt(request.getParameter("pg"));

		//1페이지당 5개씩
		int endNum = pg*5;
		int startNum = endNum-4;
		
		Map<String, Integer> map = new HashMap();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		//DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		List<BoardDTO> list = boardDAO.getBoardList(map);

		//페이징 처리
		int totalA = boardDAO.getTotalA();

		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML(); //이전 다음 123 만들어줌

		//조회수 새로고침 방지
		if(session.getAttribute("memId") != null) {
			Cookie cookie = new Cookie("memHit", "0"); //쿠키 생성
			cookie.setMaxAge(30*60);
			response.addCookie(cookie); //클라이언트에게 보내기
		}
		
		//응답
		//request.setAttribute("pg", pg); 현재페이지-페이징처리 안했을 경우
		request.setAttribute("list", list);
		request.setAttribute("boardPaging", boardPaging);
		request.setAttribute("display", "/board/boardList.jsp");
		return "/index.jsp";
	}

}
