package it.formazione.liferay.elastic.dsl.service.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.account.model.AccountEntry",
	service = ModelPreFilterContributor.class
)
public class CourseModelPreFilterContributor
	implements ModelPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		_log.info("PRE FILTER CONTRIBUTOR!");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CourseModelPreFilterContributor.class);
}
