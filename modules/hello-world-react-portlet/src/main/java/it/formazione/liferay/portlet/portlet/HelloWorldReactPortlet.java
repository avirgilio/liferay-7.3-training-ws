package it.formazione.liferay.portlet.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.WebKeys;
import it.formazione.liferay.portlet.constants.HelloWorldReactPortletKeys;
import it.formazione.liferay.portlet.portlet.display.context.ViewDisplayContext;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author Alessandro Virgilio
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.formazione",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Hello World React",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + HelloWorldReactPortletKeys.HELLO_WORLD_REACT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class HelloWorldReactPortlet extends MVCPortlet {

	@Override
	public void render(
				RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ViewDisplayContext viewDisplayContext =
			new ViewDisplayContext(renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, viewDisplayContext);

		super.render(renderRequest, renderResponse);
	}
}