<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Error 500" scope="application" />
<t:wrapper>
	<h1>Server down</h1>
	<h1>Попробуйте начать с <a  href="/test"> домашней страницы</a></h1>
</t:wrapper>