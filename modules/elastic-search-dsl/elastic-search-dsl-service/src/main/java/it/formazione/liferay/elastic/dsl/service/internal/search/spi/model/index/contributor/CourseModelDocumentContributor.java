package it.formazione.liferay.elastic.dsl.service.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import it.formazione.liferay.elastic.dsl.constants.search.CourseSearchField;
import it.formazione.liferay.elastic.dsl.model.Course;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "indexer.class.name=it.formazione.liferay.elastic.dsl.model.Course",
	service = ModelDocumentContributor.class
)
public class CourseModelDocumentContributor
	implements ModelDocumentContributor<Course> {

	@Override
	public void contribute(Document document, Course course) {

		document.addText(
			CourseSearchField.FIELD_COURSE_NAME, course.getCourseName());

		document.addNumber(
			CourseSearchField.FIELD_COURSE_TYPE, course.getCourseType());

		document.addText(
			CourseSearchField.FIELD_COURSE_DESCRIPTION, course.getCourseDescription());
	}

}
