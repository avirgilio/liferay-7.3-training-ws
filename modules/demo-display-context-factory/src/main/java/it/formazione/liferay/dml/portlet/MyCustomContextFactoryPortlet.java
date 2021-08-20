package it.formazione.liferay.dml.portlet;

import it.formazione.liferay.dml.constants.MyCustomContextFactoryPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessandro Virgilio
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.formazione",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.false=true",
		"javax.portlet.display-name=MyCustomContextFactory",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" +
			MyCustomContextFactoryPortletKeys.MY_CUSTOM_CONTEXT_FACTORY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MyCustomContextFactoryPortlet extends MVCPortlet {
}