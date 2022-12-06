<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="test-edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Создать тест</h1>
		</c:when>
		<c:otherwise>
			<h1>Изменить тест #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/test">
			<input type="hidden" name="id" value="${dto.id}" />
		<div class="row">
			<div class="input-field col s6">
					<input type="text" name="name" value="${dto.name}"> <label for="name">Имя теста</label>
				</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
					<input type="text" name="name" value="${dto.subjectId}"> <label for="subjectId">ID предмета</label>
				</div>
			<div class="input-field col s6">
				<label><input type="checkbox" name="status" ${dto.status ? 'checked' : ''} value="true" /> <span>Статус</span>
				</label>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/test"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>