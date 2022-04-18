<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1f128e742d430fd5e25a6b3a23b533bd"></script>
<style>
#center {
	text-align: center;
	margin: auto;
	justify-content: center;
	display: flex;
}
.service{
	color: #0099FF;
}
.bs, .tag{
	color: #808080;
}
.bss{
	color: #FDC800;
}
.mk{
	font-size: 20px;
}
.local{
	color: #FF0000;
}
.btn{
	background-color: #0099FF;
}
</style>
</head>
<body>
	<!--
	 색상 참고 사이트
	 http://wi.visualshower.com/index.php?mid=ko&document_srl=25700&l=ko
	  -->
	
	<!-- menubar -->
	<%@ include file="/views/common/menubar.jsp"%>

	<!-- 컨테이너 시작 div -->
	<div class="container">
		<!-- <div class="px-4 py-5 my-5 text-center"> 글자 가운데 정렬 -->
		<div class="px-4 py-5 text-center">

			<!-- 최상단에 강아지 로고 <img class="d-block mx-auto mb-4" src="assets/img/gallery/fdog.png" alt="" width="72" height="57">  -->

			<img class="d-block mx-auto mt-7 mb-4" src="assets/img/gallery/fdog.png" alt="강아지로고" width="72" height="57">
			<h1 class="display-5 fw-bold bs">Brand Story</h1>
			<h3 class="display-5 fw-bold bss">둥글개 둥글개 유치원은 반려견의 입장에서 생각합니다.</h3>
			
			<div class="col-lg-6 mx-auto">
				<!-- mark 태그로 문구에 대한 강조를 해주었으나 뺴도 상관없음! -->
					<div class="mb-3">
						<mark class="mk">"반려견은 벅찬 사랑과 행복을 주는 동등한 가족입니다."</mark>
					</div>
					아이들의 눈높이에 맟춰 사람이 변화하도록 최선의 방법론을 제안합니다.<br>
					반려견의 행복하고 자유로운 삶과 보호자의 편리함을 위한 유치원입니다.
			</div>
			
		</div>


		<!-- 강아지 사진 보여지는 div 시작 -->
		<div class="container-md">
			<div class="row">
				<!--  row gx-1 : 사진 사이의 여백 증가할수록 넓어짐 -->
				<div class="col-sm row gx-1">
					<img src="assets/img/gallery/introDog5.jpg" alt="소개강아지5" width="50" />
				</div>
				<div class="col-sm row gx-1">
					<img src="assets/img/gallery/introDog2.jpg" alt="소개강아지2" width="50" />
				</div>
				<div class="col-sm row gx-1">
					<img src="assets/img/gallery/introDog3.jpg" alt="소개강아지3" width="50" />
				</div>
				<div class="col-sm row gx-1">
					<img src="assets/img/gallery/introDog44.jpg" alt="소개강아지4" width="50" />
				</div>
			</div>
		</div>
		<!-- 강아지 사진 보여지는 div 끝 -->
		
		<!-- <div class="px-4 py-5 my-5 text-center"> 글자 가운데 정렬 -->
		<div class="px-4 py-5 mt-5 mb-3 text-center">
			<h1 class="display-5 fw-bold tag">Service</h1>
		</div>
		
		
		<!-- 서비스 소개1 -->
		<div class="container-md mb-5">
			<div class="row featurette">
				<div class="col-md-7 order-md-2">
					<h4 class="featurette-heading service">BEAUTY SERVICE<br>
						<!-- mark 태그로 문구에 대한 강조를 해주었으나 뺴도 상관없음! -->
						<span class="text-muted">반려견 미용 서비스</span>
					</h4>

					<!-- <p></p>  -->
					<!-- p태그를 사용한 이유는 br을 쓸 경우 간격이 너무 벌어지기에 적당한 간격을 띄우기 위해서 넣음 -->

					<p class="lead">둥글개 둥글개 유치원에서는 강아지 미용까지 관리하고 있습니다. 27년 경력 베테랑
						실장님의 손길로 반려견들이 스트레스 없이 편안한 분위기에서 세련된 스타일링을 찾을 수 있습니다. 바쁜 견주분들에게
						편리함과 강아지의 건강관리를 위해 기본적인 청결관리 및 미용 서비스를 제공합니다.</p>
				</div>

				<!-- order-md-2 = 사진 오른쪽 글자 왼쪽 / order-md-1 = 사진 왼쪽 글자 오른쪽 -->
				<!-- order-md-1의 단점은 페이지를 최소화 했을 경우 글자와 사진이 갈 곳을 잃어버린다. test 결과 order-md-2일 때에만 페이지 축소 시 그대로 적용된다. -->
				<div class="col-md-4 order-md-1">
					<img src="assets/img/gallery/introDogBeauty.jpg" width="450" />
				</div>
			</div>
		</div>

		<!-- hr 태그만 쓰면 선이 끝 과 끝으로 되어있어 중간에 껴있을 경우 보기에 좋지 않다 center 태그로 가운데에 보내준 뒤 size(굵기)와 width(길이)를 조정 -->
		<!--<hr size="5" id="center" style="width: 90%"> -->
		
		<!-- 서비스 소개2 -->
		<div class="container-md">
			<div class="row featurette">
				<div class="col-md-7 order-md-2">
					<h4 class="featurette-heading service">PICKUP SERVICE<br>
						<!-- mark 태그로 문구에 대한 강조를 해주었으나 뺴도 상관없음! -->
						<span class="text-muted">반려견 픽업 서비스</span>
					</h4>
					<!-- <p></p> -->
					<!-- p태그를 사용한 이유는 br을 쓸 경우 간격이 너무 벌어지기에 적당한 간격을 띄우기 위해서 넣음 -->

					<p class="lead">
						둥글개 둥글개 유치원에서는 픽업 서비스를 지원하고 있습니다.아이들을 안전하게 태우고 집에서부터
						유치원까지, 또 유치원에서 집까지 운행하는 픽업 서비스로 견주분들의 편의를 제공하고 있습니다.
						물론 언제나 안전 운전으로 반려견들의 안전은 잊지 않고 운행합니다.
					</p>
					<p class="local">*강남, 서초 지역 한정, 그중 일부 지역은 제외</p>
					<button class="btn btn-lg btn-primary w-40" onclick="goSchoolbus();">
						<b>통학버스 신청하기</b>
					</button>
				</div>

				<!-- order-md-2 = 사진 오른쪽 글자 왼쪽 / order-md-1 = 사진 왼쪽 글자 오른쪽 -->
				<div class="col-md-4 order-md-2">
					<img src="assets/img/gallery/schoolbus.jpg" width="450" />
				</div>
			</div>
		</div>

		<!-- <div class="px-4 py-5 my-5 text-center"> 글자 가운데 정렬 -->
		<div class="px-4 py-5 mt-5 mb-3 text-center">
			<h1 class="display-5 fw-bold tag">Location</h1>
		</div>

		
		<!-- 지도를 표시할 div -->
		<div class="mb-8" id="map" style="width:100%; height:600px;"></div>
		
	</div>
	


	
	<!-- footer -->
	<%@ include file="/views/common/footer.jsp"%>
	
	<script type="text/javascript">
	
		function goSchoolbus() {
			location.href="<%= contextPath%>/schoolbusForm.do;"
		}
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(37.49901442259218, 127.03285217285156), // 지도의 중심좌표 => kh 홈페이지에서 가져옴
	        level: 3 // 지도의 확대 레벨
	    };

		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		//아래부터는 지도 부가적인 기능
		
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();

		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
		// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
		var marker = new kakao.maps.Marker();

		// 타일 로드가 완료되면 지도 중심에 마커를 표시합니다
		kakao.maps.event.addListener(map, 'tilesloaded', displayMarker);

		function displayMarker() {
		    
		    // 마커의 위치를 지도중심으로 설정합니다 
		    marker.setPosition(map.getCenter()); 
		    marker.setMap(map); 
	
		    // 아래 코드는 최초 한번만 타일로드 이벤트가 발생했을 때 어떤 처리를 하고 
		    // 지도에 등록된 타일로드 이벤트를 제거하는 코드입니다 
		    // kakao.maps.event.removeListener(map, 'tilesloaded', displayMarker);
		}
	</script>

</body>
</html>