package it.formazione.liferay.kafka.integration.api.registry;

import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.kafka.common.serialization.Deserializer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import java.util.Set;

@Component(immediate = true, service = KafkaDeserializerRegistry.class)
public class KafkaDeserializerRegistry {

	public <T> Deserializer<T> getDeserializerByClass(Class<?> clazz) {
		return (Deserializer<T>)
			_deserializerServiceTrackerMap.getService(clazz.getName());
	}

	public Set<String> getAvailableDeserializers() {
		return _deserializerServiceTrackerMap.keySet();
	}

	@Activate
	@Modified
	protected void activate(BundleContext bundleContext) {

		_deserializerServiceTrackerMap =
			 ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext,
				(Class<Deserializer<?>>) (Class<?>) Deserializer.class,
				null,
				 new KafkaDeserializerTracker(bundleContext));
	}

	private static class KafkaDeserializerTracker
		implements ServiceReferenceMapper<String, Deserializer<?>> {

		public KafkaDeserializerTracker(BundleContext bundleContext) {
			_bundleContext = bundleContext;
		}

		@Override
		public void map(
			ServiceReference<Deserializer<?>> serviceReference,
			Emitter<String> emitter) {

			try {

				Deserializer<?> deserializer =
					_bundleContext.getService(serviceReference);

				String serializerMapKey = deserializer.getClass().getName();

				emitter.emit(serializerMapKey);
			}
			catch (Exception e) {
				_log.error(e);
			}
			finally {
				_bundleContext.ungetService(serviceReference);
			}
		}

		private final BundleContext _bundleContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KafkaDeserializerRegistry.class);

	private ServiceTrackerMap<String, Deserializer<?>>
		_deserializerServiceTrackerMap;
}
