package it.formazione.liferay.kafka.producer.definition;

import org.apache.kafka.common.serialization.Serializer;
import org.osgi.annotation.versioning.ProviderType;
import reactor.kafka.sender.KafkaSender;

import java.util.function.Consumer;

/**
 * @author Virgilio Alessandro 12/dic/2021
 **/
@ProviderType
public interface KafkaProducer {

	public <A, B> KafkaSender<A, B> with(
		Consumer<
			KafkaProducer.KafkaProducerBuilder> kafkaProducerBuilderConsumer);

	@ProviderType
	public interface KafkaProducerBuilder {

		public KafkaProducer.KafkaProducerBuilder bootstrapServerConfig(
			String bootstrapServerConfig);

		public KafkaProducer.KafkaProducerBuilder clientId(
			String clientId);

		public KafkaProducer.KafkaProducerBuilder acksConfig(
			String acksConfig);

		public KafkaProducer.KafkaProducerBuilder keySerializerClass(
			Class<? extends Serializer<?>> clazz);

		public KafkaProducer.KafkaProducerBuilder valueSerializerClass(
			Class<? extends Serializer<?>> clazz);
	}
}
