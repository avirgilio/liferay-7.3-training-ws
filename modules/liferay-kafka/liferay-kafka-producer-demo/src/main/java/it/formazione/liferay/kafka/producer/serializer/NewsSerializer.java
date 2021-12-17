package it.formazione.liferay.kafka.producer.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import it.formazione.liferay.kafka.integration.api.ObjectMapperProvider;
import it.formazione.liferay.kafka.producer.model.News;
import org.apache.kafka.common.serialization.Serializer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Map;

@Component(immediate = true, service = Serializer.class)
public class NewsSerializer implements Serializer<News> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public byte[] serialize(String topic, News data) {

		byte[] retVal = null;

		try {
			retVal = _objectMapperProvider
				.getObjectMapper()
				.writeValueAsBytes(data);
		}
		catch (JsonProcessingException e) {
			_log.error(e.getMessage(), e);
		}

		return retVal;
	}

	@Override
	public void close() {}

	@Reference
	private ObjectMapperProvider _objectMapperProvider;

	private final Log _log = LogFactoryUtil.getLog(
		NewsSerializer.class);

}
