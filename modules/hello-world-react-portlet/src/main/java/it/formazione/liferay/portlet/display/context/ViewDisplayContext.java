package it.formazione.liferay.portlet.display.context;

import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import it.formazione.liferay.constants.HelloWorldReactPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.HashMap;
import java.util.Map;

public class ViewDisplayContext {

    public ViewDisplayContext(
        RenderRequest renderRequest, RenderResponse renderResponse) {

        _renderRequest = renderRequest;
        _renderResponse = renderResponse;
    }

    public Map<String, Object> getReactData() {

        HashMap<String, Object> displayUserInfoPropsMap = HashMapBuilder
            .<String, Object>put("name", "Alessandro")
            .put("surname", "Virgilio")
            .put("age", 26)
            .build();

        HashMap<String, Object> contextMap = HashMapBuilder
            .<String, Object>put("spritemap", _getClaySpriteMap())
            .put("baseResourceURL", _getPortletBaseResourceURL().toString())
            .put("baseActionURL", _getPortletBaseActionURL().toString())
            .put("baseRenderURL", _getPortletBaseRenderURL().toString())
            .build();

        return HashMapBuilder
            .<String, Object>put("context", contextMap)
            .put("displayUserInfoProps", displayUserInfoPropsMap)
            .build();
    }

    private LiferayPortletURL _getPortletBaseRenderURL() {
        return _renderResponse.createRenderURL();
    }

    private LiferayPortletURL _getPortletBaseActionURL() {
        return _getPortletBaseURL(PortletRequest.ACTION_PHASE);
    }

    private LiferayPortletURL _getPortletBaseResourceURL()  {
        return _getPortletBaseURL(PortletRequest.RESOURCE_PHASE);
    }

    private LiferayPortletURL _getPortletBaseURL(String portletRequest)  {

        return PortletURLFactoryUtil.create(
            _renderRequest, HelloWorldReactPortletKeys.HELLO_WORLD_REACT,
            _getThemeDisplay().getPlid(), portletRequest);
    }

    private String _getClaySpriteMap() {
      return _getThemeDisplay().getPathThemeImages() + "/clay/icons.svg";
    }

    private ThemeDisplay _getThemeDisplay() {
        return (ThemeDisplay)
            _renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
    }

    private final RenderResponse _renderResponse;
    private final RenderRequest _renderRequest;
}
