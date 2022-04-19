<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>둥글개 둥글개</title>
<style>
	.service_area .single_service {
    padding: 70px 25px 65px 25px;
    border: 4px solid #FDC800;
    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    border-radius: 10px;
    margin-bottom: 30px;
    -webkit-transition: 0.5s;
    -moz-transition: 0.5s;
    -o-transition: 0.5s;
    transition: 0.5s;
	}
	.service_icon_bg_1 {
	    background-image: url(assets/img/gallery/service_icon_bg_1.png);
	}
	.service_area .single_service .service_thumb {
	    height: 140px;
	    width: 160px;
	    background-repeat: no-repeat;
	    background-size: 100% 100%;
	    margin: auto;
	}
	.service_area .single_service .service_content h3 {
	    font-size: 25px;
	    font-weight: 600;
	    margin-top: 43px;
	    margin-bottom: 22px;
	}
.pp{
	font-size: 25px;
	font-family: 'GmarketSansMedium';
}
.h3f{
	font-size: 20px;
}
.dogg{
	font-weight: bold;
}
</style>
</head>
 <body>

    <!-- Main Content-->
    <main class="main" id="top">
   <%@ include file = "views/common/menubar.jsp" %>
      
      <!-- 232 189 85 강아지 배경 컬러 -->
      <section class="pt-0 pb-0" id="home" style="background:rgb(232,189,85)">
        <div class="container">
          <div class="row align-items-center">
          <!-- 메인강아지 이미지 삽입 -->
            <div class="col-md-5 col-lg-6 order-0 order-md-1 text-end">
               <img class="pt-md-0 w-100" src="assets/img/gallery/maindog.jpg" width="470" alt="mainDog"/>
            </div>
            <!-- 좌측 문구 -->
            <div class="col-md-7 col-lg-6 text-md-start text-center py-6">
              <h3 class="fs-3 fw-bold text-white my-0">WAL WAL</h3>
              <!-- <h1 class="fs-7 fw-bold fs-xl-7 mb-1 font-sans-serif my-0">Hello</h1> -->
              <!-- <h1 class="fs-7 fw-bold fs-xl-7 mb-4 font-sans-serif my-0">Dogg World</h1> -->
              <p class="fs-7 fw-bold fs-xl-7 mb-1 my-0">Hello</p>
              <p class="fs-7 fw-bold fs-xl-7 mb-4 my-0">Dogg World</p>
              <a class="btn btn-info me-2" href="<%= contextPath %>/dogEnrollForm.do" role="button"><b>입학신청</b></a>
              <a class="btn btn-success" href="<%= contextPath %>/schoolbusForm.do" role="button"><b>통학버스신청</b></a>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- ============================================-->
      <!-- <section> begin ============================-->
      <section>
        <!-- 홈화면의 컨텐츠 부분 -->
		    <div class="service_area">
		        <div class="container">
		            <div class="row justify-content-center ">
		                <div class="col-lg-7 col-md-10">
		                    <div class="section_title text-center mb-95">
		                        <h3 class="text-info mt-0 mb-1">반려견이 행복한 세상</h3>
		                        <h3 class="text-info mt-0 mb-4">둥글개 둥글개가 꿈꾸는 세상입니다.</h3>
		                        <p class="h3f">최고의 전문가들과 시설로 구성된 반려견 유치원으로<br>
		                        1,500만 반려견 시대를 맞이하여<br>
		                        둥글개 둥글개 유치원은 가족과 같은 마음으로 반려견을 교육하고 케어합니다.</p>
		                    </div>
		                </div>
		            </div>
		            <div class="col-sm-12 pt-3 pb-5" style="text-align:center;">
		        	<button type="button" class="btn btn-default btn-lg btn-warning my-4" onclick="goIntro();">
		        	<p class="m-2" style="color:white;">둥글개 소개 바로가기</p>
		        	</button>
		        	</div>
		            <div class="row justify-content-center">
		                <div class="col-lg-4 col-md-6">
		                    <div class="single_service">
		                         <div class="service_thumb service_icon_bg_1 d-flex align-items-center justify-content-center">
		                             <div class="service_icon">
		                                 <img src="assets/img/gallery/service_icon_1.png" alt="">
		                             </div>
		                         </div>
		                         <div class="service_content text-center">
		                            <h3 class="text-info">교육 프로그램</h3>
		                            <p>강아지의 사회화 교육 및 예절교육 등<br> 
		                            강아지별 성향을 고려한 교육 프로그램으로 운영됩니다.</p>
		                         </div>
		                    </div>
		                </div>
		                <div class="col-lg-4 col-md-6">
		                    <div class="single_service active">
		                         <div class="service_thumb service_icon_bg_1 d-flex align-items-center justify-content-center">
		                             <div class="service_icon">
		                                 <img src="assets/img/gallery/service_icon_2.png" alt="">
		                             </div>
		                         </div>
		                         <div class="service_content text-center">
		                            <h3 class="text-info">헬스 케어</h3>
		                            <p>강아지를 위한 유기농 간식과 청결 관리를 위한<br>
		                             미용서비스 등을 제공하고 있습니다.</p>
		                         </div>
		                    </div>
		                </div>
		                <div class="col-lg-4 col-md-6">
		                    <div class="single_service">
		                         <div class="service_thumb service_icon_bg_1 d-flex align-items-center justify-content-center">
		                             <div class="service_icon">
		                                 <img src="assets/img/gallery/service_icon_3.png" alt="">
		                             </div>
		                         </div>
		                         <div class="service_content text-center">
		                            <h3 class="text-info">강아지 놀이터</h3>
		                            <p>노즈워크, 어질리티 및 터그놀이 등<br> 
		                            강아지가 즐길 수 있는 다양한 놀이가 준비되어 있습니다.</p>
		                         </div>
		                    </div>
		                </div>
		                
		                <div class="section_title text-center mt-7">
	                        <p class="pp">
		                        둥글개 둥글개 유치원은<br>
								다양한 프로그램과 관리 시스템으로 많은 견주분들의 사랑을 받고 있습니다.<br>
								강아지 피트니스부터, 산책과 같은 동적인 프로그램부터<br> 
								간식 찾기, 미각 놀이와 같은 다양한 교육 프로그램을 운영하고 있습니다.<br>
								또한 견주분들의 편의를 위한 반려견 픽업 서비스와 미용 서비스 및 알림장 게시판을 제공합니다.
							</p>
	                        <h2 class="text-info mt-4 mb-1">모든 프로그램은 유치원 과정에 포함되어 있습니다.</h2>
	                    </div>
	                    
		            </div>
		        </div>
		    </div>
      </section>
      <script>
      		function goIntro(){
      			location.href="<%= contextPath%>/intro.do";
      		}
      </script>
      <!-- <section> close ============================-->
      <!-- ============================================-->

   <!-- footer -->
   <%@ include file = "views/common/footer.jsp" %>
   
    <!-- ===============================================-->
    <!--    JavaScripts-->
    <!-- ===============================================
    <script src="vendors/@popperjs/popper.min.js"></script>
    <script src="vendors/bootstrap/bootstrap.min.js"></script>
    <script src="vendors/is/is.min.js"></script>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=window.scroll"></script>
    <script src="vendors/fontawesome/all.min.js"></script>
    <script src="assets/js/theme.js"></script>-->
    
	<link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display&amp;family=Rubik:wght@300;400;500;600;700;800&amp;display=swap" rel="stylesheet">
	
</body>
</html>