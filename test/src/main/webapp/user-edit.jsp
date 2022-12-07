<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="user-edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Создать пользователя</h1>
		</c:when>
		<c:otherwise>
			<h1>Изменить пользователя #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/user">
			<input type="hidden" name="id" value="${dto.id}" />
		<div class="row">
			<div class="input-field col s6">
					<input type="text" name="name" value="${dto.name}"> <label for="name">Имя пользователя</label>
				</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
					<input type="text" name="secondName" value="${dto.secondName}"> <label for="secondName">Фамилия пользователя</label>
				</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
					<input type="text" name="patronimyc" value="${dto.patronimyc}"> <label for="patronimyc">Отчество пользователя</label>
				</div>
		</div>
		<div class="col s6">
			<label for="roleId">Role ID</label> 
			<select name="roleId" class="browser-default" required>
				<option value="">--select test--</option>
				<c:forEach items="${allRoles}" var="role">
					<option value="${role.id}" <c:if test="${role.id eq dto.roleId}">selected="selected"</c:if>>${role.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/user"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>