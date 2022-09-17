<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<title>마이페이지</title>

<%@ include file="../header.jsp"%>
<!-- Jquery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- CSS -->
<link rel="stylesheet" href="<c:url value='/resources/css/MP2.css'/>">
<!-- uploadAjax JS -->
<script type="text/javascript" src="resources/js/uploadAjax.js"></script>
<head>
</head>
<body>

<!-- 마이페이지 로고 및 개인정보수정 링크 -->
<div class="MP_h1">          
    <h1>My Page</h1>
    <h2>개인정보를 변경해보세요.</h2>
    <img src="${profile.uuid}" style=" max-width: 50%; height: auto;">
    <form action="/profileUpload" method="post" id="form">
    	<div>
    
   		 <input type="file" class="w-btn w-btn-gray" name="uploadFile" accept=".jpg,.jpeg,.png,.gif" multiple><br>
 	     <input type="button" class="w-btn w-btn-gray pro-btn" id="upFileBtn" value="프로필 수정 »">
   		 
   	 	</div>
    	<div id="uploadResult">
    	<ul>
    	
    	</ul>
    	</div>
    </form>
</div>


<div class="userinfo">
		<form action="/mypage2" method="post">
			<div class="user_id">
			<p>아이디 : <input type="text" value="${sessionScope.login.id}" name="id" readonly></p>
			</div>
			<div class="user_name">
			<p>이름 : <input type="text" name="name"></p>
			</div>
			<div class="user_pw">
			<p>비밀번호 : <input type="password" name="password"></p>
			</div>
			<div class="user_pw2"> <!-- 비밀번호와 동일해야함 -->
			<p>2차 비밀번호 : <input type="password"></p>
			</div>
			<div class="user_email">
			<p>E-mail : <input type="text" name="email"></p>
			</div>
	<!-- 		<input type="submit" value="수정 완료"> -->
			<input type="submit" class="w-btn w-btn-gray" id="uploadBtn" value="수정 완료 »">
		</form>
	</div> <!-- userinfo 끝 -->

<form method="post">
<input type="hidden" value="${sessionScope.login.id}" name="id">
<p><input type=submit class="" value="회원탈퇴 »" formaction="/memberDelete"></p>
</form>

<!-- footer -->
  <div class="footer">
    <div class="footer-content">
      greenstudy.220608.team01@cod1ng
    </div>
    
    <div class="team-email">
      <p>lee jeaseo : ljseo0111@naver.com</p>
      <p></p>
      <p></p>
      <p></p>
    </div>
  </div>


</body>
</html>