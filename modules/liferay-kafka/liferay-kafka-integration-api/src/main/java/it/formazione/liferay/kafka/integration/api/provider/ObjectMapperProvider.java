package it.formazione.liferay.kafka.integration.api.provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ObjectMapperProvider.class)
public class ObjectMapperProvider {

	@Activate
	public void activate() {
		_OBJECT_MAPPER_INSTANCE = _initializeObjectMapper();
	}

	private static ObjectMapper _initializeObjectMapper() {

		return new ObjectMapper().configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public synchronized static ObjectMapper getObjectMapperInstance() {

		return _OBJECT_MAPPER_INSTANCE != null
			? _OBJECT_MAPPER_INSTANCE
			: _initializeObjectMapper();
	}

	private static ObjectMapper _OBJECT_MAPPER_INSTANCE = null;
}
