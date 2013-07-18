
<%@ page import="com.incra.Challenge" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'challenge.label', default: 'Challenge')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-challenge" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-challenge" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'challenge.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'challenge.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="statusType" title="${message(code: 'challenge.statusType.label', default: 'Status Type')}" />
					
						<g:sortableColumn property="startDate" title="${message(code: 'challenge.startDate.label', default: 'Start Date')}" />
					
						<g:sortableColumn property="endDate" title="${message(code: 'challenge.endDate.label', default: 'End Date')}" />
										
					</tr>
				</thead>
				<tbody>
				<g:each in="${challengeInstanceList}" status="i" var="challengeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${challengeInstance.id}">${fieldValue(bean: challengeInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: challengeInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: challengeInstance, field: "statusType")}</td>
					
						<td><g:formatDate date="${challengeInstance.startDate}" /></td>
					
						<td><g:formatDate date="${challengeInstance.endDate}" /></td>
										
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${challengeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
