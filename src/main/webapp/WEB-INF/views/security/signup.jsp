<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../layouts/header.jsp" %>

<br>

<div style="width: 500px" class="mx-auto">

<h1>회원가입</h1>

<div>
	<!-- action은 현재 url로, method는 post로 전송 -->
	<form:form modelAttribute="member" cssClass="form">
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

			<div class="form-group">
				<form:label path="username"><i class="fa-solid fa-user"></i> User Id </form:label>
				<form:input path="username" cssClass="form-control"/>
				<form:errors path="username" cssClass="error"/>
			</div>
	
			<div class="form-group">
				<form:label path="password"><i class="fa-solid fa-lock"></i> password </form:label>
				<form:password path="password" cssClass="form-control"/>
				<form:errors path="password" cssClass="error"/>
			</div>
		
			<div class="form-group">
				<form:label path="password2"><i class="fa-solid fa-lock"></i> Check the password </form:label>
				<form:password path="password2" cssClass="form-control"/>
				<form:errors path="password2" cssClass="error"/>
			</div>
		
			<div class="form-group">
				<form:label path="email"><i class="fa-solid fa-envelope-open-text"></i> E-mail </form:label>
				<form:input path="email" cssClass="form-control"/>
				<form:errors path="email" cssClass="error"/>
			</div>
		
			<div class="btn">
				<button type="submit" class="btn btn-primary btn-block">
            	    <i class="fa-solid fa-right-to-bracket"></i> login
        	    </button>
   	     </div>

		</form:form>
	</div>
</div>

<%@ include file="../layouts/footer.jsp" %>