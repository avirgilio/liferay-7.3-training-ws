package it.formazione.liferay.elastic.dsl.service.internal.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.aggregation.AggregationResult;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.aggregation.bucket.TermsAggregation;
import com.liferay.portal.search.aggregation.bucket.TermsAggregationResult;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.search.sort.FieldSort;
import com.liferay.portal.search.sort.SortFieldBuilder;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.sort.Sorts;
import it.formazione.liferay.elastic.dsl.constants.search.CourseSearchField;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.model.CourseSearchResult;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.model.CourseTypeAggregationResult;
import it.formazione.liferay.elastic.dsl.search.CourseSearcher;
import it.formazione.liferay.elastic.dsl.search.CourseSearcherFactory;
import it.formazione.liferay.elastic.dsl.service.CourseLocalService;
import it.formazione.liferay.elastic.dsl.service.internal.search.hits.CourseSearchHitsGetter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component(immediate = true, service = CourseSearcher.class)
public class CourseSearcherImpl implements CourseSearcher {

	@Override
	public CourseSearchResult search(
		CourseType[] courseTypes, String keyword, long companyId,
		int start, int end, String orderByCol) {

		Query query = _courseSearcherFactory
			.builder()
			.addCourseTypesFilter(courseTypes)
			.addSearchKeywordFilter(keyword)
			.build();

		SortOrder sortOrder = SortOrder.ASC;

		FieldSort fieldSort = _sorts.field(
			_sortFieldBuilder.getSortField(Course.class.getName(), orderByCol),
			sortOrder);

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
				.sorts(fieldSort)
				.query(query)
				.build();

		return _searcher
			.search(request)
			.withHitsGet(new CourseSearchHitsGetter());
	}

	@Override
	public long searchCount(
		CourseType[] courseTypes, String keyword, long companyId) {

		Query query = _courseSearcherFactory
			.builder()
			.addCourseTypesFilter(courseTypes)
			.addSearchKeywordFilter(keyword)
			.build();

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
				.query(query)
				.build();

		SearchResponse searchResponse = _searcher.search(request);

		return searchResponse.getCount();
	}

	@Override
	public List<CourseTypeAggregationResult> getCourseTypes(
		long groupId, long companyId) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();
		searchSearchRequest.setSize(0);

		String courseTypesAggregationName = "by-course-types";

		TermsAggregation courseTypesAggregation = _aggregations.terms(
			courseTypesAggregationName,
			Field.getSortableFieldName(CourseSearchField.FIELD_COURSE_TYPE)
		);

		Query query = _courseSearcherFactory
			.builder()
			.addAllCourseTypesFilter()
			.addGroupIdFilter(String.valueOf(groupId))
			.build();

		searchSearchRequest.setQuery(query);

		searchSearchRequest.setIndexNames(
			LIFERAY_ELASTIC_INDEX_PREFIX + companyId);

		searchSearchRequest.addAggregation(courseTypesAggregation);

		SearchSearchResponse searchSearchResponse =
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest);

		Map<String, AggregationResult> aggregationResultsMap =
			searchSearchResponse.getAggregationResultsMap();

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)
				aggregationResultsMap.get(courseTypesAggregationName);

		Collection<Bucket> courseTypeAggrBuckets =
			termsAggregationResult.getBuckets();

		List<CourseTypeAggregationResult> results = new ArrayList<>();

		for (Bucket bucket : courseTypeAggrBuckets) {

			CourseType courseType = CourseType.getCourseTypeFromValue(
				Integer.parseInt(bucket.getKey()));

			CourseTypeAggregationResult bucketResult =
				CourseTypeAggregationResult.of(
					courseType, bucket.getDocCount());

			results.add(bucketResult);
		}

		return results;
	}

	@Reference
	protected Searcher _searcher;

	@Reference
	protected SearchRequestBuilderFactory _searchRequestBuilderFactory;

	@Reference
	protected CourseSearcherFactory _courseSearcherFactory;

	@Reference
	protected CourseLocalService _courseLocalService;

	@Reference
	private SortFieldBuilder _sortFieldBuilder;

	@Reference
	private Aggregations _aggregations;

	@Reference
	private SearchRequestExecutor _searchRequestExecutor;

	@Reference
	private Sorts _sorts;

	private static final Log _log = LogFactoryUtil.getLog(
		CourseSearcherImpl.class);

	private static final String LIFERAY_ELASTIC_INDEX_PREFIX = "liferay-";
}
