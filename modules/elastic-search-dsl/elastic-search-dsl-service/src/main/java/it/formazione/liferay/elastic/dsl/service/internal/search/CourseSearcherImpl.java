package it.formazione.liferay.elastic.dsl.service.internal.search;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermQuery;
import com.liferay.portal.search.query.WildcardQuery;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import it.formazione.liferay.elastic.dsl.constants.search.CourseSearchField;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.model.CourseSearchResult;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.search.CourseSearcher;
import it.formazione.liferay.elastic.dsl.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(immediate = true, service = CourseSearcher.class)
public class CourseSearcherImpl implements CourseSearcher {

	@Override
	public CourseSearchResult search(
		CourseType[] courseTypes, String keyword, long companyId) {

		BooleanQuery mainBooleanQuery = _queries.booleanQuery();

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

		SearchRequestBuilder requestBuilder =
			_searchRequestBuilderFactory
				.builder()
				.entryClassNames(Course.class.getName());

		requestBuilder.emptySearchEnabled(true);

		requestBuilder.withSearchContext(
			searchContext -> searchContext.setCompanyId(companyId));

		SearchRequest searchRequest =
			requestBuilder
				.query(mainBooleanQuery)
				.build();

		SearchResponse searchResponse = _searcher.search(searchRequest);

		List<Course> results = searchResponse
			.getDocumentsStream()
			.map(doc ->
				_courseLocalService.fetchCourse(
					doc.getLong(Field.ENTRY_CLASS_PK)))
			.collect(Collectors.toList());

		return CourseSearchResult.of(searchResponse.getCount(), results);
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
