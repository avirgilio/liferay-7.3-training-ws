
<%@ include file="/init.jsp" %>

<%
	ViewDisplayContext displayContext = (ViewDisplayContext) request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<p>
	<b><liferay-ui:message key="helloworldreact.caption"/></b>
</p>

<div id="<portlet:namespace />my-react-component-wrapper">
	<react:component
			componentId="<portlet:namespace />-user-viewer"
			module="js/DisplayUserInfoComponent"
			props="<%= displayContext.getReactData() %>"
	/>
</div>