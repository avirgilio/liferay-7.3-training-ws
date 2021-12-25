package it.formazione.liferay.kafka.producer.definition;

import org.apache.kafka.common.serialization.Serializer;
import org.osgi.annotation.versioning.ProviderType;
import reactor.kafka.sender.KafkaSender;

import java.util.function.Consumer;

/**
 * @author Virgilio Alessandro 12/dic/2021
 **/
@ProviderType
public interface KafkaProducerFactory {

	public <A, B> KafkaSender<A, B> builder(
		Consumer<
			KafkaProducerFactory.KafkaProducerBuilder> kafkaProducerBuilderConsumer);

	@ProviderType
	public interface KafkaProducerBuilder {

		public KafkaProducerFactory.KafkaProducerBuilder bootstrapServerConfig(
			String bootstrapServerConfig);

		public KafkaProducerFactory.KafkaProducerBuilder clientId(
			String clientId);

		public KafkaProducerFactory.KafkaProducerBuilder acksConfig(
			String acksConfig);

		public KafkaProducerFactory.KafkaProducerBuilder keySerializerClass(
			Class<? extends Serializer<?>> clazz);

		public KafkaProducerFactory.KafkaProducerBuilder valueSerializerClass(
			Class<? extends Serializer<?>> clazz);
	}
}
