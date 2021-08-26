package it.formazione.liferay.portlet.actions;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import it.formazione.liferay.constants.HelloWorldReactPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Virgilio Alessandro 25/ago/2021
 **/

@Component(
	property = {
		"javax.portlet.name=" + HelloWorldReactPortletKeys.HELLO_WORLD_REACT,
		"mvc.command.name=demoRenderCommand"
	},
	service = MVCRenderCommand.class
)
public class DemoMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		return "/myRenderCommandView.jsp";
	}
}
