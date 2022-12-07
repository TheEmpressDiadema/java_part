<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="result-edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Создать значения</h1>
		</c:when>
		<c:otherwise>
			<h1>Изменить значения #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/result">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="input-field col s6">
					<input type="text" name="userId" value="${dto.userId}"> <label for="userId">ID пользователя</label>
				</div>
			<div class="input-field col s6">
				<input type="text" name="testId" value="${dto.testId}"> <label for="testId">ID теста</label>
			</div>
			<div class="input-field col s6">
				<input type="text" name="date" value="${dto.date}"> <label for="date">Дата</label>
			</div>
			<div class="input-field col s6">
				<input type="text" name="mark" value="${dto.mark}"> <label for="mark">Оценка</label>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/result"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>