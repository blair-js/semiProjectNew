<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 입학신청</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style type="text/css">
@font-face {
	font-family: 'LeferiPoint-BlackA';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/LeferiPoint-BlackA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
* {
	font-family: 'LeferiPoint-BlackA';
}
.reg-form {
	background: #FDC800;;
	box-sizing: border-box;
	box-shadow: 1px 2px 6px rgba(0, 0, 0, 0.4);
	margin: 15px auto;
	padding: 30px;
	width: 650px;
}
.field-row {
  position: relative;
  width: 100%;
}
.form-label {
  display: inline-block;
  font-size: 16px;
  margin: 0 5px 5px 0;
  text-align: right;
  width: 100px;
}
.field {
  border: 1px solid #ccc;
  box-sizing: border-box;
  display: inline-block;
  font-size: 16px;
  padding: 10px;
  margin-bottom: 15px;
  width: 240px;
}
.field.field-short {
  width: 70px;
}
.form-button {
  background: #0099FF;
  margin-bottom: 7px;
  border: none;
  border-radius: 5px;
  color: white;
  display: inline-block;
  padding: 10px;
  font-size: 16px;
}
.tar {
	resize: none;
}
#titleImg{
	border: 1px solid gray;
}
.btnWrapper{
	text-align: center;
}
button{
	border-radius: 10px;
}
div .bDog{
	color: #0099FF;
}
.btns{
	width: 20%;
	border-radius: 5px;
	color: white;
	font-weight: bold;
}
.enrolls{
	color: #FDC800;
}
#goLogin{
	text-align: center;
}
</style>
</head>
<body>
	<!-- DogEnrollFormServlet에서 여기로 옴 -->
	
	<!-- menubar -->
	<%@ include file="../common/menubar.jsp"%>
	
	
		<div class="py-3 text-center mb-3 mt-3">
			<img class="d-block mx-auto mb-4" src="assets/img/gallery/paw.png" alt="강아지로고" width="72" height="57">
			<h2>입학 신청서</h2>
			<b class="bDog">반려견</b>의 정보를 입력해주세요.
		</div>
		
		<!-- 신청서 form 시작 -->
		<!-- 첨부파일도 같이 보내기 때문에 multipart/form-data 속성 설정 -->
		<form class="reg-form mb-5" action="<%= contextPath%>/insertDog.do;" enctype="multipart/form-data" method="post" onsubmit="return dogEnrollValidate();">
			<!-- hidden으로 해당 loginUser의 userNo도 같이 전달해야한다. -->
			<input type="hidden" id="userNo" name="userNo" value="<%= loginUser.getUserNo() %>">
			
			<!-- 사진 -->
			<div class="field-row mb-3">
				<label class="form-label">*사진</label> 
				<img id="titleImg" name="titleImg" width="250" height="200">
			</div>
			<!-- 이름 -->
			<div class="field-row">
				<label class="form-label" for="dogName">*이름</label> 
				<input type="text" id="dogName" class="field text-field first-name-field" name="dogName" required>
			</div>
			<!-- 나이 -->
			<div class="field-row">
				<label class="form-label cf" for="dogAge">나이</label> 
				<input type="text" id="dogAge" class="field text-field last-name-field" name="dogAge" required> 
			</div>
			<!-- 성별 -->
			<div class="field-row">
				<label class="form-label">*성별</label>
					<select class="field form-dropdown" id="dogGender" name="dogGender">
						<option value="M">남자</option>
						<option value="F">여자</option>
					</select>
			</div>
			<!-- 몸무게 -->
			<div class="field-row">
				<label class="form-label">*몸무게</label> 
				<select class="field form-dropdown" id="dogWeight" name="dogWeight" required>
					<option value="S">소형(1~5kg)</option>
					<option value="M">중형(6~10kg)</option>
					<option value="L">대형(11kg 이상)</option>
				</select>
			</div>
			<!-- 특이사항 -->
			<div class="field-row" style="text-align: left">
				<label class="form-label" for="memo">특이사항</label> 
				<textarea class="tar" id="memo" name="memo" rows="5" cols="30">Hello Dogg World</textarea>
			</div>
			<!-- 입학가능여부조회 버튼 -->
			<div class="field-row">
				<label class="form-label"></label>
				<button type="button" class="form-button mb-3" id="" onclick="goDogEnrollCheck()">입학 가능여부 조회</button>
			</div>
			<!-- 자동 반배정 -->
			<div class="field-row">
				<label class="form-label"></label>
				<span style="color:red;">*반은 강아지의 몸무게에 따라 자동으로 배정됩니다.</span>
			</div>
			<!-- 반 -->
			<div class="field-row">
				<label class="form-label" for="dogClass">*반</label>
				<input type="text" id="dogClass" class="field text-field hs-field" name="dogClass" required readonly>
			</div>
			<!-- 대기여부 -->
			<div class="field-row">
				<label class="form-label">대기여부</label> 
					<input type="radio" id="radioBtnYes" name="wating" value="Y"> 예
					<input type="radio" id="radioBtnNo" name="wating" value="N"> 아니오
			</div>
			<!-- 입학신청서 제출 버튼 -->
			<div class="field-row btnWrapper mt-5">
				<button type="submit" id="enrollBtn" class="form-button">입학신청서 제출</button>&nbsp;
				<button type="submit" id="watingBtn" class="form-button">대기신청서 제출</button>
			</div>
			
			<!-- 숨겨져있는 파일영역 -->
			<div id="fileArea">
		    	<input type="file" name="file" id="file" onchange="loadImg(this);" required>
		    </div>
	</form>
	<!-- 신청서 form 끝 -->
	

	<script>
		$(function(){
		      // 파일 input 하는 부분은 숨겼음
		      $("#fileArea").hide();
		      
		      // 위에 이미지 부분을 클릭하면 숨겨놓은 input 버튼 클릭되게 구현
		      $("#titleImg").click(function(){
		         $("#file").click();
		      });
	      });
		
		function goLogin() {
			location.href="<%= contextPath%>/loginForm.do;"
		}
		
		//사진을 로드해주는 함수
		function loadImg(inputFile){
	      if(inputFile.files.length == 1){
	         //readAsDataURL : 파일의 읽어서 리더에 업로드 동작이 되면서 파일 읽기가 완료가 되면 이미지 src를 URL에 담아주는 방식
	         var reader = new FileReader(); //파일을 읽어 들일 객체 생성
	         reader.readAsDataURL(inputFile.files[0]); //파일을 읽어 들이는 메소드
	         // onload : 파일 읽기가 완료가 되면 실행 하는것
	         reader.onload = function(e){ //파일 읽기가 다 완료 되면 실행
	            $("#titleImg").attr("src", e.target.result);
	         }
	      }
	   }
		//입학신청 버튼 클릭시 서블릿으로 가는 함수
		function goDogEnroll() {
			location.href="<%= request.getContextPath()%>/insertDog.do;"
		}
		//입학가능여부조회
		function goDogEnrollCheck() {
			
			//form에서 사용자가 선택한 도그의 몸무게 값을 가져온다.
			var dogWeight = $('#dogWeight').val();
			
			//Ajax 통신을 할 것
			$.ajax({
				//기재한 매핑명이 있는 서블릿으로 이동 
				url: "dogEnrollChecked.do",
				
				//전달할 데이터 : 위에서 변수에 담아놓은 도그 몸무게 전달(키:값)
				data: {
					dogWeight: dogWeight,
				},
				
				//type : 전송방식
				type:"get",
				
				//성공시
				success: function(className) {
					console.log("Ajax 통신성공")
					
					//결과값으로 들어온 className이 null이 아닌 경우(servlet에서 null값이 절대 안넘어오게 처리함)
					if($.trim(className) != null){
						if($.trim(className) != "대기"){ //className 값이 "대기"가 아니라면 
							alert('입학이 가능합니다. 확인을 누르시면 반이 배정됩니다.')
							$('#dogClass').val(className)
							$('#radioBtnNo').attr('checked', true)
							$('#radioBtnYes').attr('disabled', true)
							$('#watingBtn').attr('disabled', true)
							$('#watingBtn').css('background-color', 'gray')
							
						}else{ //className 값이 "대기"라면
							alert('정원초과로 입학이 불가능합니다. 대기를 부탁드립니다.')
							$('#dogClass').val('대기중')
							$('#radioBtnYes').attr('checked', true)
							$('#radioBtnNo').attr('disabled', true)
							$('#enrollBtn').css('background-color', 'gray')
							$('#enrollBtn').attr('disabled', true)
						}
					}
				},
				
				//실패시
				error: function() {
					console.log("Ajax 통신실패")
				}
				
			})
			
		}
		
		//form에 모든 데이터가 작성되었는지 확인해주는 용도
		function dogEnrollValidate() {
			if($('#file').val() == null){
				alert('파일을 첨부해주세요.');
				return false;
			}
			if($('#dogName').val() == null){
				alert('이름을 입력해주세요.');
				$('#dogName').focus();
				return false;
			}
			if($('#dogGender').val() == null){
				alert('성별을 선택해주세요.');
				$('#dogGender').focus();
				return false;
			}
			if($('#dogWeight').val() == null){
				alert('몸무게를 선택해주세요.');
				$('#dogWeight').focus();
				return false;
			}
			
			return true;
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>