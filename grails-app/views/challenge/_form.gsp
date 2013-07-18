<%@ page import="com.incra.Challenge" %>



<div class="fieldcontain ${hasErrors(bean: challengeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="challenge.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${challengeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: challengeInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="challenge.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${challengeInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: challengeInstance, field: 'statusType', 'error')} required">
	<label for="statusType">
		<g:message code="challenge.statusType.label" default="Status Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="statusType" from="${com.incra.domain.nonentity.ChallengeStatusType?.values()}" keys="${com.incra.domain.nonentity.ChallengeStatusType.values()*.name()}" required="" value="${challengeInstance?.statusType?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: challengeInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="challenge.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${challengeInstance?.startDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: challengeInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="challenge.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${challengeInstance?.endDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: challengeInstance, field: 'activities', 'error')} ">
	<label for="activities">
		<g:message code="challenge.activities.label" default="Activities" />
		
	</label>
	<g:select name="activities" from="${com.incra.ActivityEvent.list()}" multiple="multiple" optionKey="id" size="5" value="${challengeInstance?.activities*.id}" class="many-to-many"/>
</div>

