package it.formazione.liferay.elastic.dsl.service.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;
import it.formazione.liferay.elastic.dsl.constants.search.CourseSearchField;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	enabled = false,
	immediate = true,
	property = "indexer.class.name=it.formazione.liferay.elastic.dsl.model.Course",
	service = KeywordQueryContributor.class
)
public class CourseKeywordQueryContributor implements KeywordQueryContributor{

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		queryHelper.addSearchTerm(
			booleanQuery, keywordQueryContributorHelper.getSearchContext(),
			CourseSearchField.FIELD_COURSE_NAME, true);

		queryHelper.addSearchTerm(
			booleanQuery, keywordQueryContributorHelper.getSearchContext(),
			CourseSearchField.FIELD_COURSE_DESCRIPTION, true);

		queryHelper.addSearchTerm(
			booleanQuery, keywordQueryContributorHelper.getSearchContext(),
			CourseSearchField.FIELD_COURSE_TYPE, false);
	}

	@Reference
	protected QueryHelper queryHelper;
}
