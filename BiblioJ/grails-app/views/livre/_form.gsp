<%@ page import="biblioj.Livre" %>



<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'auteurs', 'error')} required">
	<label for="auteurs">
		<g:message code="livre.auteurs.label" default="Auteurs" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="auteurs" name="auteurs.id" from="${biblioj.Auteur.list()}" optionKey="id" required="" value="${livreInstance?.auteurs?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplaires', 'error')} required">
	<label for="nombreExemplaires">
		<g:message code="livre.nombreExemplaires.label" default="Nombre Exemplaires" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplaires" type="number" value="${livreInstance.nombreExemplaires}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplairesDisponibles', 'error')} required">
	<label for="nombreExemplairesDisponibles">
		<g:message code="livre.nombreExemplairesDisponibles.label" default="Nombre Exemplaires Disponibles" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplairesDisponibles" type="number" value="${livreInstance.nombreExemplairesDisponibles}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'reservations', 'error')} required">
	<label for="reservations">
		<g:message code="livre.reservations.label" default="Reservations" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservations" name="reservations.id" from="${biblioj.Reservation.list()}" optionKey="id" required="" value="${livreInstance?.reservations?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'titre', 'error')} ">
	<label for="titre">
		<g:message code="livre.titre.label" default="Titre" />
		
	</label>
	<g:textField name="titre" value="${livreInstance?.titre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'typeDocument', 'error')} required">
	<label for="typeDocument">
		<g:message code="livre.typeDocument.label" default="Type Document" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="typeDocument" name="typeDocument.id" from="${biblioj.TypeDocument.list()}" optionKey="id" required="" value="${livreInstance?.typeDocument?.id}" class="many-to-one"/>
</div>

