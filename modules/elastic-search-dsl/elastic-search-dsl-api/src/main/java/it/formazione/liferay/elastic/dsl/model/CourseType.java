package it.formazione.liferay.elastic.dsl.model;

public enum CourseType {
	DEVELOP(CourseType.DEVELOP_TYPE,CourseType.DEVELOP_VALUE),
	ADMINISTRATION(CourseType.ADMIN_TYPE, CourseType.ADMINISTRATION_VALUE);

	CourseType(String label, int value) {
		this._label = label;
		this._value = value;
	}

	public static CourseType getCourseTypeFromValue(int value) {
		return value == DEVELOP_VALUE ? DEVELOP : ADMINISTRATION;
	}

	public int getValue() {
		return _value;
	}

	public String getLabel() {
		return _label;
	}

	public static final String DEVELOP_TYPE = "development";
	public static final String ADMIN_TYPE = "administration";

	public static final int DEVELOP_VALUE = 1;
	public static final int ADMINISTRATION_VALUE = 2;

	private final String _label;
	private final int _value;
}
