<%@ page import="com.liferay.petra.string.StringPool" %>
<%@ include file="/init.jsp" %>

<%
	long courseId = 0;
	Course course = null;

	if (request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW) != null) {

		ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		course = (Course) row.getObject();

		if (course != null) {
			courseId = course.getCourseId();
		}
	}

	PortletURL mainPortletURL= renderResponse.createRenderURL();
	mainPortletURL.setParameter("mvcPath", "/view.jsp");
	String viewPortletURL = mainPortletURL.toString();
%>


<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editCourseURL">
		<portlet:param name="mvcRenderCommandName" value="/course/edit" />
		<portlet:param name="backURL" value="<%= viewPortletURL %>" />
		<portlet:param name="courseId" value="<%= String.valueOf(courseId) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		label="<%= true %>"
		url="<%= editCourseURL %>"
	/>

	<liferay-portlet:actionURL name="/course/delete_course" var="deleteURL">
		<portlet:param name="courseId" value="<%= String.valueOf(courseId) %>" />
		<portlet:param name="redirect" value="<%= viewPortletURL %>" />
	</liferay-portlet:actionURL>

	<liferay-ui:icon-delete
		icon="trash"
		showIcon="<%= true %>"
		label="<%= true %>"
		url="<%= deleteURL %>"
	/>

</liferay-ui:icon-menu>