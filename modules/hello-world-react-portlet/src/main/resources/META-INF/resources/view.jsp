
<%@ include file="/init.jsp" %>

<%
	ViewDisplayContext displayContext = (ViewDisplayContext) request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="container">
	<div class="row justify-content-md-center">
		<div class="col-md-auto">
			<h1> <b><liferay-ui:message key="helloworldreact.caption"/></b> </h1>
		</div>
	</div>
</div>

<div id="<portlet:namespace />my-react-component-wrapper">
	<react:component
			componentId="<portlet:namespace />-user-viewer"
			module="js/index"
			props="<%= displayContext.getReactData() %>"
	/>
</div>