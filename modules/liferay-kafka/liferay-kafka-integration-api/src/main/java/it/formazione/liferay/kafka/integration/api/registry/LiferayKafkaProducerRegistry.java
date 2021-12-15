package it.formazione.liferay.kafka.integration.api.registry;

import com.liferay.osgi.util.ServiceTrackerFactory;
import it.formazione.liferay.kafka.integration.api.LiferayKafkaProducer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Virgilio Alessandro 14/dic/2021
 **/
@Component(
	enabled = false,
	immediate = true,
	service = LiferayKafkaProducerRegistry.class
)
public class LiferayKafkaProducerRegistry {

	@Activate
	public void activate(BundleContext bundleContext) {

		_bundleContext = bundleContext;

		_serviceTracker = ServiceTrackerFactory.open(
			_bundleContext,
			(Class<LiferayKafkaProducer>)
				(Class<?>)LiferayKafkaProducer.class,
			new LiferayKafkaProducerServiceTrackerCustomizer());
	}

	private class LiferayKafkaProducerServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<
			LiferayKafkaProducer, LiferayKafkaProducer> {

		@Override
		public LiferayKafkaProducer addingService(
			ServiceReference<LiferayKafkaProducer> reference) {

			LiferayKafkaProducer<?> producer =
				_bundleContext.getService(reference);

			return producer;
		}

		@Override
		public void modifiedService(
			ServiceReference<LiferayKafkaProducer> reference,
			LiferayKafkaProducer service) {

			removedService(reference, service);
			addingService(reference);
		}

		@Override
		public void removedService(
			ServiceReference<LiferayKafkaProducer> reference,
			LiferayKafkaProducer service) {

			_bundleContext.ungetService(reference);
		}
	}

	private BundleContext _bundleContext;
	private ServiceTracker<LiferayKafkaProducer, LiferayKafkaProducer>
		_serviceTracker;

}
