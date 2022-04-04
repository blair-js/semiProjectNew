<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

	#center{
	 text-align: center;
	 margin: auto;
	 justify-content: center;
	 display: flex;
	}

</style>

</head>
<body>
	<!--
	 색상 참고 사이트
	 http://wi.visualshower.com/index.php?mid=ko&document_srl=25700&l=ko
	  -->
	
	<%@ include file = "/views/common/menubar.jsp" %> 

   <!-- <div class="px-4 py-5 my-5 text-center"> 글자 가운데 정렬 -->
  <div class="px-4 py-5 my-5 text-center">
  
   <!-- 최상단에 강아지 로고 <img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png" alt="" width="72" height="57">  -->
   
    <img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png" alt="" width="72" height="57"> 
   
    <h1 class="display-5 fw-bold" style="color: #808080;">Brand Story</h1>
    
    <br>

    <h3 class="display-5 fw-bold" style="color: #FFCC00">둥글개 둥글개 유치원은 반려견의 입장에서 생각합니다.</h3>
    <br>
    <div class="col-lg-6 mx-auto">
    <!-- mark 태그로 문구에 대한 강조를 해주었으나 뺴도 상관없음! -->
      <p class="lead mb-4"><mark>반려견은 벅찬 사랑과 행복을 주는 동등한 가족입니다.</mark><br> 
      아이들의 눈높이에 맟춰 사람이 변화하도록 최선의 방법론을 제안합니다.<br>
      반려견의 행복하고 자유로운 삶과 보호자의 편리함을 위한 유치원입니다.</p>
     
      <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
      </div>
    </div>
  </div>


	<!-- 제일 처음 나오는 강아지 사진 해당 div에 img를 넣으면 가로로 나오게 된다 img 사이의 여백 및 길이 높이는 안쪽 div에서 지정 --> 
	
	<div class="container-md">
		<div class="row">
	<!--  row gx-1 : 사진 사이의 여백 증가할수록 넓어짐 --> 
			<div class="col-sm row gx-1"><img src="assets/img/gallery/intro_dog_1.JPG" width="50" height ="250px"/></div>
			<div class="col-sm row gx-1"><img src="assets/img/gallery/intro_dog_2.JPG" width="50" height ="250px"/></div>
			<div class="col-sm row gx-1"><img src="assets/img/gallery/intro_dog_3.JPG" width="50" height ="250px"/></div>
			<div class="col-sm row gx-1"><img src="assets/img/gallery/intro_dog_4.JPG" width="50" height ="250px"/></div>
		</div>
		
	</div>
				
	<br>
	<br>
	
	<!-- <div class="px-4 py-5 my-5 text-center"> 글자 가운데 정렬 -->
	<div class="px-4 py-5 my-5 text-center">
	<h1 class="display-5 fw-bold" style="color: #808080;">Service</h1>
	</div>
	
	<div class="container-md">
    <div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h4 class="featurette-heading"> <h4 style="color: #0099FF">BEAUTY SERVICE</h4> 
   		
   		<!-- mark 태그로 문구에 대한 강조를 해주었으나 뺴도 상관없음! -->
        <span class="text-muted">반려견 미용 서비스</span></h4> 
        
    	 <p></p> <!-- p태그를 사용한 이유는 br을 쓸 경우 간격이 너무 벌어지기에 적당한 간격을 띄우기 위해서 넣음 -->
    	
        <p class="lead">둥글개 둥글개 유치원에서는 강아지 미용까지 관리하고 있습니다.
						27년 경력 베테랑 실장님의 손길로 반려견들이 스트레스 없이 편안한 분위기에서 세련된 스타일링을 찾을 수 있습니다.
						바쁜 견주분들에게 편리함과 강아지의 건강관리를 위해 기본적인 청결관리 및 미용 서비스를 제공합니다.</p>
      </div>
      
      <!-- order-md-2 = 사진 오른쪽 글자 왼쪽 / order-md-1 = 사진 왼쪽 글자 오른쪽 -->
      <!-- order-md-1의 단점은 페이지를 최소화 했을 경우 글자와 사진이 갈 곳을 잃어버린다. test 결과 order-md-2일 때에만 페이지 축소 시 그대로 적용된다. -->
     <div class="col-md-4 order-md-1">
 	<img src="assets/img/gallery/intro_beauty.JPG" width="460" height ="250px"/> 
 	
 	</div>
    </div>
	</div>
	
	
	<br>
	
	<!-- hr 태그만 쓰면 선이 끝 과 끝으로 되어있어 중간에 껴있을 경우 보기에 좋지 않다 center 태그로 가운데에 보내준 뒤 size(굵기)와 width(길이)를 조정 -->
	
	
	<hr size="5" id="center" style="width:90%">
	
	
	
	<br>

	<div class="container-md">
    <div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h4 class="featurette-heading"> <h4 style="color: #0099FF">PICKUP SERVICE</h4> 
   		
   		<!-- mark 태그로 문구에 대한 강조를 해주었으나 뺴도 상관없음! -->
        <span class="text-muted">반려견 픽업 서비스</span></h4>
        
        <p></p> <!-- p태그를 사용한 이유는 br을 쓸 경우 간격이 너무 벌어지기에 적당한 간격을 띄우기 위해서 넣음 -->
        
        <p class="lead">둥글개 둥글개 유치원에서는 픽업 서비스를 지원하고 있습니다.<br> 
						아이들을 안전하게 태우고 집에서부터 유치원까지, 또 유치원에서 집까지 운행하는 픽업 서비스로 견주분들의 편의를 제공하고 있습니다. 
						물론 언제나 안전 운전으로 반려견들의 안전은 잊지 않고 <br>운행합니다. 
						<P style="color: #FF0000">*강남, 서초 지역 한정, 그중 일부 지역은 제외<p></p>
      </div>
      
      <!-- order-md-2 = 사진 오른쪽 글자 왼쪽 / order-md-1 = 사진 왼쪽 글자 오른쪽 -->
      <div class="col-md-4 order-md-2"> 
       <img src="assets/img/gallery/intro_pickup.JPG" width="460" height ="250px"/>
		
      </div>
    </div>
	</div>
	
	<br>
	<br>
	
	<!-- <div class="px-4 py-5 my-5 text-center"> 글자 가운데 정렬 -->
	<div class="px-4 py-5 my-5 text-center">
	<h1 class="display-5 fw-bold" style="color: #808080;">Location</h1>
	</div>
	
	

	

	<div class="col-8 mb-7" id="center"> <!-- col 사진의 길이 영역 조절 / mb = footer와의 간격 조절 -->
	
	<img class="w-100" src="assets/img/gallery/intro_location.JPG"  width="100px" height ="500px" />
	
	</div>
	
	
	<%@ include file = "/views/common/footer.jsp" %>	
	
</body>
</html>