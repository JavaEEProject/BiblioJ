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

<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'livres', 'error')} ">
	<label for="livres">
		<g:message code="auteur.livres.label" default="Livres" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${auteurInstance?.livres?}" var="l">
    <li><g:link controller="livre" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="livre" action="create" params="['auteur.id': auteurInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'livre.label', default: 'Livre')])}</g:link>
</li>
</ul>

</div>

