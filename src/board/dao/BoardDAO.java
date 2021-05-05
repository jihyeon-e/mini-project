package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.bean.BoardDTO;

public class BoardDAO {
	private static BoardDAO instance=null;
	private SqlSessionFactory sqlSessionFactory;

	public static BoardDAO getInstance() {
		if(instance==null) { //instance가 null인 경우는 맨처음 딱 한번뿐임
			synchronized (BoardDAO.class) { //{}구간-동기화:한사람이라도 먼저 접근하면 통과할때까지 아무도 못들어옴
				instance = new BoardDAO();
			}
		}
		return instance;
	} //BoardDAO getInstance()
	
	public BoardDAO() { //드라이버는 딱 한번만 부르면 됨 -> 생성자
		try {
			//InputStream도 되고 Reader도 가능
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} //BoardDAO()
	
//--------------------------------------------------------------------
	
	public void boardWrite(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("boardSQL.boardWrite", map);
		sqlSession.commit();
		sqlSession.close();

	} //writeBoard(BoardDTO boardDTO)
	
//---------------------------------------------------------------
	
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.getBoardList", map);
		sqlSession.close();
		return list;
	}

//---------------------------------------------------------------

	public BoardDTO getBoard(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.getBoard", seq);
		sqlSession.close();
		return boardDTO;
	}
	
//---------------------------------------------------------------
	
	public int getTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int totalA = sqlSession.selectOne("boardSQL.getTotalA");
		sqlSession.close();
		return totalA;
	}

//---------------------------------------------------------------
	
	public void boardModify(int seq, String subject, String content) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setSeq(seq);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.boardModify", boardDTO);
		sqlSession.commit();
		sqlSession.close();
	}
	
//---------------------------------------------------------------
	
	public void addHit(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.addHit", seq);
		sqlSession.commit();
		sqlSession.close();
	}
	
//---------------------------------------------------------------

}