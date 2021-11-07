package it.formazione.liferay.elastic.dsl.search;

import it.formazione.liferay.elastic.dsl.model.CourseSearchResult;
import it.formazione.liferay.elastic.dsl.model.CourseType;

public interface CourseSearcher {
	CourseSearchResult search(
		CourseType[] courseTypes, String keyword, long companyId);
}
