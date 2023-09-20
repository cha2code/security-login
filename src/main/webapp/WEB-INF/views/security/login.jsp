<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layouts/header.jsp" %>

<br>
<div style="width: 500px" class="mx-auto">

<h1>login page</h1>

<hr>

<c:if test="${param.error == 'true'}">
	<div class="error">
		사용자 ID 또는 Password가 일치하지 않습니다.
	</div>
</c:if>

<br>

	<!-- action = SecurityConfig에서 정해 놓은 url -->
	<form action="/security/login" method="post">

		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	
		<div class="form-group">
			<label for="username"><i class="fa-solid fa-user"></i> User ID : </label> 
			<input type="text" name="username"/>
			<br><br>
		</div>
	
		<div class="form-group">
			<label for="username"><i class="fa-solid fa-lock"></i> Password : </label>
			<input type="password" name="password"/>
			<br><br>
		</div>
	
		<div class="form-group form-check">
			<label class="form-check-label">
				<input class="form-check-input" type="checkbox" name="remember-me" /> 로그인 유지
			</label>
		</div>
	
		<button type="submit" class="btn btn-primary btn-block">
			<i class="fa-solid fa-right-to-bracket"></i> login
		</button>
	
	</form>
</div>

<%@ include file="../layouts/footer.jsp" %>