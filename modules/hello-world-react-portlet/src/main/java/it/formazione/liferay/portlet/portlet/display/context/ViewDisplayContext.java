package it.formazione.liferay.portlet.portlet.display.context;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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
                .build();
    }

    private String _getClaySpriteMap() {
      return _getThemeDisplay().getPathThemeImages() + "/clay/icons.svg";
    }

    private ThemeDisplay _getThemeDisplay() {
        return (ThemeDisplay) _renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
    }

    private final RenderRequest _renderRequest;
}
