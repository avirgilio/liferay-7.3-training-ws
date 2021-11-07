package it.formazione.liferay.elastic.dsl.service.internal.search;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.query.TermQuery;
import com.liferay.portal.search.query.WildcardQuery;
import it.formazione.liferay.elastic.dsl.constants.search.CourseSearchField;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.search.CourseSearchQueryBuilder;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = CourseSearchQueryBuilder.class)
public class CourseSearchQueryBuilderImpl implements CourseSearchQueryBuilder {

	public CourseSearchQueryBuilderImpl(){}

	public CourseSearchQueryBuilderImpl(Queries queries) {
		_queries = queries;
		_mainBooleanQuery = _queries.booleanQuery();
	}

	@Override
	public CourseSearchQueryBuilder addSearchKeyword(String keyword) {

		boolean hasKeyword = !Validator.isBlank(keyword);

		if (hasKeyword) {

			BooleanQuery keywordQuery = _queries.booleanQuery();

			WildcardQuery courseNameWildCard =
				_queries.wildcard(
					CourseSearchField.FIELD_COURSE_NAME,
					"*" + keyword.toLowerCase() + "*");

			WildcardQuery courseDescriptionWildCard =
				_queries.wildcard(
					CourseSearchField.FIELD_COURSE_DESCRIPTION,
					"*" + keyword.toLowerCase() + "*");

			keywordQuery.addShouldQueryClauses(courseNameWildCard);
			keywordQuery.addShouldQueryClauses(courseDescriptionWildCard);

			_mainBooleanQuery.addMustQueryClauses(keywordQuery);
		}

		return this;
	}

	@Override
	public CourseSearchQueryBuilder addCourseTypesFilter(
		CourseType[] courseTypes) {

		boolean hasCourseTypeFilter = ArrayUtil.isNotEmpty(courseTypes);

		if (hasCourseTypeFilter) {

			BooleanQuery atLeastOneCourseTypeQuery = _queries.booleanQuery();

			for (CourseType courseType : courseTypes) {

				TermQuery match = _queries.term(
					CourseSearchField.FIELD_COURSE_TYPE, courseType.getValue());

				atLeastOneCourseTypeQuery.addShouldQueryClauses(match);
			}

			_mainBooleanQuery.addMustQueryClauses(atLeastOneCourseTypeQuery);
		}

		return this;
	}

	@Override
	public Query build() {
		return _mainBooleanQuery;
	}

	private BooleanQuery _mainBooleanQuery;

	private Queries _queries;
}
