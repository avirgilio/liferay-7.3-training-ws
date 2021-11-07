package it.formazione.liferay.elastic.dsl.search;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface CourseSearcherFactory {
	public CourseSearchQueryBuilder builder();
}
