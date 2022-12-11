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
				<input type="text" name="date" required value="${dto.date}"> <label for="date">Дата в формате YYYY-MM-DD hh-mm</label>
			</div>
			<div class="input-field col s6">
				<input type="number" min="0" required max="10" name="mark" value="${dto.mark}"> <label for="mark">Оценка</label>
			</div>
		</div>
		<div class="col s6">
			<label for="userId">User ID</label> 
			<select name="userId" class="browser-default" required>
				<option value="">--select user--</option>
				<c:forEach items="${allUsers}" var="user">
					<option value="${user.id}" <c:if test="${user.id eq dto.userId}">selected="selected"</c:if>>${user.name} ${user.secondName} ${user.patronimyc}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col s6">
			<label for="testId">Test ID</label> 
			<select name="testId" class="browser-default" required>
				<option value="">--select test--</option>
				<c:forEach items="${allTests}" var="test">
					<option value="${test.id}" <c:if test="${test.id eq dto.testId}">selected="selected"</c:if>>${test.name}</option>
				</c:forEach>
			</select>
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