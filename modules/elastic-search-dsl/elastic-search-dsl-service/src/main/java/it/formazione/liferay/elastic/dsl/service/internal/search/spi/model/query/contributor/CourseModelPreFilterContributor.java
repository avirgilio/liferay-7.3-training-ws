package it.formazione.liferay.elastic.dsl.service.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "indexer.class.name=it.formazione.liferay.elastic.dsl.model.Course",
	service = ModelPreFilterContributor.class
)
public class CourseModelPreFilterContributor
	implements ModelPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext != null) {

			long currentUserId = serviceContext.getUserId();
			long currentGroupId = serviceContext.getScopeGroupId();

			booleanFilter.addRequiredTerm(Field.USER_ID, currentUserId);
			booleanFilter.addRequiredTerm(Field.GROUP_ID, currentGroupId);

			searchContext.setUserId(currentUserId);
			searchContext.setGroupIds(new long[] { currentGroupId });

			if (_log.isDebugEnabled()) {
				_log.debug(
					"PRE FILTER: FILTER SEARCH RESPONSE FOR USER ID: "
					+ currentUserId + " - GROUPID: " + currentGroupId);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CourseModelPreFilterContributor.class);
}
