<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>둥글개 둥글개</title>
</head>
<body>

	<%@ include file="../common/menubar.jsp"%>

	<h1>간식 수정, 삭제 (상세 페이지)</h1>

	<button class="btn btn-outline-warning btn-sm" style="width: 5%"
		id="center" onclick="goSnackUpdate()">간식 수정</button>

	<br>

	<button class="btn btn-outline-warning btn-sm" style="width: 5%"
		id="center" onclick="goSnackDelete()">간식 삭제</button>


	<script>
			function goSnackUpdate(){//간식 업데이트를 위한 서블릿
				location.href="<%=request.getContextPath()%>/snackUpdate.do"
			}
			function goSnackDelete(){//간식 삭제를 하기위해 form으로 페이지 전환
				location.href="<%=request.getContextPath()%>/snackDelete.do"
		}
	</script>
	
	<%@ include file="../common/footer.jsp"%>
</body>
</html>