<%@ page import="biblioj.Panier" %>



<div class="fieldcontain ${hasErrors(bean: panierInstance, field: 'idSession', 'error')} ">
	<label for="idSession">
		<g:message code="panier.idSession.label" default="Id Session" />
		
	</label>
	<g:textField name="idSession" value="${panierInstance?.idSession}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: panierInstance, field: 'livre', 'error')} ">
	<label for="livre">
		<g:message code="panier.livre.label" default="Livre" />
		
	</label>
	<g:select name="livre" from="${biblioj.Livre.list()}" multiple="multiple" optionKey="id" size="5" value="${panierInstance?.livre*.id}" class="many-to-many"/>
</div>

