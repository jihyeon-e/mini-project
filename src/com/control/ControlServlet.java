package com.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
		urlPatterns= {"*.do"},
		initParams= {
				@WebInitParam(name="propertyConfig", value="command.properties")
		})
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map = new HashMap<String, Object>();
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		//web.xml에 있는 command.properties 이게 어디있는지 알아내야 한다.
		String propertyConfig = config.getInitParameter("propertyConfig");
		System.out.println("propertyConfig = " + propertyConfig);
		
		//command.properties 위치 알려주기
		String realFolder = config.getServletContext().getRealPath("/WEB-INF");
		String realPath = realFolder + "/" + propertyConfig;
		System.out.println("realPath = " + realPath);
		
		FileInputStream fin = null;
	    Properties properties = new Properties();
	      
	      try {
	         //fin = new FileInputStream(propertyConfig);
	    	 fin = new FileInputStream(realPath);
	         properties.load(fin);
	         System.out.println("properties = "+properties);
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      }finally{
	         try {
	            fin.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      System.out.println();
	      
	      Iterator it = properties.keySet().iterator();
	      while(it.hasNext()) {
	         String key = (String)it.next();
	         System.out.println("key = "+key);
	         
	         String className = properties.getProperty(key);
	         System.out.println("className = "+className);
	         
	         try {
	            Class<?> classType = Class.forName(className);
	            Object ob = classType.newInstance();
	            
	            System.out.println("ob = "+ob);
	            
	            map.put(key, ob); //Map에 저장
	            
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } catch (InstantiationException e) {
	            e.printStackTrace();
	         } catch (IllegalAccessException e) {
	            e.printStackTrace();
	         } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	         } 
	         
	         System.out.println();
	      }//while
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}//get은 인코딩을 안해도 된다.

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getMethod().equals("POST")) { //요청된 부분에서 getMethod()를 가져와라 if 문 안걸고 전부 걸어도 된다.
			request.setCharacterEncoding("UTF-8");
		}
		
		//요청이 들어왔을 때 - http://localhost:8080/mvcmember/member/writeForm.do
		String category = request.getServletPath();
		System.out.println("category = "+category); //member/writeForm.do를 꺼내와서 해당하는 자바파일을 꺼내와서 요청한다
		
		CommandProcess com = (CommandProcess)map.get(category);
		String view = null;
		
		try {
			view = com.requestPro(request, response);//WriteFormService 자바파일에서 "/member/writeForm.jsp"를 리턴받음
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		//forward
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);//상대번지
		dispatcher.forward(request, response);//제어권 넘기기
	}

}
