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
					<input type="text" name="name" required value="${dto.name}"> <label for="name">Имя теста</label>
				</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
				<label><input type="checkbox" name="status" ${dto.status ? 'checked' : ''} value="true" /> <span>Активен</span>
				</label>
			</div>
		</div>
		<div class="col s6">
			<label for="subjectId">Subject ID</label> 
			<select name="subjectId" class="browser-default" required>
				<option value="">--select subject--</option>
				<c:forEach items="${allSubjects}" var="subject">
					<option value="${subject.id}" <c:if test="${subject.id eq dto.subjectId}">selected="selected"</c:if>>${subject.name}</option>
				</c:forEach>
			</select>
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