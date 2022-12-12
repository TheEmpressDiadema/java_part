<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="result-list" scope="application"/>
<c:set var="pageUrl" value="/result" scope="page" />
<t:wrapper>
		<h1>Список результатов</h1>
		<div class="row">
			<div class="col s12">
				<div class="center-align">
					<a class="btn-floating btn-large waves-effect waves-light" href="/result?view=edit"><i class="material-icons">add</i></a>
				</div>
			</div>
		</div>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Имя пользователя</th>
					<th>Имя теста</th>
                    <th>Дата прохождения теста</th>
                    <th>Оценка</th>
                    <th>Создан</th>
                    <th>Изменен</th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="userName">Имя пользователя</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="testName">Имя тесте</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Создан</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="updated">Изменен</mytaglib:sort-link></th>
					<th>Действия</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.userName}" /></td>
					<td><c:out value="${entity.testName}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.date}" /></td>
					<td><c:out value="${entity.mark}" /></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.created}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.updated}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/result?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/result?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<t:paging />
</t:wrapper>