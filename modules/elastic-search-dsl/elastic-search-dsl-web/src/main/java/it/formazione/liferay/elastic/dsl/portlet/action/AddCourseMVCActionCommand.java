package it.formazione.liferay.elastic.dsl.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import it.formazione.liferay.elastic.dsl.constants.CoursePortletKeys;
import it.formazione.liferay.elastic.dsl.service.CourseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CoursePortletKeys.COURSE,
		"mvc.command.name=/course/add_course"
	},
	service = MVCActionCommand.class
)
public class AddCourseMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		int courseId = ParamUtil.getInteger(actionRequest, "courseId");

		String courseName = ParamUtil.getString(actionRequest, "courseName");

		String courseDescription =
			ParamUtil.getString(actionRequest, "courseDescription");

		int courseType = ParamUtil.getInteger(actionRequest, "courseType");

		boolean isAddMode = courseId == 0;

		if (isAddMode) {
			_courseService.createCourse(
				courseName, courseDescription, courseType);
		}
		else {
			_courseService.updateCourse(
				courseId, courseName, courseDescription, courseType);
		}

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	@Reference
	private CourseService _courseService;
}
