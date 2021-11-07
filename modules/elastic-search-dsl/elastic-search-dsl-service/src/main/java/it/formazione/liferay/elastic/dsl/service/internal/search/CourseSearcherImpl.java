package it.formazione.liferay.elastic.dsl.service.internal.search;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermQuery;
import com.liferay.portal.search.query.WildcardQuery;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import it.formazione.liferay.elastic.dsl.constants.search.CourseSearchField;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.model.CourseSearchResult;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.search.CourseSearcher;
import it.formazione.liferay.elastic.dsl.service.CourseLocalService;
import it.formazione.liferay.elastic.dsl.service.internal.search.hits.CourseSearchHitsGetter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = CourseSearcher.class)
public class CourseSearcherImpl implements CourseSearcher {

	@Override
	public CourseSearchResult search(
		CourseType[] courseTypes, String keyword, long companyId,
		int start, int end) {

		BooleanQuery mainBooleanQuery = _queries.booleanQuery();

		_handleCourseTypeFilter(courseTypes, mainBooleanQuery);

		_handleKeywordFilter(keyword, mainBooleanQuery);

		SearchRequest request =
			_searchRequestBuilderFactory
				.builder()
				.emptySearchEnabled(true)
				.modelIndexerClasses(Course.class)
				.entryClassNames(Course.class.getName())
				.withSearchContext(
					searchContext -> {
						searchContext.setCompanyId(companyId);
						searchContext.setStart(start);
						searchContext.setEnd(end);
					})
				.query(mainBooleanQuery)
				.build();

		return _searcher
			.search(request)
			.withHitsGet(new CourseSearchHitsGetter());
	}

	@Override
	public long searchCount(
		CourseType[] courseTypes, String keyword, long companyId) {

		BooleanQuery mainBooleanQuery = _queries.booleanQuery();

		_handleCourseTypeFilter(courseTypes, mainBooleanQuery);
		_handleKeywordFilter(keyword, mainBooleanQuery);

		SearchRequest request =
			_searchRequestBuilderFactory
				.builder()
				.emptySearchEnabled(true)
				.modelIndexerClasses(Course.class)
				.entryClassNames(Course.class.getName())
				.withSearchContext(
					searchContext -> {
						searchContext.setCompanyId(companyId);
					})
				.query(mainBooleanQuery)
				.build();

		SearchResponse searchResponse = _searcher.search(request);

		return searchResponse.getCount();
	}

	private void _handleCourseTypeFilter(
		CourseType[] courseTypes, BooleanQuery mainBooleanQuery) {

		boolean hasCourseTypeFilter = ArrayUtil.isNotEmpty(courseTypes);

		if (hasCourseTypeFilter) {

			BooleanQuery atLeastOneCourseTypeQuery = _queries.booleanQuery();

			for (CourseType courseType : courseTypes) {

				TermQuery match = _queries.term(
					CourseSearchField.FIELD_COURSE_TYPE, courseType.getValue());

				atLeastOneCourseTypeQuery.addShouldQueryClauses(match);
			}

			mainBooleanQuery.addMustQueryClauses(atLeastOneCourseTypeQuery);
		}
	}

	private void _handleKeywordFilter(
		String keyword, BooleanQuery mainBooleanQuery) {

		if (!Validator.isBlank(keyword)) {

			BooleanQuery keywordQuery = _queries.booleanQuery();

			WildcardQuery courseNameWildCard =
				_queries.wildcard(
					CourseSearchField.FIELD_COURSE_NAME,
					"*" + keyword.toLowerCase() + "*");

			WildcardQuery courseDescriptionWildCard =
				_queries.wildcard(
					CourseSearchField.FIELD_COURSE_DESCRIPTION,
					"*" + keyword.toLowerCase() + "*");

			keywordQuery.addShouldQueryClauses(courseNameWildCard);
			keywordQuery.addShouldQueryClauses(courseDescriptionWildCard);

			mainBooleanQuery.addMustQueryClauses(keywordQuery);
		}
	}

	@Reference
	protected Searcher _searcher;

	@Reference
	private Queries _queries;

	@Reference
	protected SearchRequestBuilderFactory _searchRequestBuilderFactory;

	@Reference
	protected CourseLocalService _courseLocalService;

}
