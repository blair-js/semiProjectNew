<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
  border-radius: 0;
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
div b{
	color: #0099FF;
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
		<b>반려견</b>의 정보를 입력해주세요.
	</div>
	
	<!-- 신청서 form 시작 -->
	<form class="reg-form mb-5">
	
		<!-- 사진 -->
		<div class="field-row mb-3">
			<label class="form-label">*사진</label> 
			<img id="titleImg" width="250" height="200">
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
				<select class="field form-dropdown" name="dogGender">
					<option value="M">남자</option>
					<option value="F">여자</option>
				</select>
		</div>
		<!-- 몸무게 -->
		<div class="field-row">
			<label class="form-label">*몸무게</label> 
			<select class="field form-dropdown" name="dogWeight" required>
				<option value="S">소형(1~5kg)</option>
				<option value="M">중형(6~10kg)</option>
				<option value="L">대형(11kg 이상)</option>
			</select>
		</div>
		<!-- 특이사항 -->
		<div class="field-row" style="text-align: left">
			<label class="form-label" for="memo">특이사항</label> 
			<textarea class="tar" id="memo" rows="5" cols="30" name="dogMemo">Hello Dogg World</textarea>
		</div>
		<!-- 입학가능여부조회 버튼 -->
		<div class="field-row">
			<label class="form-label"></label>
			<button type="button" class="form-button mb-3" onclick="goDogEnrollCheck()">입학 가능여부 조회</button>
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
				<input type="radio" id="radioBtn" name="wating" value="Y" checked> 예
				<input type="radio" id="radioBtn" name="wating" value="N"> 아니오
		</div>
		<!-- 입학신청서 제출 버튼 -->
		<div class="field-row btnWrapper">
			<button type="button" class="form-button" onclick="goDogEnroll()">입학신청서 제출</button>
		</div>
	</form>
	<!-- 신청서 form 끝 -->
	
	<!-- 숨겨져있는 파일영역 -->
	<div id="fileArea">
    	<input type="file" name="file" id="file" onchange="loadImg(this);">
    </div>
	
	<script>
		$(function(){
		      // 파일 input 하는 부분은 숨겼음
		      $("#fileArea").hide();
		      
		      // 위에 이미지 부분을 클릭하면 숨겨놓은 input 버튼 클릭되게 구현
		      $("#titleImg").click(function(){
		         $("#file").click();
		      });
	      });
		
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
		//입학신청 함수
		function goDogEnroll() {
			location.href="<%= request.getContextPath()%>/insertDog.do;"
		}
		//입학가능여부조회
		function goDogEnrollCheck() {
			alert('일단 입학가능');
		}
	</script>

	<!-- footer -->
	<%@ include file="../common/footer.jsp"%>
	
</body>
</html>