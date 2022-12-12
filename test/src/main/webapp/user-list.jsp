<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageUrl" value="/user" scope="page" />
<c:set var="pageTitle" value="user-list" scope="application"/>
<t:wrapper>
		<h1>Список пользователей</h1>
		<div class="row">
			<div class="col s12">
				<div class="center-align">
					<a class="btn-floating btn-large waves-effect waves-light" href="/user?view=edit"><i class="material-icons">add</i></a>
				</div>
			</div>
		</div>
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>Имя</th>
					<th>Фамилия</th>
					<th>Отчество</th>
                    <th>Роль</th>
					<th>Создан</th>
					<th>Изменен</th>
					<th>Действия</th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="name">Имя пользователя</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="secondName">Фамилия</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="patronimyc">Отчество</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Создан</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="updated">Изменен</mytaglib:sort-link></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.name}" /></td>
					<td><c:out value="${entity.secondName}" /></td>
					<td><c:out value="${entity.patronimyc}" /></td>
					<td><c:out value="${entity.roleName}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.created}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.updated}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/user?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/user?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
<t:paging />
</t:wrapper>