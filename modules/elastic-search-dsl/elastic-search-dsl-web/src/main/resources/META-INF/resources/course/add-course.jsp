
<%@ include file="/init.jsp" %>

<%
	long courseId = ParamUtil.getLong(request, "courseId");
	String redirect = ParamUtil.getString(request, "redirect");
	String backURL = ParamUtil.getString(request, "backURL", redirect);

	boolean isAddMode = courseId == 0;

	String title = "add-course";

	Course course = null;

	if (!isAddMode) {
		course = CourseServiceUtil.getCourse(courseId);
		title = "edit-course";
	}
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="<%= title %>"
/>

<liferay-portlet:actionURL name="/course/add_course" var="addCourseURL" />

<aui:form
	action="<%= addCourseURL %>"
	name="fm"
	method="post"
	cssClass="container-fluid-1280"
>

	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="courseId" type="hidden" value="<%= courseId %>" />

	<aui:model-context bean="<%= course %>" model="<%= Course.class %>" />

	<aui:fieldset-group markupView="lexicon">

		<aui:fieldset>

			<aui:row>
				<aui:col span="<%= 12 %>">
					<aui:input name="courseName" label="course-name"/>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col span="<%= 12 %>">
					<aui:input name="courseDescription" label="course-description" />
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col span="<%= 12 %>">
					<aui:select name="courseType" label="course-type">

						<%
							for (CourseType courseType : CourseType.values()) {
						%>

							<aui:option
								label="<%= courseType.getLabel() %>"
								selected="<%= (course != null) && (courseType.getValue() != course.getCourseType()) %>"
								value="<%= courseType.getValue() %>"
							/>

						<%
							}
						%>

					</aui:select>
				</aui:col>
			</aui:row>
		</aui:fieldset>

	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>

</aui:form>
