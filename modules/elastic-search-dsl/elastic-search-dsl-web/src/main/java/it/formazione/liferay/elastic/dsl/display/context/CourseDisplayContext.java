package it.formazione.liferay.elastic.dsl.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import it.formazione.liferay.elastic.dsl.constants.CourseDisplayConstants;
import it.formazione.liferay.elastic.dsl.constants.search.CourseSearchField;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.model.CourseTypeAggregationResult;
import it.formazione.liferay.elastic.dsl.search.CourseSearcherUtil;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Alessandro Virgilio
 * */
public class CourseDisplayContext extends BaseDisplayContext<Course> {

	public CourseDisplayContext(
			LiferayPortletRequest liferayRenderRequest,
			LiferayPortletResponse liferayRenderResponse,
			HttpServletRequest request)
		throws PortalException {

		super(liferayRenderRequest, liferayRenderResponse, request);
	}

	@Override
	protected String getEmptyResultsMessage() {
		return CourseDisplayConstants.DISPLAY_EMPTY_RESULT_MESSAGE;
	}

	@Override
	protected int getTotal() throws PortalException {

		_filterCourseType = ParamUtil.getString(
			request, FILTER_COURSE_TYPE, DEFAULT_COURSE_TYPE);

		_hasCourseTypeFilter =
			!_filterCourseType.equals(DEFAULT_COURSE_TYPE);

		String searchKeyword =
			ParamUtil.getString(request, DisplayTerms.KEYWORDS);

		_searchKeyword = searchKeyword;

		List<CourseType> courseTypeFilterList = new ArrayList<>();

		if (_hasCourseTypeFilter) {
			courseTypeFilterList.add(
				CourseType.getCourseTypeFromValue(
					Integer.parseInt(_filterCourseType)));
		}

		CourseType[] courseTypesFilter = courseTypeFilterList.toArray(
			new CourseType[0]);

		return (int) CourseSearcherUtil.searchCount(
				courseTypesFilter, searchKeyword, themeDisplay.getCompanyId());
	}

	@Override
	public CreationMenu getCreationMenu() {
		return new CreationMenu() {
			{
				addPrimaryDropdownItem(
					dropdownItem -> {

						PortletURL mainPortletURL =
							liferayPortletResponse.createRenderURL();

						mainPortletURL.setParameter("mvcPath", "/view.jsp");

						dropdownItem.setHref(
							liferayPortletResponse.createRenderURL(),
							"mvcRenderCommandName",
							"/course/add",
							"redirect", mainPortletURL.toString());

						dropdownItem.setLabel(
							LanguageUtil.get(request, "add-course"));
					});
			}
		};
	}

	@Override
	protected List<Course> getResults(
			int start, int end, String orderByCol, boolean orderByType)
		throws PortalException {

		List<CourseType> courseTypeFilterList = new ArrayList<>();

		_filterCourseOrder = ParamUtil.getString(
			request, FILTER_COURSE_ORDER, CourseSearchField.FIELD_COURSE_NAME);

		if (_hasCourseTypeFilter) {
			courseTypeFilterList.add(
				CourseType.getCourseTypeFromValue(
					Integer.parseInt(_filterCourseType)));
		}

		CourseType[] courseTypesFilter = courseTypeFilterList.toArray(
			new CourseType[0]);

		return CourseSearcherUtil.search(
				courseTypesFilter, _searchKeyword,
				themeDisplay.getCompanyId(), start, end, _filterCourseOrder)
			.getSearchResults();
	}

	@Override
	public List<DropdownItem> getFilterDropdownItems() {
		return new DropdownItemList() {
			{

				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							getCourseTypeDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(request, "course-type"));
					});

				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							getOrderByDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(request, "order-by"));
					});

			}
		};
	}

	@Override
	public String getSortingOrder() {
		return super.getSortingOrder();
	}

	@Override
	protected Map<String, String> getOrderByEntriesMap() {
		return new LinkedHashMap<String, String>() {{
			put("course-name", CourseSearchField.FIELD_COURSE_NAME);
			put("course-description", CourseSearchField.FIELD_COURSE_DESCRIPTION);
		}};
	}

	@Override
	public String getSortingURL() {

		PortletURL sortingURL = getPortletURL();

		sortingURL.setParameter(
			getOrderByTypeParam(),
			Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

		return sortingURL.toString();
	}

	@Override
	protected String getOrderByColParam() {
		return FILTER_COURSE_ORDER;
	}

	@Override
	protected String getOrderByCol() {
		return _filterCourseOrder;
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	protected List<DropdownItem> getCourseTypeDropdownItems() {

		Map<String, String> coursesTypes = new LinkedHashMap<>();

		coursesTypes.put(DEFAULT_COURSE_TYPE, DEFAULT_COURSE_TYPE);

		try {

			List<CourseTypeAggregationResult> aggr =
				CourseSearcherUtil.getCourseTypes(
					themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId());

			for (
				CourseTypeAggregationResult courseTypeAggregationResult : aggr) {

				CourseType courseType =
					courseTypeAggregationResult.getCourseType();

				ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
					"content.Language", themeDisplay.getLocale(),
					getClass());

				String newLabel = LanguageUtil.format(
					resourceBundle, "course-type-x-of-x",
					new Object[]{
						courseType.getLabel(),
						courseTypeAggregationResult.getCount()
					});

				coursesTypes.put(
					newLabel, String.valueOf(courseType.getValue()));
			}

		}
		catch (PortalException e) {
			_log.error(e, e);
		}

		return getDropdownItems(
			coursesTypes, getPortletURL(),
			FILTER_COURSE_TYPE, _filterCourseType);
	}

	public String getCourseTypeLabel(int value) {
		return CourseType.getCourseTypeFromValue(value).getLabel();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CourseDisplayContext.class);

	private static final String FILTER_COURSE_TYPE = "filter-course-type";
	private static final String FILTER_COURSE_ORDER = "filter-course-order";

	private String _filterCourseType;
	private String _filterCourseOrder;

	private String _searchKeyword;

	private boolean _hasCourseTypeFilter = false;

	private static final String DEFAULT_COURSE_TYPE = "all";
}
