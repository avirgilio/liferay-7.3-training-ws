package it.formazione.liferay.kafka.integration.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(
	enabled = false,
	immediate = true,
	service = ObjectMapperProvider.class
)
public class ObjectMapperProvider {

	@Activate
	public void activate() {

		_objectMapper = new ObjectMapper();
		_objectMapper.configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

	public ObjectMapper getObjectMapper() {
		return _objectMapper;
	}

	private ObjectMapper _objectMapper;
}
