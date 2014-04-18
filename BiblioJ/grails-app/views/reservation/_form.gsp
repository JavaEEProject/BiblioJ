<%@ page import="biblioj.Reservation" %>



<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="reservation.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="code" type="number" value="${reservationInstance.code}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'dateReservation', 'error')} required">
	<label for="dateReservation">
		<g:message code="reservation.dateReservation.label" default="Date Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateReservation" precision="day"  value="${reservationInstance?.dateReservation}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'livres', 'error')} ">
	<label for="livres">
		<g:message code="reservation.livres.label" default="Livres" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${reservationInstance?.livres?}" var="l">
    <li><g:link controller="livre" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="livre" action="create" params="['reservation.id': reservationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'livre.label', default: 'Livre')])}</g:link>
</li>
</ul>

</div>

