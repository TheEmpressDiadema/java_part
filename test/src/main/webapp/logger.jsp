<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Register" scope="application"/>
<t:wrapper>
<div class="row">
	<form class="col s12">
		<div class="row">
			<div class="input-field col s12">
				<input id="password" type="password" class="validate"> <label
					for="password">Пароль</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<input id="email" type="email" class="validate"> <label
					for="email">Email</label>
			</div>
		</div>

		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="index.jsp">отмена</a> <a class="btn waves-effect waves-light green"
					href="#">Сохранить</a>
			</div>
		</div>
	</form>
</div>
</t:wrapper>