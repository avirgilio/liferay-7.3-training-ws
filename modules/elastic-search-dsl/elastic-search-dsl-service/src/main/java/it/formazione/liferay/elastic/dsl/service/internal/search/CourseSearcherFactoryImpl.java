package it.formazione.liferay.elastic.dsl.service.internal.search;

import com.liferay.portal.search.query.Queries;
import it.formazione.liferay.elastic.dsl.search.CourseSearchQueryBuilder;
import it.formazione.liferay.elastic.dsl.search.CourseSearcherFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = CourseSearcherFactory.class)
public class CourseSearcherFactoryImpl implements CourseSearcherFactory {

	@Override
	public CourseSearchQueryBuilder builder() {
		return new CourseSearchQueryBuilderImpl(_queries);
	}

	@Reference
	private Queries _queries;
}
