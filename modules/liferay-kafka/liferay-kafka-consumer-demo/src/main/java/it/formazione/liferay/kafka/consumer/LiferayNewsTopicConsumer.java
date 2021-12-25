package it.formazione.liferay.kafka.consumer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import it.formazione.liferay.kafka.consumer.definition.KafkaConsumerFactory;
import it.formazione.liferay.kafka.consumer.deserializer.NewsDeserializer;
import it.formazione.liferay.kafka.integration.api.LiferayKafkaConsumer;
import it.formazione.liferay.kafka.integration.api.log.LiferayReactorLogger;
import it.formazione.liferay.kafka.integration.api.model.News;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import reactor.core.Disposable;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.util.Logger;

/**
 * @author Virgilio Alessandro 19/dic/2021
 **/
@Component(immediate = true, service = LiferayKafkaConsumer.class)
public class LiferayNewsTopicConsumer implements LiferayKafkaConsumer {

	@interface LiferayNewsTopicConsumerConfig {
		String clientId() default "lfr-news-consumer";
		String bootstrapServerConfig() default "localhost:9092";
		String groupId() default "My Group";
		String autoOffsetResetConfig() default "earliest";
		String[] topics() default { "lfr-news-topic" };
	}

	@Activate
	@Modified
	public void activate(LiferayNewsTopicConsumerConfig consumerConfig) {

		_receiver = _kafkaConsumerFactory
			.on(consumer ->
				consumer
					.autoOffsetResetConfig(
						consumerConfig.autoOffsetResetConfig())
					.clientId(consumerConfig.clientId())
					.bootstrapServerConfig(
						consumerConfig.bootstrapServerConfig())
					.groupId(consumerConfig.groupId())
					.topics(consumerConfig.topics())
					.keyDeserializerClass(StringDeserializer.class)
					.valueDeserializerClass(NewsDeserializer.class)
			);
	}

	@Override
	public Disposable consume() {

		return _receiver
			.receive()
			.log(_log)
			.subscribe(record -> {
				News newNews = record.value();
				_log.info( "RECEIVED NEWS : ");
				_log.info(newNews.toString());
			});
	}

	@Reference
	private KafkaConsumerFactory _kafkaConsumerFactory;

	private KafkaReceiver<String, News> _receiver;

	private static final Logger _log = new LiferayReactorLogger(
		LiferayNewsTopicConsumer.class);
}
