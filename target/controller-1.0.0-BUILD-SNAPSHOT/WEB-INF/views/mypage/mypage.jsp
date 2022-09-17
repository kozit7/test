<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<title>마이페이지</title>

<%@ include file="../header.jsp"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/MP1.css'/>">
<head>
</head>
<body>

<!-- 마이페이지 로고 및 개인정보수정 링크 -->
<div class="MP_h1">          
    <h1>My Page</h1>
    <p>환영합니다 !</p>
    <p><a href="/mypage2" role="button" class="w-btn w-btn-gray">개인정보 수정 »</a></p>
</div>

<div class="container1" align="center" style="overflow: hidden">

<div class="WB">
					<h3>댓글 단 게시물</h3>
					<p><a class="more_btn" href="#" role="button">더보기 »</a></p>
		<!-- 작성한 게시물 배경 -->
        <div class="reply_board">
        
                
                				  <!-- 카드형 게시물 -->
                <c:forEach items="${mypage}" var="mypage" begin="0" end="1">
           <div class = "card">
             <div class="card-box">
			   
					<h5 class="card-header">${mypage.title}</h5>
					<p class="card-main">${mypage.content}</p>
					<p><a href="detail?bno=${mypage.bno}" class="card-link">이게시물 자세히보기링크</a></p>
				   
			  </div>
			</div>
				</c:forEach>
		</div> 
</div>  


<div class="RB">
					<h3>작성한 게시물</h3>
					<p><a class="more_btn" href="#" role="button">더보기 »</a></p>
		<!-- 댓글 단 게시물 배경 -->
        <div class="write_board">
                
                				  <!-- 카드형 게시물 -->
                <c:forEach items="${mypage}" var="mypage" begin="0" end="1">
           <div class = "card">
             <div class="card-box">
			  				  
					<h5 class="card-header">${mypage.title}</h5>
					<p class="card-main">${mypage.content}</p>
					<p><a href="detail?bno=${mypage.bno}" class="card-link">이게시물 자세히보기링크</a></p>
				   
			  </div>
			</div>
				</c:forEach>
		</div>   
</div>
</div>  <!-- container1 -->

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