<%@ include file="/init.jsp" %>

<div class="container">
	<div class="row justify-content-md-center">
		<div class="col-md-auto">
			<h1> <b><liferay-ui:message key="mytodoweb.caption"/></b> </h1>
		</div>
	</div>
</div>

<div id="todo-component-wrapper">
	<react:component
		module="js/App"
	/>
</div>