package it.formazione.liferay.elastic.dsl.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import it.formazione.liferay.elastic.dsl.constants.CoursePortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CoursePortletKeys.COURSE,
		"mvc.command.name=/course/add",
		"mvc.command.name=/course/edit"
	},
	service = MVCRenderCommand.class
)
public class AddCourseMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		return "/course/add-course.jsp";
	}
}
