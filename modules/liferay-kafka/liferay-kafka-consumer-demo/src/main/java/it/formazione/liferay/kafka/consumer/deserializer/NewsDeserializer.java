package it.formazione.liferay.kafka.consumer.deserializer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import it.formazione.liferay.kafka.integration.api.constants.LiferayKafkaConsumerConstants;
import it.formazione.liferay.kafka.integration.api.model.News;
import it.formazione.liferay.kafka.integration.api.provider.ObjectMapperProvider;
import org.apache.kafka.common.serialization.Deserializer;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author Virgilio Alessandro 19/dic/2021
 **/
@Component(
	immediate = true,
	property = {
		LiferayKafkaConsumerConstants.KAFKA_DESERIALIZER_CLASS + "=News"
	},
	service = Deserializer.class
)
public class NewsDeserializer implements Deserializer<News> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) { }

	@Override
	public News deserialize(String topic, byte[] data) {

		News news = null;

		try {
			news = ObjectMapperProvider
				.getObjectMapperInstance()
				.readValue(data, News.class);
		}
		catch (IOException e) {
			_log.error(e,e);
		}

		return news;
	}

	@Override
	public void close() {}

	private static final Log _log = LogFactoryUtil.getLog(
		NewsDeserializer.class);
}
