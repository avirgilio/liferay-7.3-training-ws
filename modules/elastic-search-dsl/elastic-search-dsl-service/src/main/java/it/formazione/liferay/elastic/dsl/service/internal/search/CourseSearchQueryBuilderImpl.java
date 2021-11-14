package it.formazione.liferay.elastic.dsl.service.internal.search;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.query.TermQuery;
import com.liferay.portal.search.query.WildcardQuery;
import it.formazione.liferay.elastic.dsl.constants.search.CourseSearchField;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.search.CourseSearchQueryBuilder;
import org.osgi.service.component.annotations.Component;

/**
 * Sort and setAttributes(??)
 * @see com.liferay.account.internal.search.searcher.UserSearchRequestBuilder#_populateSearchContext(com.liferay.portal.kernel.search.SearchContext)
 * */
@Component(immediate = true, service = CourseSearchQueryBuilder.class)
public class CourseSearchQueryBuilderImpl implements CourseSearchQueryBuilder {

	public CourseSearchQueryBuilderImpl(){}

	public CourseSearchQueryBuilderImpl(Queries queries) {
		_queries = queries;
		_mainBooleanQuery = _queries.booleanQuery();
		_keywordsTermQuery = _queries.booleanQuery();
		_courseTypesQuery = _queries.booleanQuery();
	}

	@Override
	public CourseSearchQueryBuilder addSearchKeywordFilter(String keyword) {

		boolean hasKeyword = !Validator.isBlank(keyword);

		if (hasKeyword) {

			WildcardQuery courseNameWildCard =
				_queries.wildcard(
					CourseSearchField.FIELD_COURSE_NAME,
					"*" + keyword.toLowerCase() + "*");

			WildcardQuery courseDescriptionWildCard =
				_queries.wildcard(
					CourseSearchField.FIELD_COURSE_DESCRIPTION,
					"*" + keyword.toLowerCase() + "*");

			_keywordsTermQuery.addShouldQueryClauses(courseNameWildCard);
			_keywordsTermQuery.addShouldQueryClauses(courseDescriptionWildCard);
		}

		return this;
	}

	@Override
	public CourseSearchQueryBuilder addCourseTypeFilter(CourseType courseType) {

		TermQuery newCourseTypeFilter = _queries.term(
			CourseSearchField.FIELD_COURSE_TYPE,
			courseType.getValue());

		_courseTypesQuery.addShouldQueryClauses(newCourseTypeFilter);

		return this;
	}

	@Override
	public CourseSearchQueryBuilder addCourseTypesFilter(
		CourseType[] courseTypes) {

		boolean hasCourseTypeFilter = ArrayUtil.isNotEmpty(courseTypes);

		if (hasCourseTypeFilter) {

			for (CourseType courseType : courseTypes) {

				TermQuery match = _queries.term(
					CourseSearchField.FIELD_COURSE_TYPE, courseType.getValue());

				_courseTypesQuery.addShouldQueryClauses(match);
			}
		}

		return this;
	}

	@Override
	public Query build() {
		return _mainBooleanQuery
			.addMustQueryClauses(_keywordsTermQuery)
			.addMustQueryClauses(_courseTypesQuery);
	}

	private BooleanQuery _keywordsTermQuery;
	private BooleanQuery _mainBooleanQuery;
	private BooleanQuery _courseTypesQuery;

	private Queries _queries;
}
