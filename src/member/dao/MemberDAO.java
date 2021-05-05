package member.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.bean.ZipcodeDTO;
import member.bean.MemberDTO;

public class MemberDAO {
	private static MemberDAO instance=null;
	private SqlSessionFactory sqlSessionFactory;

	public static MemberDAO getInstance() {
		if(instance==null) { //instance가 null인 경우는 맨처음 딱 한번뿐임
			synchronized (MemberDAO.class) { //{}구간-동기화:한사람이라도 먼저 접근하면 통과할때까지 아무도 못들어옴
				instance = new MemberDAO();
			}
		}
		return instance;
	} //MemberDAO getInstance()
	
	public MemberDAO() { //드라이버는 딱 한번만 부르면 됨 -> 생성자
		try {
			//InputStream도 되고 Reader도 가능
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} //MemberDAO()
	
//--------------------------------------------------------------------
	
	public int write(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int su = sqlSession.insert("memberSQL.write", memberDTO);
							 	 //Mapper.xml의 namespace 안에 write로 가겠다
		sqlSession.commit();
		sqlSession.close();
		return su;
	}
	
//--------------------------------------------------------------------	
	
	public MemberDTO login(String id, String pwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login", map);
		sqlSession.close();
		return memberDTO;
	}
	
//--------------------------------------------------------------------
	//mybatis는 DB와 연결돼있기때문에 Boolean형으로 꺼내올 수 없음->DTO나 int만 가능
	public Boolean isExistId(String id) {
		Boolean exist = false;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.isExistId", id);
		if(memberDTO != null) exist=true;
		sqlSession.close();
		return exist;
	}
	
//--------------------------------------------------------------------
	
	public List<ZipcodeDTO> getZipcodeList(String sido, String sigungu, String roadname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", sido);
		map.put("sigungu", sigungu);
		map.put("roadname", roadname);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<ZipcodeDTO> list = sqlSession.selectList("memberSQL.getZipcodeList", map);
		sqlSession.close();
		return list;
	}	

//--------------------------------------------------------------------
				
	public MemberDTO getMember(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.getMember", id);
		sqlSession.close();
		return memberDTO;
	}
	
//--------------------------------------------------------------------
	
	public void modify(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("memberSQL.modify",memberDTO);
		sqlSession.commit();
		sqlSession.close();
	}

//--------------------------------------------------------------------	
		
}