package it.formazione.liferay.elastic.dsl.display.context;

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
import com.liferay.portal.kernel.util.Validator;
import it.formazione.liferay.elastic.dsl.constants.CourseDisplayConstants;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.model.CourseSearchResult;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.search.CourseSearcherUtil;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

		_hasSearchKeyword = Validator.isNotNull(searchKeyword);

		List<CourseType> courseTypeFilterList = new ArrayList<>();

		if (_hasCourseTypeFilter) {
			courseTypeFilterList.add(
				CourseType.getCourseTypeFromValue(
					Integer.parseInt(_filterCourseType)));
		}

		CourseType[] courseTypesFilter = courseTypeFilterList.toArray(
			new CourseType[0]);

		_courseSearchResult =
			CourseSearcherUtil.search(
				courseTypesFilter, searchKeyword, themeDisplay.getCompanyId());

		return (int) _courseSearchResult.getSearchCount();
	}

	@Override
	protected List<String> getHeaderNames() {
		return Collections.singletonList("courses");
	}

	@Override
	protected List<Course> getResults(
			int start, int end, String orderByCol, boolean orderByType)
		throws PortalException {

		return _courseSearchResult.getSearchResults();
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
			}
		};
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	protected List<DropdownItem> getCourseTypeDropdownItems() {

		Map<String, String> coursesTypes = new LinkedHashMap<>();

		coursesTypes.put(DEFAULT_COURSE_TYPE, DEFAULT_COURSE_TYPE);

		for (CourseType courseType : CourseType.values()) {
			coursesTypes.put(
				courseType.getLabel(),
				String.valueOf(courseType.getValue())
			);
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
	private String _filterCourseType;

	private CourseSearchResult _courseSearchResult;

	private boolean _hasSearchKeyword = false;
	private String _searchKeyword;

	private boolean _hasCourseTypeFilter = false;

	private static final String DEFAULT_COURSE_TYPE = "all";
}
