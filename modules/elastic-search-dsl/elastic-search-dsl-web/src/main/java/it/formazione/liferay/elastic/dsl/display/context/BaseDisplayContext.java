package it.formazione.liferay.elastic.dsl.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Alessandro Virgilio
 * */
public abstract class BaseDisplayContext<T>
	extends SearchContainerManagementToolbarDisplayContext<T> {

	public BaseDisplayContext(
			LiferayPortletRequest liferayRenderRequest,
			LiferayPortletResponse liferayRenderResponse,
			HttpServletRequest request)
		throws PortalException {

		super(liferayRenderRequest, liferayRenderResponse, request);
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);
		clearResultsURL.setParameter("navigation", "all");

		return clearResultsURL.toString();
	}

	public List<NavigationItem> getNavigationItems() {

		ResourceBundle resourceBundle =
			ResourceBundleUtil.getBundle(themeDisplay.getLocale(), getClass());

		return new NavigationItemList() {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(
							Objects.equals(getTabs1(), "courses"));
						navigationItem.setHref(
							getCoursesURL(), "tabs1", "courses");
						navigationItem.setLabel(
							LanguageUtil.get(resourceBundle, "courses"));
					});
			}
		};
	}

	public PortletURL getCoursesURL() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/view.jsp");
		portletURL.setParameter("tabs1", getTabs1());

		return portletURL;
	}

	public String getTabs1() {
		if (_tabs1 != null) {
			return _tabs1;
		}

		_tabs1 = ParamUtil.getString(request, "tabs1", "locations");

		return _tabs1;
	}

	private String _tabs1;
}
