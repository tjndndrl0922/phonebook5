<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>서웅기 전화번호 리스트</h1>

	<p>
		수정 화면 입니다.
		아래 항목을 수정하고 "수정" 버튼을 클릭하세요
	</p>
	
	<form action="${pageContext.request.contextPath }/phone/modify" method="get">
		이름(name): <input type="text" name="name" value="${personVo.name }"><br>
		핸드폰(hp): <input type="text" name="hp" value="${personVo.hp }"><br>
		회사(company): <input type="text" name="company" value="${personVo.company }"><br>
		<button type="submit">수정</button><br>
		<input type="hidden" name="person_id" value="${personVo.person_id }"><br>
	</form>
	
	<br>
	<a href="${pageContext.request.contextPath }/phone/list">리스트 바로 가기</a>
	
</body>
</html>