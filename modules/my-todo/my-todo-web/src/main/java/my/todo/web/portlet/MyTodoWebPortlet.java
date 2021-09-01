package my.todo.web.portlet;

import com.liferay.portal.kernel.util.WebKeys;
import my.todo.web.constants.MyTodoWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import my.todo.web.portlet.display.context.TodoAppDisplayContext;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author alessandro.virgilio
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=MyTodoWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MyTodoWebPortletKeys.MY_TODO_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.css-class-wrapper=my-todo-web",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MyTodoWebPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		TodoAppDisplayContext displayContext =
			new TodoAppDisplayContext(renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, displayContext);

		super.render(renderRequest, renderResponse);
	}
}