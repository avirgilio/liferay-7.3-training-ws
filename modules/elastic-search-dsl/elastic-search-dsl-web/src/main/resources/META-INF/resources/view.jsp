
<%@ include file="/init.jsp" %>

<%
	CourseDisplayContext courseDisplayContext = new CourseDisplayContext(
		liferayPortletRequest, liferayPortletResponse, request);
%>

<clay:management-toolbar
	componentId="courseManagementToolbar"
	selectable="<%= false %>"
	itemsTotal="<%= courseDisplayContext.getItemsTotal() %>"
	disabled="<%= courseDisplayContext.isDisabled() %>"
	clearResultsURL="<%= courseDisplayContext.getClearResultsURL() %>"
	creationMenu="<%= courseDisplayContext.getCreationMenu() %>"
	showCreationMenu="<%= courseDisplayContext.isShowCreationMenu() %>"
	showSearch="<%= true %>"
	searchFormName="courseManagementForm"
	searchContainerId="courseManagementSearchContainer"
	searchActionURL="<%= courseDisplayContext.getSearchActionURL() %>"
	searchInputName="<%= DisplayTerms.KEYWORDS %>"
	searchValue="<%= ParamUtil.getString(request, DisplayTerms.KEYWORDS) %>"
	filterDropdownItems="<%= courseDisplayContext.getFilterDropdownItems() %>"
	sortingOrder="<%= courseDisplayContext.getSortingOrder() %>"
	sortingURL="<%= courseDisplayContext.getSortingURL() %>"
/>

<div class="container-fluid-1280">

	<liferay-ui:search-container
		id="coursesSearchContainer"
		searchContainer="<%= courseDisplayContext.getSearchContainer() %>"
	>

		<liferay-ui:search-container-row
			className="it.formazione.liferay.elastic.dsl.model.Course"
			keyProperty="courseId"
			modelVar="course"
		>

			<liferay-ui:search-container-column-text
				name="courseName"
				property="courseName"
			/>

			<liferay-ui:search-container-column-text
				name="courseDescription"
				property="courseDescription"
			/>

			<liferay-ui:search-container-column-text
				name="courseType"
				cssClass="course-type-column"
				value="<%= courseDisplayContext.getCourseTypeLabel(course.getCourseType()) %>"
			/>

			<liferay-ui:search-container-column-jsp
				cssClass="entry-action-column"
				path="/course/course-action.jsp"
			/>

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="list" markupView="lexicon" />
	</liferay-ui:search-container>

</div>