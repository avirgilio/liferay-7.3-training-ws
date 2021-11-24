package it.formazione.liferay.elastic.dsl.service.internal.search.hits;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.model.CourseSearchResult;
import it.formazione.liferay.elastic.dsl.service.CourseLocalServiceUtil;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseSearchHitsGetter
	implements Function<Hits, CourseSearchResult> {

	@Override
	public CourseSearchResult apply(Hits hits) {

		List<Course> results = Arrays
			.stream(hits.getDocs())
			.map(doc -> doc.get(Field.ENTRY_CLASS_PK))
			.map(courseId -> CourseLocalServiceUtil.fetchCourse(
				Integer.parseInt(courseId)))
			.collect(Collectors.toList());

		return CourseSearchResult.of(hits.getLength(), results);
	}
}
