package com.cos.hello.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardController extends HttpServlet{
	//http://localhost:8000/hello/board?gubun=deleteOne
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
		System.out.println("FrontController 실행됨");
		String gubun = req.getParameter("gubun");
		System.out.println(gubun);
		
		if(gubun.equals("deleteOne")) {
			resp.sendRedirect("board/deleteOne.jsp"); //한번 더 req
		} else if(gubun.equals("insertOne")) {
			resp.sendRedirect("board/insertOne.jsp"); //한번 더 req
		} else if(gubun.equals("selectAll")) {
			resp.sendRedirect("board/selectAll.jsp"); //한번 더 req
		} else if(gubun.equals("updateOne")) {
			resp.sendRedirect("board/updateOne.jsp"); //한번 더 req
		}
	}
}