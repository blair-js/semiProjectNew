<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
<style>
	@font-face {
		font-family: 'GmarketSansMedium';
		src:
			url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
			format('woff');
		font-weight: normal;
		font-style: normal;
	}
	body{font-family: 'GmarketSansMedium';}
	.title{margin-top:10%;}
	.video-film {box-shadow: rgba(0, 7, 15, 0.7) 0 0 0 9999px; z-index: 100;}
	
	.video-background {
		background: #000; position: fixed;
		top: 0; right: 0; bottom: 0; left: 0; z-index: -99;
	}
	.video-foreground, .video-background iframe {
		position: absolute; top: 0; left: 0;
		width: 100%; height: 100%; pointer-events: none;
	}
	@media ( min-aspect-ratio : 16/9) {
		.video-foreground {height: 285%; top: -100%;}
	}
	@media ( max-aspect-ratio : 16/9) {
		.video-foreground {width: 285%; left: -100%;}
	}
	.btn {
		z-index: 50; color: #fff; font-size: 20px; border: 2px solid #fff;
		padding: 12px 24px; border-radius: 5px; cursor: pointer; background-color: rgba(0, 0, 0, 0);
		font-family: 'GmarketSansMedium';
	}
	.btn:hover {
		color: #ff6868; font-size: 20px; border: 2px solid #ff6868;
		padding: 12px 24px; border-radius: 5px; cursor: pointer;
	}
	#indexBtn {position: fixed; text-align: center}
	.center1 {position: absolute; top: 60%; left: 47%; margin: -50px 0 0 -50px;}
	.center2 {position: absolute; top: 70%; left: 51%; margin: -50px 0 0 -50px;}
	
	.aTag{
		text-decoration:none;
		color:white;
		font-size:18px;
		margin:auto;
	}
	a:hover{
		color: #ff6868; 
	}
</style>
</head>
<body>
	<div class="title">
		<p id="titlement" style="text-align:center; font-size:20pt; color:white;"></p>
	</div>
	
	<div id="indexBtn" class="center1">
		<button class="btn" onclick="javascript:location.href='index.jsp'">둥글개 둥글개 바로가기</button>
	</div>
	<div id="loginBtn" class="center2">
		<a class="aTag" href="/loginForm.do">로그인</a>&nbsp;
		<a class="aTag" href="/userEnrollForm.do">회원가입</a>
	</div>
	
	<div class="video-background">
		<div class="video-foreground">
			<div id="muteYouTubeVideoPlayer"></div>
		</div>
	</div>
	<div class="video-film"></div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script  src="https://cdn.jsdelivr.net/jquery.typeit/4.4.0/typeit.min.js"></script>
	
	<script>
	// 화면에 타자 입력하듯이 보여주는 함수, 반복으로 실행
		$(function(){
			$("#titlement").typeIt({
				strings: ["둥글개 둥글개는 언제나", "반려견이 행복한", "세상을 만들어갑니다."],
				speed: 70,
				autoStart: true,
				loop:true
			 	//breakLines: false
			});
		});
	</script>
	
	<script defer src="https://www.youtube.com/iframe_api"></script>
	<script type="text/javascript">
		var player;
		
		function onYouTubePlayerAPIReady(){
			player = new YT.Player('muteYouTubeVideoPlayer', {
				videoId : '0YRDWKWmnwc',
				playerVars : {
					autoplay : 1, 		// Auto-play the video on load // 자동 재생 (유)
					controls : 0, 		// Show pause/play buttons in player // 컨트롤러의 유무 (무)
					rel : 0,			// 해당 영상이 종료된 후 관련 동영상 표시할지
					start : 10,
					end : 30,
					showinfo : 0, 		// Hide the video title // 재생 영상에 대한 정보 유무
					modestbranding : 1, // Hide the Youtube Logo
					loop : 1, 			// Run the video in a loop // 반복 재생에 대한 여부
					playlist : '0YRDWKWmnwc',
					fs : 0, 			// Hide the full screen button
					cc_load_policy : 0, // Hide closed captions
					iv_load_policy : 3, // Hide the Video Annotations
					autohide : 1		// Hide video controls when playing
				},
				events:{
					onReady:function(e){
						e.target.mute();
					}
				}
			});
		}
	</script>

</body>
</html>