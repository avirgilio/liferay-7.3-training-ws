package it.formazione.liferay.kafka.producer.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import it.formazione.liferay.kafka.integration.api.model.News;
import org.apache.kafka.common.serialization.Serializer;
import org.osgi.service.component.annotations.Component;

import java.util.Map;

@Component(immediate = true, service = Serializer.class)
public class NewsSerializer implements Serializer<News> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public byte[] serialize(String topic, News data) {

		byte[] retVal = null;

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		//objectMapper.registerModule(new JavaTimeModule());

		try {
			retVal = objectMapper.writeValueAsBytes(data);
		}
		catch (JsonProcessingException e) {
			_log.error(e.getMessage(), e);
		}

		return retVal;
	}

	@Override
	public void close() {}

	private final Log _log = LogFactoryUtil.getLog(
		NewsSerializer.class);

}
