<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="subject-edit" scope="application"/>
<t:wrapper>
		<h1>Изменить предмет</h1>


		<div class="row">
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input placeholder="Имя теста" id="first_name" type="text"
							class="validate">
					</div>
					<div class="input-field col s6">
						<input id="last_name" type="text" class="validate"> <label
							for="last_name">Тип теста</label>
					</div>
				</div>				
				  <label>Статус</label>
				  <select class="browser-default">
					<option value="" disabled selected>Выберите</option>
					<option value="1">Активен</option>
					<option value="2">В разработке</option>
				  </select>
			</form>
		</div>

	</div>
</div>

<div class="row">
	<div class="col s12 input-field center-align">
		<a class="btn waves-effect waves-light red" href="list.jsp">К списку</a> <a class="btn waves-effect waves-light green"
			href="#">Сохранить</a>
	</div>
</div>
</t:wrapper>