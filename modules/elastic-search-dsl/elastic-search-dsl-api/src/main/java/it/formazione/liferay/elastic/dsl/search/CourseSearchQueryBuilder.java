package it.formazione.liferay.elastic.dsl.search;

import com.liferay.portal.search.query.Query;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface CourseSearchQueryBuilder {
	public CourseSearchQueryBuilder addSearchKeywordFilter(String keyword);
	public CourseSearchQueryBuilder addCourseTypeFilter(CourseType courseType);
	public CourseSearchQueryBuilder addCourseTypesFilter(CourseType[] courseTypes);
	public CourseSearchQueryBuilder addAllCourseTypesFilter();
	public Query build();
}
