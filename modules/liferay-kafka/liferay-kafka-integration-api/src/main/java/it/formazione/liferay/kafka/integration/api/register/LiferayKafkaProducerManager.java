package it.formazione.liferay.kafka.integration.api.register;

import it.formazione.liferay.kafka.integration.api.LiferayKafkaProducer;
import org.osgi.service.component.annotations.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Virgilio Alessandro 14/dic/2021
 **/
@Component(immediate = true, service = LiferayKafkaProducerManager.class)
public class LiferayKafkaProducerManager {
/*
	public LiferayKafkaProducer initializeKafkaProducer(
		LiferayKafkaProducer producer) {

		Map<String, Object> props = new HashMap<>();

		props.put(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
			producer.getBootstrapServerConfig());

		props.put(ProducerConfig.CLIENT_ID_CONFIG, producer.getClientId());
		props.put(ProducerConfig.ACKS_CONFIG, producer.getAsksConfig());

		props.put(
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
			StringSerializer.class
		);

		//TODO: JSON
		props.put(
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
			StringSerializer.class);

		SenderOptions<String, String> senderOptions =
			SenderOptions.create(props);

		KafkaSender<String, String> kafkaSender =
			KafkaSender.create(senderOptions);

		_kafkaProducerMap.put(producer.getClientId(), producer);

		return producer;
	}

	public LiferayKafkaProducer removeKafkaProducer(
		LiferayKafkaProducer producer) {

		_kafkaProducerMap.remove(producer.getClientId());

		return producer;
	}

	public Map<String, LiferayKafkaProducer> getKafkaProducerMap() {
		return Collections.unmodifiableMap(_kafkaProducerMap);
	}

 */
	private final Map<String, LiferayKafkaProducer>
		_kafkaProducerMap = new ConcurrentHashMap<>();
}
