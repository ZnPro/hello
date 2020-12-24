package com.cos.hello.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
// javax로 시작하는 패키지는 톰켓이 들고 있는라이브러리
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.config.DBconn;
import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;

// 디스패쳐 역할 =분기 =필요한 view를 응답해주는 것
public class UserController extends HttpServlet {
	// req와 res는 클라이언트의 요청이 있을 때 마다 톰켓이 만들어줌
	// req는 BufferedReader할 수 있는 ByteStream
	// res는 BufferedWriter할 수 있는 ByreStream
	
	//http://localhost:8000/hello/user?gubun=login
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get: 브라우저에 주소 적고 엔터 = select
		System.out.println("comment get 요청");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post: form태그 만들고 요청 = insert, delete, update
		System.out.println("comment post 요청");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("FrontController 실행됨");
		
		String gubun = req.getParameter("gubun");
		System.out.println(gubun);
		route(gubun, req, resp);  //이거 하기 !
//		
//		if(gubun.equals("login")) {
//			resp.sendRedirect("auth/login.jsp"); //한번 더 req
//		} else if(gubun.equals("join")) {
//			resp.sendRedirect("auth/join.jsp"); //한번 더 req
//		} else if(gubun.equals("selectOne")) {
//			resp.sendRedirect("user/selectOne.jsp"); //한번 더 req
//		} else if(gubun.equals("updateOne")) {
//			resp.sendRedirect("user/updateOne.jsp"); //한번 더 req
//		} else if(gubun.equals("joinProc")) { //회원가입을 수행 해줘
//			//데이터 원형 username=ssar&password=1234&email=ssar@nate.com 이 날아옴
//			
//			//1번 form의 input태그에 있는 3가지 값 username, password, email 받기
//			//req.getParameter 함수는 get, ost방식의 데이터를 모두 받을 수 있음
//			//단 post방식에서는 데이터 타입이 x-www-form-urlencoded방식만 받을 수 있음
//			String username = req.getParameter("username");
//			String password = req.getParameter("password");
//			String email = req.getParameter("email");
//			
//			System.out.println("==========joinProc Start==========");
//			System.out.println(username);
//			System.out.println(password);
//			System.out.println(email);
//			System.out.println("==========joinProc End==========");
//			// 2번 DB연결 해서 3개 값 insert
//			//생략
//			// 3번 insert가 정상적으로 됐다면 index.jsp를 응답
//			resp.sendRedirect("index.jsp");
//		}
		
	}
	private void route(String gubun, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(gubun.equals("login")) { //에러 뜨면 throw하기
			resp.sendRedirect("auth/login.jsp"); //한번 더 req
		} else if(gubun.equals("join")) {
			resp.sendRedirect("auth/join.jsp"); //한번 더 req
		} else if(gubun.equals("selectOne")) { //유저 정보 보기
			//인증이 필요한 페이지
			String result;
			HttpSession session = req.getSession();
			if(session.getAttribute("sessionUser") != null) { //인증끝
				Users user = (Users)session.getAttribute("sessionUser"); 
				//숫자인지 문자인지 모르기 때문에 Object로 return 하고 다운캐스팅
				result = "인증되었습니다.";
				System.out.println(user);
			} else {
				result = "인증되지 않았습니다.";
			}//resp.sendRedirect("user/selectOne.jsp"); //한번 더 req
			//request 유지하는 코드
			req.setAttribute("result", result);
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
		} else if(gubun.equals("updateOne")) {
			resp.sendRedirect("user/updateOne.jsp"); //한번 더 req
		} else if(gubun.equals("joinProc")) { //회원가입을 수행 해줘
			//데이터 원형 username=ssar&password=1234&email=ssar@nate.com 이 날아옴
			
			//1번 form의 input태그에 있는 3가지 값 username, password, email 받기
			//req.getParameter 함수는 get, ost방식의 데이터를 모두 받을 수 있음
			//단 post방식에서는 데이터 타입이 x-www-form-urlencoded방식만 받을 수 있음
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			
			Users user = Users.builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
			
			UsersDao usersDao = new UsersDao(); //싱글톤 getinstance방식으로
			int result = usersDao.insert(user);
			if(result ==1) { //분기 역할
				// 3번 insert가 정상적으로 됐다면 index.jsp를 응답
				resp.sendRedirect("auth/login.jsp");
		} else{
			resp.sendRedirect("auth/login.jsp");
		}
			
//			System.out.println("==========joinProc Start==========");
//			System.out.println(username);
//			System.out.println(password);
//			System.out.println(email);
//			System.out.println("==========joinProc End==========");
//			// 2번 DB연결 해서 3개 값 insert
//			StringBuffer sb = new StringBuffer(); //컬렉션으로 버퍼를 담음.(동기화 되어있음. 동시접근x) String 전용
//			sb.append("INSERT INTO users(username, password, email) ");
//			sb.append("VALUES(?,?,?)");
//			String  sql = sb.toString();
////			String  sql = "INSERT INTO users(username, password, email) ";
////			sql += "VALUES(?,?,?)"; // + 쓰면 String construct full되서 안좋음
//			Connection conn = DBconn.getInstance();
//			try {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				// ?,?,? 설정
//				pstmt.setString(1, username);
//				pstmt.setString(2, password);
//				pstmt.setString(3, email);
//				//insert시키기
//				int result = pstmt.executeUpdate(); //변경된 행의 개수 리턴
//				if(result ==1) {
//					// 3번 insert가 정상적으로 됐다면 index.jsp를 응답
//					resp.sendRedirect("auth/login.jsp");
//			} else{
//				resp.sendRedirect("auth/login.jsp");
//			}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			// 3번 insert가 정상적으로 됐다면 index.jsp를 응답
//			//resp.sendRedirect("index.jsp");
		} else if (gubun.equals("loginProc")) {
			// 1번 전달되는 값 받기
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			System.out.println("==========Login Start==========");
			System.out.println(username);
			System.out.println(password);
			System.out.println("==========Login End==========");
			// 2번 DB값이 있는 select 해서 확인
			// 생략
			Users user = Users.builder()
					.id(1)
					.username(username)
					.build();
			// 3번 stateless를 statefull로
			HttpSession session = req.getSession();
			//session에는 사용자 패스워드 절대 넣지 않기
			session.setAttribute("sessionUser",user); //해쉬맵. 키밸류
			// 모든 응답에는 jSessionId가 쿠키로 추가된다.
			
			// 4번  index.jsp이동
			//resp.setHeader("cookie", "9998");//헤더에 담기
			//응답
			resp.sendRedirect("index.jsp"); //body에 담김
		}
	}
}