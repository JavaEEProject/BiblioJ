<%@ page import="biblioj.Auteur" %>



<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="auteur.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" maxlength="30" required="" value="${auteurInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'prenom', 'error')} required">
	<label for="prenom">
		<g:message code="auteur.prenom.label" default="Prenom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prenom" maxlength="30" required="" value="${auteurInstance?.prenom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'livresEcrit', 'error')} ">
	<label for="livresEcrit">
		<g:message code="auteur.livresEcrit.label" default="Livres Ecrit" />
		
	</label>
	<g:select name="livresEcrit" from="${biblioj.Livre.list()}" multiple="multiple" optionKey="id" size="5" value="${auteurInstance?.livresEcrit*.id}" class="many-to-many"/>
</div>

