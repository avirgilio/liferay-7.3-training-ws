package it.formazione.liferay.kafka.consumer.definition;

import org.apache.kafka.common.serialization.Deserializer;
import org.osgi.annotation.versioning.ProviderType;
import reactor.kafka.receiver.KafkaReceiver;

import java.util.function.Consumer;

/**
 * @author Virgilio Alessandro 12/dic/2021
 **/
@ProviderType
public interface KafkaConsumer {

	public KafkaReceiver<?, ?> on(
		Consumer<KafkaConsumerBuilder> consumerBuilderConsumer);

	@ProviderType
	public interface KafkaConsumerBuilder {

		public KafkaConsumer.KafkaConsumerBuilder topics(String... topicNames);

		public KafkaConsumer.KafkaConsumerBuilder bootstrapServerConfig(
			String bootstrapServerConfig);

		public KafkaConsumer.KafkaConsumerBuilder clientId(String client);
		public KafkaConsumer.KafkaConsumerBuilder groupId(String groupId);

		public KafkaConsumer.KafkaConsumerBuilder keyDeserializerClass(
			Class<? extends Deserializer<?>> clazz);

		public KafkaConsumer.KafkaConsumerBuilder valueDeserializerClass(
			Class<? extends Deserializer<?>> clazz);

		public KafkaConsumer.KafkaConsumerBuilder autoOffsetResetConfig(
			String autoOffsetResetConfig);

	}
}
