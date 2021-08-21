package it.formazione.liferay.dml.provider.util;

import it.formazione.liferay.dml.exception.ExistingFavoriteDocumentException;
import it.formazione.liferay.dml.model.dto.FavoriteDocumentDTO;
import it.formazione.liferay.dml.provider.FavoriteDocumentProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * @author Virgilio Alessandro 20/ago/2021
 **/
public class FavoriteDocumentProviderUtil {

	public static List<FavoriteDocumentDTO> getUserFavoriteDocuments(
		long userId) {

		return getService().getUserFavoriteDocuments(userId);
	}

	public static FavoriteDocumentDTO addEntry(
			long userId, FavoriteDocumentDTO doc)
		throws ExistingFavoriteDocumentException {

		return getService().addEntry(userId, doc);
	}

	private static FavoriteDocumentProvider getService() {
		return _serviceTracker.getService();
	}

	private static final ServiceTracker<
		FavoriteDocumentProvider, FavoriteDocumentProvider> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FavoriteDocumentProvider.class);

		ServiceTracker<
			FavoriteDocumentProvider, FavoriteDocumentProvider> serviceTracker =
				new ServiceTracker<>(
					bundle.getBundleContext(),
					FavoriteDocumentProvider.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
