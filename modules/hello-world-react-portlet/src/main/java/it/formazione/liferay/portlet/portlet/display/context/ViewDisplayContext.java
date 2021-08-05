package it.formazione.liferay.portlet.portlet.display.context;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import it.formazione.liferay.portlet.constants.HelloWorldReactPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import java.util.Map;

public class ViewDisplayContext {

    public ViewDisplayContext(RenderRequest renderRequest) {

        _renderRequest = renderRequest;
    }

    public Map<String, Object> getReactData() {
        return HashMapBuilder
                .<String, Object>put("name", "Alessandro")
                .put("surname", "Virgilio")
                .put("age", 26)
                .put("spritemap", _getClaySpriteMap())
                .put("baseResourceURL", _getPortletBaseResourceURL())
                .build();
    }

    private String _getPortletBaseResourceURL() {

       return PortletURLFactoryUtil.create(
            _renderRequest, HelloWorldReactPortletKeys.HELLO_WORLD_REACT,
            _getThemeDisplay().getPlid(),
            PortletRequest.RESOURCE_PHASE).toString();
    }

    private String _getClaySpriteMap() {
      return _getThemeDisplay().getPathThemeImages() + "/clay/icons.svg";
    }

    private ThemeDisplay _getThemeDisplay() {
        return (ThemeDisplay) _renderRequest.getAttribute(
            WebKeys.THEME_DISPLAY);
    }

    private final RenderRequest _renderRequest;
}
