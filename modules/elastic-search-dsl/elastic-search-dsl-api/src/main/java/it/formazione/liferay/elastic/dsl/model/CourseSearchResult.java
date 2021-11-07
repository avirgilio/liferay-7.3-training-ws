package it.formazione.liferay.elastic.dsl.model;

import java.util.List;

public class CourseSearchResult {

	public static CourseSearchResult of(
		long searchCount, List<Course> results) {

		return new CourseSearchResult(searchCount, results);
	}

	private CourseSearchResult(long searchCount,List<Course> searchResults) {
		_searchCount = searchCount;
		_searchResults = searchResults;
	}

	public long getSearchCount() {
		return _searchCount;
	}

	public List<Course> getSearchResults() {
		return _searchResults;
	}

	private final long _searchCount;
	private final List<Course> _searchResults;
}
