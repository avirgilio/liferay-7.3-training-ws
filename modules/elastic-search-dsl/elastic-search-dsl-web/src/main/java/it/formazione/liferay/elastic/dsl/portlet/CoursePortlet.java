package it.formazione.liferay.elastic.dsl.portlet;

import it.formazione.liferay.elastic.dsl.constants.CoursePortletKeys;

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
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Course Portlet",
		"javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.css-class-wrapper=elastic-search-dsl-web",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CoursePortletKeys.COURSE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CoursePortlet extends MVCPortlet {
}