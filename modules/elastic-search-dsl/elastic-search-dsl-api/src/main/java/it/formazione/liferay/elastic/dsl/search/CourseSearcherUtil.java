package it.formazione.liferay.elastic.dsl.search;

import com.liferay.portal.kernel.exception.PortalException;
import it.formazione.liferay.elastic.dsl.model.CourseSearchResult;
import it.formazione.liferay.elastic.dsl.model.CourseType;
import it.formazione.liferay.elastic.dsl.model.CourseTypeAggregationResult;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

public class CourseSearcherUtil {

	public static CourseSearchResult search(
			CourseType[] courseTypes, String keyword, long companyId,
			int start, int end, String orderByCol)
		throws PortalException {

		return getService().search(courseTypes, keyword, companyId, start, end, orderByCol);
	}

	public static long searchCount(
			CourseType[] courseTypes, String keyword, long companyId)
		throws PortalException {

		return getService().searchCount(courseTypes, keyword, companyId);
	}

	public static List<CourseTypeAggregationResult> getCourseTypes(
			long groupId, long companyId)
		throws PortalException {

		return getService().getCourseTypes(groupId, companyId);
	}

	private static CourseSearcher getService() {
		return _serviceTracker.getService();
	}

	private static final ServiceTracker<
		CourseSearcher, CourseSearcher> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseSearcher.class);

		ServiceTracker<CourseSearcher, CourseSearcher>
			serviceTracker = new ServiceTracker<>(
			bundle.getBundleContext(),
			CourseSearcher.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}
