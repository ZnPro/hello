<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	String contextPath = request.getContextPath();
%>
<title>Insert title here</title>
</head>
<body>
<ul>
	<li><a href = "<%=contextPath %>/user?gubun=login">로그인</a></li>
	<li><a href = "<%=contextPath %>/user?gubun=join">회원가입</a></li>
	<li><a href = "<%=contextPath %>/user?gubun=selectOne">유저정보</a></li>
	<li><a href = "<%=contextPath %>/user?gubun=updateOne">유저수정</a></li>
	<li><a href = "<%=contextPath %>/board?gubun=deleteOne">게시글삭제</a></li>
	<li><a href = "<%=contextPath %>/board?gubun=insertOne">게시글입력</a></li>
	<li><a href = "<%=contextPath %>/board?gubun=selectAll">게시글전체보기</a></li>
	<li><a href = "<%=contextPath %>/board?gubun=updateOne">게시글수정</a></li>
</ul>