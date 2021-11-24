package it.formazione.liferay.elastic.dsl.portlet.action;


import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import it.formazione.liferay.elastic.dsl.constants.CoursePortletKeys;
import it.formazione.liferay.elastic.dsl.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CoursePortletKeys.COURSE,
		"mvc.command.name=/course/delete_course"
	},
	service = MVCActionCommand.class
)
public class DeleteCourseMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long courseId = ParamUtil.getLong(actionRequest, "courseId");

		_courseLocalService.deleteCourse(courseId);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	@Reference
	private CourseLocalService _courseLocalService;
}
