<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.schoolbus.model.dto.*"%>
<%
	// 현재 세션에 있는 회원의 정보로 로그인 안되어 있으면 0을 담고, 로그인이 되어있으면 회원 번호를 담아준다.
	int userNo = (User)request.getSession().getAttribute("loginUser") != null ? ((User)request.getSession().getAttribute("loginUser")).getUserNo() : 0;
	String adminCheck = (User)request.getSession().getAttribute("loginUser") != null ? ((User)request.getSession().getAttribute("loginUser")).getAdminChecked() : null;
	ArrayList<Schoolbus> list = (ArrayList<Schoolbus>)request.getAttribute("list");
	ArrayList<UserReservation> rList = (ArrayList<UserReservation>)request.getAttribute("rList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a3a36dd4e535bb8999adf903f3ebd469"></script>
<script src="https://kit.fontawesome.com/96b0e9ec8b.js" crossorigin="anonymous"></script>
<style>
	#container{
		margin:auto;
		text-align:center;
	}
	.sc-table{
		max-width:80%;
		margin:auto;
	}
	#schedule{
		background-color:white;
		border: 1px solid black;
	}
</style>
<title>둥글개 둥글개</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
  		<div class="container" id="container">
  			<span><h1 class="text-center"><i class="fa-solid fa-bus"></i> 통학버스 운행표</h1></span>
  			<div id="schoolmap" style="width:70%;height:400px; margin:0 auto;"></div>
  			<table class="sc-table table table-bordered mt-5 mb-5" style="border:solid 1px #808080;">
  				<thead class="bg-secondary" style="color:white;">
  					<tr>
  						<th>승차장소</th>
  					</tr>
  				</thead>
  				<tbody>
  					<tr>
  						<td>일원동 주민센터 앞</td>
  					</tr>
  					<tr>
  						<td>개포동역 4번 출구</td>
  					</tr>
  					<tr>
  						<td>도곡역 1번 출구</td>
  					</tr>
  					<tr>
  						<td>양재역 4번 출구</td>
  					</tr>
  					<tr>
  						<td>서울 남부터미널역</td>
  					</tr>
  					<tr>
  						<td>사당역 11번 출구</td>
  					</tr>
  					<tr>
  						<td>내방역 내방동물병원</td>
  					</tr>
  					<tr>
  						<td>교대역 9번 출구</td>
  					</tr>
  				</tbody>
  			</table>
  			<div class="bus-title mb-5">
  				<h2 class="text-warning">버스 이용 안내</h2>
  				<h4>둥글개 둥글개 통학버스는 한시간마다 버스가 운영됩니다.<br> 원하시는 시간의 버스를 예약하여 이용 바랍니다.</h4>
  				(통학버스는 무상서비스입니다. 다른 회원들을 위해 회원마다 하루에 한 번만 이용이 가능합니다.)<br><br>
  			<span><i class="fa-solid fa-bus"></i>운행지역 <br>
  				  일원동 주민센터 <i class="fa-solid fa-arrows-left-right"></i> 둥글개 둥글개 본원<br>
  				  <i class="fa-solid fa-clock-rotate-left"></i>&nbsp;운행시간 <br>
  				  평일 AM 08:00 ~ PM 03:00<br>
  				  <i class="fa-solid fa-hourglass-empty"></i>&nbsp;배차간격 : 1시간 간격<br>
  				  <i class="fa-solid fa-sign-hanging"></i> 경유지<br>
  				  일원동 주민센터 앞 - 개포동역 4번 출구 - 도곡역 1번 출구<br>
  				  양재역 4번 출구 - 서울 남부터미널역 - 사당역 11번 출구 <br>
  				  내방역 내방동물병원 - 교대역 9번 출구
  			</span>
  			</div>
  			<span id="ment" class="m-3"><b>※원하시는 시간의 버스를 예약해주세요.</b></span>
			<span id="userService"><b>※ 회원 전용</b> 서비스입니다. <a class="text-primary" href="/loginForm.do">로그인</a> 후 이용해주세요.</span>
			<br><span id="checkNo"><b>※ 이미 예약을 하셨습니다. 잘못 예약 하신경우 마이페이지에서 예약 취소 후 이용 바랍니다.</b></span>
  			<form id="busForm" action="/schoolbusInsert.do?userNo=<%=userNo %>" method="post">
	  			<div class="input-group mb-5 p-1" style="margin:auto; width:50%;">
	  				<span class="mt-2">시간 선택 :&nbsp;&nbsp;</span>
	  				<select id="schedule" name="scheduleNo" class="form-select border-1 rounded-1">
	  					<% for(Schoolbus sb : list){ %>
	  						<% if(sb.getSeatCount() == 0) { %>
	  							<option disabled value="<%=sb.getBusDallyNo() %>"><%= sb.getBusContent() %>
	  							 (잔여좌석 수: <%=sb.getSeatCount() %>)</option>
	  						<% }else{ %>
		  						<option value="<%=sb.getBusDallyNo() %>"><%= sb.getBusContent() %>
		  						 (잔여좌석 수: <%=sb.getSeatCount() %>)</option>
	  						<% } %>
	  					<% } %>
	  				</select>
	  			</div>
	  			<div class="applydiv">
	  				<button id="apply-btn" class="btn bg-warning btn-lg mb-5" style="color:white;" disabled><b>통학버스 신청하기<b></b></button>
				</div>
			</form>
		</div>
  		<script>
  			// 로그인한 회원만 입학신청이 가능, 로그인 안 했을 경우에는 버튼 비활성화 처리, 로그인 후 이용하라는것도 숨기기
  			var userNo = '<%= userNo %>';
  			var adminCheck = '<%= adminCheck %>';
  			console.log(adminCheck + "관리자");
  			if(userNo == 0){
  				$("#apply-btn").prop("disabled", true);
  				$("#ment").hide();
  				$("#checkNo").hide();
  			}else if(adminCheck == "Y"){ // 관리자일 경우 신청 불가
  				$("#apply-btn").prop("disabled", true);
  				$("#ment").hide();
  				$("#checkNo").hide();
  				$("#userService").hide();
  			}else{
  				$("#apply-btn").prop("disabled", false);
  				$("#userService").hide();
  				$("#checkNo").hide();
  				$("#ment").show();

  			}
  			
  			
  			// 예약을 한 회원의 경우 중복 예약 불가 예약 테이블에서 회원 아이디 조회
  			// 중복 예약 불가능하다고 멘트 보여주고 버튼 비활성화
  			// 예약한 회원이 없을 수도 있으니 rList가 비어있지 않을경우에만 실행하도록 if문 설정
  			if(userNo != 0 && <%= !(rList.isEmpty()) %>){
	  			<% for(int i=0; i<rList.size(); i++) { %>
	  				var rno = '<%= rList.get(i).getResUserNo() %>';
	  				if(rno == '<%= userNo %>'){
	  					$("#checkNo").show();
	  					$("#ment").hide();
	  					$("#apply-btn").prop("disabled", true);
	  				}
	  			<% } %>
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