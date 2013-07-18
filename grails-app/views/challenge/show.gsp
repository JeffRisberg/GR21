
<%@ page import="com.incra.Challenge" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'challenge.label', default: 'Challenge')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-challenge" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-challenge" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list challenge">
			
				<g:if test="${challengeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="challenge.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${challengeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${challengeInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="challenge.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${challengeInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${challengeInstance?.statusType}">
				<li class="fieldcontain">
					<span id="statusType-label" class="property-label"><g:message code="challenge.statusType.label" default="Status Type" /></span>
					
						<span class="property-value" aria-labelledby="statusType-label"><g:fieldValue bean="${challengeInstance}" field="statusType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${challengeInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="challenge.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${challengeInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${challengeInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="challenge.endDate.label" default="End Date" /></span>
					
						<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${challengeInstance?.endDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${challengeInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="challenge.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${challengeInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${challengeInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="challenge.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${challengeInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${challengeInstance?.activities}">
				<li class="fieldcontain">
					<span id="activities-label" class="property-label"><g:message code="challenge.activities.label" default="Activities" /></span>
					
						<g:each in="${challengeInstance.activities}" var="a">
						<span class="property-value" aria-labelledby="activities-label"><g:link controller="activityEvent" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${challengeInstance?.id}" />
					<g:link class="edit" action="edit" id="${challengeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
