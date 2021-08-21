package it.formazione.liferay.dml.portlet.display.context.documents;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author Alessandro Virgilio
 * */
public abstract class SearchContainerManagementToolbarDisplayContext<T>
	extends BaseManagementToolbarDisplayContext {

	protected SearchContainerManagementToolbarDisplayContext(
			HttpServletRequest request,
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		super(request, liferayPortletRequest, liferayPortletResponse);

		this.themeDisplay = (ThemeDisplay) liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	@Override
	public int getItemsTotal() {
		return searchContainer.getTotal();
	}

	public SearchContainer<T> getSearchContainer() {
		return searchContainer;
	}

	@Override
	public String getSearchContainerId() {
		return searchContainer.getId(request, getNamespace());
	}

	protected SearchContainer<T> createSearchContainer()
		throws PortalException {

		SearchContainer<T> searchContainer =
			new SearchContainer<>(
				liferayPortletRequest, getPortletURL(), Collections.emptyList(),
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"no-favorite-documents-available"));

		searchContainer.setTotal(getTotal());

		String orderByType = searchContainer.getOrderByType();

		orderByType = orderByType == null ? "asc" : orderByType;

		searchContainer.setResults(
			getResults(
				searchContainer.getStart(),
				searchContainer.getEnd(),
				searchContainer.getOrderByCol(),
				orderByType.equals("asc")));

		return searchContainer;
	}

	protected abstract int getTotal() throws PortalException;

	protected abstract List<T> getResults(
			int start, int end, String orderByCol, boolean orderByType)
		throws PortalException;

	@Override
	protected String getOrderByCol() {
		return searchContainer.getOrderByCol();
	}

	@Override
	protected String getOrderByColParam() {
		return searchContainer.getOrderByColParam();
	}

	@Override
	protected String getOrderByType() {
		return searchContainer.getOrderByType();
	}

	@Override
	protected String getOrderByTypeParam() {
		return searchContainer.getOrderByTypeParam();
	}

	protected SearchContainer<T> searchContainer;
	protected ThemeDisplay themeDisplay;

}
