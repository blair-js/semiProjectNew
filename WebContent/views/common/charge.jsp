<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 가상결제 api를 사용하기 위한 아임포트 CDN 추가 -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>
.container{
	margin: auto;
	text-align: center;
}
.textPr, .goLog{
	color : #0099FF;
}
.textPr b{
	color : #FDC800;
}
.btns{
	background-color: #F5F5F5';
	color : white;
	font-weight: bold;
	width: 30%;
}
.textR{
	color: red;
}
.mk{
	background-color: #FDC800;
}
.text20{
	font-size: 20px;
}
.text20 span{
	color: red;
}
.sel{
	display: inline-block;
}
.loginCkPay{
	color: red;
}
</style>
</head>
<body>
	
	<!-- menubar -->
	<%@ include file="/views/common/menubar.jsp"%>

	<form class="container">
		<img class="mt-3" alt="뼈다귀로고" src="assets/img/gallery/point.jpg" height="100">
		<h2 class="textPr"><b>뼈다귀</b> 충전이란?</h2>
		<div class="text20 mb-2">
			해당 웹사이트에서 운영중인<br>
			간식 페이지 <mark class="mk">나만먹을개</mark>에서 사용 가능한 포인트 입니다.<br>
			금액 별 충전되는 포인트로 자유롭게 간식 선택이 가능하며,<br>
			구매하신 간식은 반려견의 <span>간식 시간</span>에 배급됩니다.
		</div>
		<div class="textR mb-4">
			*자택으로 배송되는 상품이 아니라 소진 시까지 반려견에게 제공됩니다.
		</div>
		
		<div class="mb-7">
			<img alt="뼈다귀로고" src="assets/img/gallery/point.jpg" height="40">
			뼈다귀 충전금액 선택 
			<div class="input-group-prepend sel">
				<select class="form-select border-1 rounded-1" id="chargePoint" name="point" disabled>
					<option value="10">10원(뼈다귀 10개 충전)</option>
					<option value="20">20원(뼈다귀 20개 충전)</option>
					<option value="30">30원(뼈다귀 30개 충전)</option>
				</select>
			</div>
		</div>
		
		<div class="loginCkPay" id="loginCkPay">*로그인 후 결제가 가능합니다. <a class="goLog" href="<%= contextPath %>/loginForm.do" >로그인</a></div>
		<button type="button" class="btn_payment btn-lg btns mb-5" id="payBtn" disabled>결제하기</button>
		
	</form>
	
	
	<!-- footer -->
	<%@ include file="/views/common/footer.jsp"%>
	
	<script>
	//페이지 로드시
	$(function() {
		//로그인한 객체가 있다면
		if('<%=loginUser%>' != null){
			
			//버튼 두개 비활성화 속성 삭제하고
			$('#payBtn').removeAttr("disabled");
			$('#chargePoint').removeAttr("disabled");
			
			//로그인 필수 문구 숨기기
			$('#loginCkPay').hide();
			
			//그리고 컬러 입히기
			$('#payBtn').css("background-color", "#0099FF");
			$('#payBtn').css("color", "white");
			$('#payBtn').css("border", "0px");
			$('#chargePoint').css("border-color", "#0099FF");
		
		}
		
	})
	
	//결제하기 버튼 클릭시 실행되는 함수
	$(".btn_payment").click(function() {
		  //class가 btn_payment인 태그를 선택했을 때 작동한다.
		
		  //로그인된 회원객체에서 결제창에서 추가로 뿌려줄 정보를 받는다.
		  var userEmail = '<%= loginUser.getEmail()%>';
		  var userName = '<%= loginUser.getUserName()%>';
		  
		  //회원아이디
		  var userId = '<%= loginUser.getUserId()%>';
		  
		  //선택된 요소
		  var target = document.getElementById('chargePoint');
		  
	      //선택된 요소의 값(충전 금액)
		  var point = target.options[target.selectedIndex].value;
	      console.log("point확인 : " + point);
			
		  	IMP.init('imp61171514');
		  	//결제시 전달되는 정보
			IMP.request_pay({
					    pg : 'inicis', 
					    pay_method : 'card',
					    merchant_uid : 'merchant_' + new Date().getTime(),
					    name : '뼈다귀'/*상품명*/,
					    amount : point /*상품 가격*/, 
					    buyer_email : userEmail /*구매자 이메일*/,
					    buyer_name : userName
					    //buyer_tel : '010-1234-5678'/*구매자 연락처*/,
					    //buyer_addr : '서울특별시 강남구 삼성동'/*구매자 주소*/,
					    //buyer_postcode : '123-456'/*구매자 우편번호*/
					}, 
					function(rsp) {
						var result = '';
					    if ( rsp.success ) {
					        var msg = '결제가 완료되었습니다.';
					        //msg += '고유ID : ' + rsp.imp_uid;
					        //msg += '상점 거래ID : ' + rsp.merchant_uid;
					        msg += '결제 금액 : ' + rsp.paid_amount;
					        msg += ' & 카드 승인번호 : ' + rsp.apply_num;
					        msg += ' & 회원 아이디 : ' + userId;
					        result ='0';
					    } else {
					        var msg = '결제에 실패하였습니다.';
					        msg += '에러내용 : ' + rsp.error_msg;
					        result ='1';
					    }
					    //결제 성공시 이동하는 페이지/
					    if(result == '0') {
					    	location.href="<%=contextPath%>/userPointInsert.do?userId="+userId+"&point="+rsp.paid_amount;
					    }
					    alert(msg);
					});
				})
	</script>
	
</body>
</html>