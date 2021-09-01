<%@ include file="/init.jsp" %>

<%
	TodoAppDisplayContext displayContext = (TodoAppDisplayContext) request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="container">
	<div class="row justify-content-md-center">
		<div class="col-md-auto">
			<h1> <b><liferay-ui:message key="mytodoweb.caption"/></b> </h1>
		</div>
	</div>
</div>

<div id="todo-component-wrapper">
	<react:component
		module="js/TodoApp"
		props="<%= displayContext.getReactProps() %>"
	/>
</div>