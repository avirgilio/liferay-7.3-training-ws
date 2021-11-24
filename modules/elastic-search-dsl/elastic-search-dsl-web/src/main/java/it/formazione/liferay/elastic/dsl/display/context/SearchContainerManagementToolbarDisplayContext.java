package it.formazione.liferay.elastic.dsl.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author Alessandro Virgilio
 */
public abstract class SearchContainerManagementToolbarDisplayContext<T>
	extends BaseManagementToolbarDisplayContext {

	public SearchContainerManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest request)
		throws PortalException {

		super(request, liferayPortletRequest, liferayPortletResponse);

		this.themeDisplay = (ThemeDisplay) liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		this.searchContainer = createSearchContainer();
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

	@Override
	public Boolean isDisabled() {
		return getItemsTotal() == 0 && noFilterSelected();
	}

	protected boolean noFilterSelected() {
		return "all".equals(getNavigation());
	}

	protected SearchContainer<T> createSearchContainer()
		throws PortalException {

		SearchContainer<T> searchContainer = new SearchContainer<>(
			liferayPortletRequest,
			getPortletURL(), getHeaderNames(), getEmptyResultsMessage());

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

	protected List<String> getHeaderNames() {
		return Collections.emptyList();
	}

	protected String getEmptyResultsMessage() {
		return "no-entries-were-found";
	}

	protected int getTotal() throws PortalException {
		return 0;
	}

	protected List<T> getResults(
			int start, int end, String orderByCol, boolean orderByType)
		throws PortalException {

		return Collections.emptyList();
	}

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
