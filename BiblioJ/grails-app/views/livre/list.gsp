
<%@ page import="biblioj.Livre"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'livre.label', default: 'Livre')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-livre" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
		</ul>

		<g:form controller="livre">
			<span> <label for="champRechercheLivreParTitre"> Titre</label>
				<g:textField name="champRechercheLivreParTitre"
					value="${params?.champRechercheLivreParTitre}" />
			</span>

			<span> <label for="champRechercheLivreParAuteur">
					Auteur </label> <g:textField name="champRechercheLivreParAuteur"
					value="${params?.champRechercheLivreParAuteur}" />
			</span>

			<span> <label for="champRechercheLivreParTypeDocument">
					Type </label> <g:select id="typeDocument"
					name="champRechercheLivreParTypeDocument"
					from="${biblioj.TypeDocument.list()}"
					value="${params?.champRechercheLivreParTypeDocument}"
					noSelection="['':'- Choose a type -']" />
			</span>
			<fieldset class="buttons">
				<g:actionSubmit class="formulaireBoutonSubmit" action="list"
					value="Submit" />
			</fieldset>
		</g:form>

		<ul>
			<li
				class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'dateReservation', 'error')} required">
				<label for="dateReservation"> <g:message
						code="reservation.dateReservation.label"
						default="Date Reservation" /> <span class="required-indicator">*</span>
			</label> <g:datePicker name="dateReservation" precision="day"
					value="${reservationInstance?.dateReservation}" />
			</li>
			<li><g:link class="buttons"
					style="text-decoration: none; color: black"
					controller="reservation" action="saveLivre"
					id="${reservationInstance?.id}">
						Sauvegarder la reservation
					</g:link></li>
		</ul>
	</div>
	<div id="list-livre" class="content scaffold-list" role="main">
		<h1>
			<g:message code="default.list.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<table>
			<thead>
				<tr>
					<g:sortableColumn property="titre"
						title="${message(code: 'livre.titre.label', default: 'Titre')}" />
					<g:sortableColumn property="nombreExemplaires"
						title="${message(code: 'livre.nombreExemplaires.label', default: 'Nombre Exemplaires')}" />
					<g:sortableColumn property="nombreExemplairesDisponibles"
						title="${message(code: 'livre.nombreExemplairesDisponibles.label', default: 'Nombre Exemplaires Disponibles')}" />
					<th><g:message code="livre.typeDocument.label"
							default="Type Document" /></th>
					<th>Réservation</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${livreInstanceList}" status="i" var="livreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${livreInstance.id}">
								${fieldValue(bean: livreInstance, field: "titre")}
							</g:link></td>
						<td>
							${fieldValue(bean: livreInstance, field: "nombreExemplaires")}
						</td>
						<td>
							${fieldValue(bean: livreInstance, field: "nombreExemplairesDisponibles")}
						</td>
						<td>
							${fieldValue(bean: livreInstance, field: "typeDocument")}
						</td>
						<td><g:if
								test="${session['user'].find { it.id == livreInstance.id }}">Déjà ajouté</g:if>
							<g:else>
								<g:if test="${livreInstance.nombreExemplairesDisponibles > 0}">
									<g:link class="buttons"
										style="text-decoration: none; color: black"
										controller="reservation" action="addLivre"
										id="${livreInstance?.id}">Ajouter</g:link>
								</g:if>
								<g:else>
									<p>Rupture de stock</p>
								</g:else>
							</g:else></td>

					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<tr>
				<td><g:paginate total="${livreInstanceTotal}" /></td>
			</tr>
		</div>
	</div>
</body>
</html>
