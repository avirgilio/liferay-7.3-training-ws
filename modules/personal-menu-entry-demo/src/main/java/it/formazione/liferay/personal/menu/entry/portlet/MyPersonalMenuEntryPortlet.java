package it.formazione.liferay.personal.menu.entry.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import it.formazione.liferay.personal.menu.entry.constants.MyPersonalMenuEntryPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

/**
 * @author Alessandro Virgilio
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=My Personal Menu Entry",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MyPersonalMenuEntryPortletKeys.MY_PERSONAL_MENU_ENTRY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MyPersonalMenuEntryPortlet extends MVCPortlet {
}