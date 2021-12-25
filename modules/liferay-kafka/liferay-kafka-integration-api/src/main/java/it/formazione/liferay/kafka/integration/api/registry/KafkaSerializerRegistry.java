package it.formazione.liferay.kafka.integration.api.registry;

import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.kafka.common.serialization.Serializer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import java.util.Set;

@Component(immediate = true, service = KafkaSerializerRegistry.class)
public class KafkaSerializerRegistry {

	public <T> Serializer<T> getSerializerByClass(Class<?> clazz) {
		return (Serializer<T>)
			_serializerServiceTrackerMap.getService(clazz.getName());
	}

	public Set<String> getAvailableSerializers() {
		return _serializerServiceTrackerMap.keySet();
	}

	@Activate
	@Modified
	protected void activate(BundleContext bundleContext) {

		_serializerServiceTrackerMap =
			 ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext,
				(Class<Serializer<?>>) (Class<?>) Serializer.class,
				null,
				 new KafkaSerializerTracker(bundleContext));
	}

	private static class KafkaSerializerTracker implements
		ServiceReferenceMapper<String, Serializer<?>> {

		public KafkaSerializerTracker(BundleContext bundleContext) {
			_bundleContext = bundleContext;
		}

		@Override
		public void map(
			ServiceReference<Serializer<?>> serviceReference,
			Emitter<String> emitter) {

			try {

				Serializer<?> serializer =
					_bundleContext.getService(serviceReference);

				String serializerMapKey = serializer.getClass().getName();

				emitter.emit(serializerMapKey);
			}
			catch (Exception e) {
				_log.error(e);
			}
			finally {
				_bundleContext.ungetService(serviceReference);
			}
		}

		private BundleContext _bundleContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KafkaSerializerRegistry.class);

	private ServiceTrackerMap<String, Serializer<?>>
		_serializerServiceTrackerMap;
}
