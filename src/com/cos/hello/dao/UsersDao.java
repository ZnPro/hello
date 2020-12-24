package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.hello.config.DBconn;
import com.cos.hello.model.Users;

public class UsersDao {
	public int insert(Users user) { //회원가입>insert로 바꾸면 재사용 가능
		//회원가입(String username, String password, String email)으로 받지 말자
		
		System.out.println("==========joinProc Start==========");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println("==========joinProc End==========");
		// 2번 DB연결 해서 3개 값 insert
		StringBuffer sb = new StringBuffer(); //컬렉션으로 버퍼를 담음.(동기화 되어있음. 동시접근x) String 전용
		sb.append("INSERT INTO users(username, password, email) ");
		sb.append("VALUES(?,?,?)");
		String  sql = sb.toString();
//		String  sql = "INSERT INTO users(username, password, email) ";
//		sql += "VALUES(?,?,?)"; // + 쓰면 String construct full되서 안좋음
		Connection conn = DBconn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ?,?,? 설정
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			//insert시키기
			int result = pstmt.executeUpdate(); //변경된 행의 개수 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3번 insert가 정상적으로 됐다면 index.jsp를 응답
		//resp.sendRedirect("index.jsp");
		return -1;
	}
}
