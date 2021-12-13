package it.formazione.liferay.kafka.consumer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import it.formazione.liferay.kafka.consumer.definition.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import reactor.core.Disposable;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Virgilio Alessandro 12/dic/2021
 **/
@Component(immediate = true, service = LiferayEventsTopicConsumer.class)
public class LiferayEventsTopicConsumer {

	@interface KafkaConsumerConfig {
		String clientId() default "simple-consumer";
		String bootstrapServerConfig() default "localhost:9092";
		String groupId() default "sample-group";
		String autoOffsetResetConfig() default "earliest";
	}

	@Activate
	@Modified
	@SuppressWarnings("unchecked")
	public void activate(KafkaConsumerConfig consumerConfig) {

		String[] topics = new String[] {"lfr-events-topic"};

		KafkaReceiver<Integer, String> receiver =
			(KafkaReceiver<Integer, String>) _kafkaConsumer
				.on(consumer ->
					consumer
						.autoOffsetResetConfig(
							consumerConfig.autoOffsetResetConfig())
						.clientId(consumerConfig.clientId())
						.bootstrapServerConfig(
							consumerConfig.bootstrapServerConfig())
						.groupId(consumerConfig.groupId())
						.topics(topics)
						.keyDeserializerClass(IntegerDeserializer.class)
						.valueDeserializerClass(StringDeserializer.class)
				);

		SimpleDateFormat dateFormat =
			new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");

		_consumerDisposable = receiver
			.receive()
			.subscribe(record -> {
				ReceiverOffset offset = record.receiverOffset();

				_log.info(
					"Received message from topic partition: "
					+ offset.topicPartition()
					+ " timestamp = " + dateFormat.format(
						new Date(record.timestamp()))
					+ " key =  " + record.key()
					+ "  value = " + record.value());

				offset.acknowledge();
			});
	}

	@Deactivate
	protected void deactivate() {
		_consumerDisposable.dispose();
	}

	@Reference
	private KafkaConsumer _kafkaConsumer;

	private Disposable _consumerDisposable;

	private static final Log _log = LogFactoryUtil.getLog(
		LiferayEventsTopicConsumer.class);

}
