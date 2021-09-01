package my.todo.web.portlet.display.context;

import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import my.todo.web.constants.MyTodoWebPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Virgilio Alessandro 30/ago/2021
 **/
public class TodoAppDisplayContext {

	public TodoAppDisplayContext(RenderRequest renderRequest) {

		_renderRequest = renderRequest;
	}

	public Map<String, Object> getReactProps() {

		HashMap<String, Object> contextMap = HashMapBuilder
			.<String, Object>put("spritemap", _getClaySpriteMap())
			.put("baseResourceURL", _getPortletBaseResourceURL().toString())
			.build();

		return HashMapBuilder.
			<String, Object>put("context", contextMap)
			.build();
	}

	private LiferayPortletURL _getPortletBaseResourceURL()  {
		return _getPortletBaseURL(PortletRequest.RESOURCE_PHASE);
	}

	private LiferayPortletURL _getPortletBaseURL(String portletRequest)  {

		return PortletURLFactoryUtil.create(
			_renderRequest, MyTodoWebPortletKeys.MY_TODO_WEB,
			_getThemeDisplay().getPlid(), portletRequest);
	}

	private String _getClaySpriteMap() {
		return _getThemeDisplay().getPathThemeImages() + "/clay/icons.svg";
	}

	private ThemeDisplay _getThemeDisplay() {
		return (ThemeDisplay)
			_renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	}

	private final RenderRequest _renderRequest;
}
