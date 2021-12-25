package it.formazione.liferay.kafka.producer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import it.formazione.liferay.kafka.integration.api.LiferayKafkaProducer;
import it.formazione.liferay.kafka.integration.api.model.News;
import it.formazione.liferay.kafka.producer.definition.KafkaProducerFactory;
import it.formazione.liferay.kafka.producer.serializer.NewsSerializer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Virgilio Alessandro 17/dic/2021
 **/
@Component(immediate = true, service = LiferayKafkaProducer.class)
public class LiferayNewsProducer implements LiferayKafkaProducer<News> {

	@interface KafkaNewsProducer {
		String clientId() default "lfr-news-producer";
		String bootstrapServerConfig() default "localhost:9092";
		String acksConfig() default "all";
		String liferay_kafka_producer_group() default "My Group";
		String liferay_kafka_producer_topics() default "lfr-news-topic";
	}

	@Activate
	@Modified
	protected void activate(KafkaNewsProducer config) {

		_sender = _kafkaProducerFactory.builder(
			producer ->
				producer
					.bootstrapServerConfig(config.bootstrapServerConfig())
					.clientId(config.clientId())
					.acksConfig(config.acksConfig())
					.keySerializerClass(StringSerializer.class)
					.valueSerializerClass(NewsSerializer.class)
		);

		_topic = config.liferay_kafka_producer_topics();
	}

	@Deactivate
	public void deactivate() {
		_sender.close();
	}

	@Override
	public Disposable produce(List<News> entity) {

		Flux<SenderRecord<String, News, String>> newsFlux = Flux
			.fromIterable(entity)
			.map(news -> SenderRecord.create(
				new ProducerRecord<>(_topic, news.getNewsId(), news),
				news.getBody()));

		SimpleDateFormat dateFormat =
			new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");

		return _sender
					.send(newsFlux)
					.doOnError(e -> _log.error("Send failed", e))
					.subscribe(r -> {

						RecordMetadata metadata = r.recordMetadata();

						_log.info(
							"LIFERAY NEWS TOPIC PRODUCER: sent message!"
							+ metadata.topic()
							+ " value: " + r.correlationMetadata()
							+  " timestamp = " +
							dateFormat.format(new Date(metadata.timestamp()))
						);
					});
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LiferayEventTopicProducer.class);

	@Reference
	private KafkaProducerFactory _kafkaProducerFactory;

	private KafkaSender<String, News> _sender;

	private String _topic;
}
