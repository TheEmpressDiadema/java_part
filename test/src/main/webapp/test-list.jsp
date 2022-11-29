<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Tests" scope="application"/>
<t:wrapper>
		<h1>Список тестов</h1>
		<div class="row">
			<div class="col s12">
				<div class="center-align">
					<a class="btn-floating btn-large waves-effect waves-light" href="edit.jsp"><i class="material-icons">add</i></a>
				</div>
			</div>
		</div>
		<table>
			<thead>
				<tr>
					<th>№</th>
					<th>Имя теста</th>
					<th>Тип теста</th>
					<th>Активен</th>
					<th>Дата создания</th>
					<th>Дата обновления</th>
					<th>Изменить</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Математика</td>
					<td>Образовательный</td>
					<td>true</td>
					<td>01.01.2022</td>
					<td>08.11.2022</td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="edit.jsp"><i class="material-icons">edit</i></a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>Физика</td>
					<td>Образовательный</td>
					<td>true</td>
					<td>01.01.2022</td>
					<td>08.11.2022</td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="edit.jsp"><i class="material-icons">edit</i></a></td>
				</tr>
				<tr>
					<td>3</td>
					<td>Английский язык</td>
					<td>Образовательный</td>
					<td>true</td>
					<td>01.01.2022</td>
					<td>08.11.2022</td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="edit.jsp"><i class="material-icons">edit</i></a></td>
				</tr>
			</tbody>
		</table>

	</div>
</div>
</t:wrapper>