<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login page</title>
		
		<script language="javascript">
			function loginCheck(){
				
				if(frm.t_id.value == ""){
					document.getElementById("empty_id").style.display = "block";
					frm.t_id.focus();
					return;
				}else if(frm.t_pwd.value == ""){
					document.getElementById("empty_pwd").style.display = "block";
					frm.t_pwd.focus();
					return;
				}
				frm.action = "/member/loginPost";
				frm.method = "post";
				frm.submit();
			}

			
		</script>
	</head>
	<body>
		<section class="login-form">
			<h1>TO-BE</h1>
			<form name="frm">
				<div>
					<input type="text" name="t_id" id="t_id" placeholder="아이디" aria-live="assertive">
				</div>
				<div class="error" id="empty_id" style="display:none">아이디를 입력해주세요.</div>
				<div>
					<input type="password" name="t_pwd" id="t_pwd" placeholder="비밀번호">
				</div>
				<div class="error" id="empty_pwd" style="display:none" aria-live="assertive">비밀번호를 입력해주세요.</div>
				<div>
					<input type="checkbox" name="useCookie" id="useCookie">
					<label for="useCookie">아이디 저장</label>
					<input type="checkbox" id="auto-login">
					<label for="auto-login">자동 로그인</label>
				</div>
				<div>
					<button type="button" onclick="loginCheck();">LOGIN</button>
				</div>
				<div>
					비밀번호를 잊으셨나요?<a href="/findPwd" target="_blank">비밀번호 찾기</a>
				</div>
			</form>
		</section>
	</body>
</html>