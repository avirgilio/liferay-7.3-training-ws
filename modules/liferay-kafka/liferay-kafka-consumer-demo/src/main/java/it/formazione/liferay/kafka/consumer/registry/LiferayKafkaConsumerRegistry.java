package it.formazione.liferay.kafka.consumer.registry;

import com.liferay.osgi.util.ServiceTrackerFactory;
import it.formazione.liferay.kafka.integration.api.LiferayKafkaConsumer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import reactor.core.Disposable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Virgilio Alessandro 19/dic/2021
 **/
@Component(immediate = true, service = LiferayKafkaConsumerRegistry.class)
public class LiferayKafkaConsumerRegistry {

	@Activate
	protected void activate(BundleContext bundleContext) {

		_bundleContext = bundleContext;

		_serviceTracker = ServiceTrackerFactory.open(
			_bundleContext,
			(Class<LiferayKafkaConsumer>)
				(Class<?>)LiferayKafkaConsumer.class,
			new LiferayKafkaConsumerCustomizer());
	}

	private class LiferayKafkaConsumerCustomizer
		implements ServiceTrackerCustomizer<
			LiferayKafkaConsumer, LiferayKafkaConsumer> {

		@Override
		public LiferayKafkaConsumer addingService(
			ServiceReference<LiferayKafkaConsumer> reference) {

			LiferayKafkaConsumer consumer =
				_bundleContext.getService(reference);

			Disposable consumerDisposable = consumer.consume();

			_disposableMap.putIfAbsent(
				consumer.getClass().getName(), consumerDisposable);

			return consumer;
		}

		@Override
		public void modifiedService(
			ServiceReference<LiferayKafkaConsumer> reference,
			LiferayKafkaConsumer service) {

			removedService(reference, service);
			addingService(reference);
		}

		@Override
		public void removedService(
			ServiceReference<LiferayKafkaConsumer> reference,
			LiferayKafkaConsumer service) {

			_disposableMap.get(service.getClass().getName()).dispose();
			_bundleContext.ungetService(reference);
		}
	}

	private BundleContext _bundleContext;

	private ServiceTracker<LiferayKafkaConsumer, LiferayKafkaConsumer>
		_serviceTracker;

	private final Map<String, Disposable>
		_disposableMap = new ConcurrentHashMap<>();

}
