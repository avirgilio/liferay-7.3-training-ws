package it.formazione.liferay.elastic.dsl.service.internal.search;

import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.model.CourseSearchResult;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.search.CourseSearcher;
import it.formazione.liferay.elastic.dsl.search.CourseSearcherFactory;
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

		Query query = _courseSearcherFactory
			.builder()
			.addCourseTypesFilter(courseTypes)
			.addSearchKeyword(keyword)
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
						searchContext.setStart(start);
						searchContext.setEnd(end);
					})
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
			.addSearchKeyword(keyword)
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

	@Reference
	protected Searcher _searcher;

	@Reference
	protected SearchRequestBuilderFactory _searchRequestBuilderFactory;

	@Reference
	protected CourseSearcherFactory _courseSearcherFactory;

	@Reference
	protected CourseLocalService _courseLocalService;

}
