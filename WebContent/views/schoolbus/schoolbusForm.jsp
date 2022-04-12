<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 현재 세션에 있는 회원의 정보로 로그인 안되어 있으면 빈 값을 담고, 로그인이 되어있으면 회원 아이디를 담아준다.
	String userId = (User)request.getSession().getAttribute("loginUser") != null ? ((User)request.getSession().getAttribute("loginUser")).getUserId() : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a3a36dd4e535bb8999adf903f3ebd469"></script>
<script src="https://kit.fontawesome.com/96b0e9ec8b.js" crossorigin="anonymous"></script>
<style>
	.sc-table{
		max-width:80%;
		margin:auto;
		border: 1px solid;
	}
</style>
<title>둥글개 둥글개</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
  		<div class="container">
  			<span><h1 class="text-center"><i class="fa-solid fa-bus"></i> 통학버스 운행표</h1></span>
  			<div id="schoolmap" style="width:70%;height:400px; margin:0 auto;"></div>
  			<table class="sc-table table table-bordered text-center mt-5 mb-5" style="border:solid 1px #808080;">
  				<thead class="bg-secondary" style="color:white;">
  					<tr>
  						<th>출발시간</th>
  						<th>승차장소</th>
  					</tr>
  				</thead>
  				<tbody>
  					<tr>
  						<td>8시 00분</td>
  						<td>일원동 주민센터 앞</td>
  					</tr>
  					<tr>
  						<td>8시 00분</td>
  						<td>개포동역 4번 출구</td>
  					</tr>
  					<tr>
  						<td>8시 00분</td>
  						<td>도곡역 1번 출구</td>
  					</tr>
  					<tr>
  						<td>8시 00분</td>
  						<td>양재역 4번 출구</td>
  					</tr>
  					<tr>
  						<td>8시 00분</td>
  						<td>서울 남부터미널역</td>
  					</tr>
  					<tr>
  						<td>8시 00분</td>
  						<td>사당역 11번 출구</td>
  					</tr>
  					<tr>
  						<td>8시 00분</td>
  						<td>내방역 내방동물병원</td>
  					</tr>
  					<tr>
  						<td>8시 00분</td>
  						<td>교대역 9번 출구</td>
  					</tr>
  				</tbody>
  			</table>
  			<div class="applydiv text-center">
  				<button id="apply-btn" class="btn bg-warning btn-lg mb-5" style="color:white;" disabled onclick="goBus();"><b>통학버스 신청하기<b></b></button>
			</div>
		</div>
  		<script>
  			// 로그인한 회원만 입학신청이 가능, 로그인 안 했을 경우에는 버튼 비활성화 처리
  			var id = '<%= userId %>';
  			if(id == ""){
  				$("#apply-btn").prop("disabled", true);
  			}else{
  				$("#apply-btn").prop("disabled", false);
  			}
  		// 통학버스 신청하기 버튼 눌렀을때
  			function goBus(){
  				console.log("안녕");
  			}
			var mapContainer = document.getElementById('schoolmap'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new kakao.maps.LatLng(37.49895813632953, 127.03294537573757), // 지도의 중심좌표
			        level: 7 // 지도의 확대 레벨
			    };
			
			var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			
			// 마커가 표시될 위치입니다 
			var positions = [
			    {
			        content: '<div>둥글개둥글개 본원</div>', 
			        latlng: new kakao.maps.LatLng(37.49895813632953, 127.03294537573757)
			    },
			    {
			        content: '<div>8.교대역 9번 출구</div>', 
			        latlng: new kakao.maps.LatLng(37.49329918015625, 127.01306916518074)
			    },
			    {
			        content: '<div>7.내방역 내방동물병원</div>', 
			        latlng: new kakao.maps.LatLng(37.48760989481694, 126.99414009848037)
			    },
			    {
			        content: '<div>6.사당역 11번 출구</div>',
			        latlng: new kakao.maps.LatLng(37.47842288577915, 126.98200413659184)
			    },
			    {
			        content: '<div>5.서울 남부터미널역</div>',
			        latlng: new kakao.maps.LatLng(37.48508616543749, 127.01576438559796)
			    },
			    {
			        content: '<div>4.양재역 4번 출구</div>',
			        latlng: new kakao.maps.LatLng(37.48485685855263, 127.0347369446023)
			    },
			    {
			        content: '<div>3.도곡역 1번 출구</div>',
			        latlng: new kakao.maps.LatLng(37.49103680453607, 127.05506523480578)
			    },
			    {
			        content: '<div>2.개포동역 4번 출구</div>',
			        latlng: new kakao.maps.LatLng(37.489292027228636, 127.0664108757142)
			    },
			    {
			        content: '<div>1.일원동 주민센터 앞</div>',
			        latlng: new kakao.maps.LatLng(37.49089975242338, 127.08797304117097)
			    }
			];
			
			for (var i = 0; i < positions.length; i ++) {
			    // 마커를 생성합니다
			    var marker = new kakao.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: positions[i].latlng // 마커의 위치
			    });
			
			    // 마커에 표시할 인포윈도우를 생성합니다 
			    var infowindow = new kakao.maps.InfoWindow({
			        content: positions[i].content // 인포윈도우에 표시할 내용
			    });
			
			    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
			    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
			    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
			    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
			    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
			}
			
			// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
			function makeOverListener(map, marker, infowindow) {
			    return function() {
			        infowindow.open(map, marker);
			    };
			}
			
			// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
			function makeOutListener(infowindow) {
			    return function() {
			        infowindow.close();
			    };
			}
			
			// 선을 구성하는 좌표 배열 생성, 이 좌표들을 이어서 선을 표시한다.
			var linePath = [
				new kakao.maps.LatLng(37.49089975242338, 127.08797304117097),
			    new kakao.maps.LatLng(37.489292027228636, 127.0664108757142),
			    new kakao.maps.LatLng(37.49103680453607, 127.05506523480578),
			    new kakao.maps.LatLng(37.48485685855263, 127.0347369446023),
			    new kakao.maps.LatLng(37.48508616543749, 127.01576438559796),
			    new kakao.maps.LatLng(37.47842288577915, 126.98200413659184),
			    new kakao.maps.LatLng(37.48760989481694, 126.99414009848037),
			    new kakao.maps.LatLng(37.49329918015625, 127.01306916518074),
			    new kakao.maps.LatLng(37.49895813632953, 127.03294537573757)
				];
			
			// 지도에 표시할 선을 생성
			var polyline = new kakao.maps.Polyline({
				path: linePath, // 선을 구성하는 좌표 배열
				strokeWeight: 3, // 선의 두께
				strokeColor:'#75B8FA', //선의 색
				strokeOpacity: 0.8, // 선의 투명도
				strokeStyle: 'solid' //선 스타일
			});
			
			// 지도에 선 표시
			polyline.setMap(map);
		</script>
  	<%@ include file = "../common/footer.jsp" %>
</body>
</html>