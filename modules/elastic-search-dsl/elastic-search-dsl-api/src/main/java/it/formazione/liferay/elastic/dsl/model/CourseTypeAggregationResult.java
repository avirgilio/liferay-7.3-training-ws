package it.formazione.liferay.elastic.dsl.model;

public class CourseTypeAggregationResult {

	public static CourseTypeAggregationResult of(CourseType courseType, long count) {
		return new CourseTypeAggregationResult(courseType, count);
	}

	private CourseTypeAggregationResult(CourseType courseType, long count) {
		_courseType = courseType;
		_count = count;
	}

	public CourseType getCourseType() {
		return _courseType;
	}

	public long getCount() {
		return _count;
	}

	private CourseType _courseType;
	private long _count;
}
