package it.formazione.liferay.elastic.dsl.search;

import com.liferay.portal.search.query.Query;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface CourseSearchQueryBuilder {
	public CourseSearchQueryBuilder addSearchKeyword(String keyword);
	public CourseSearchQueryBuilder addCourseTypesFilter(CourseType[] courseTypes);
	public Query build();
}
