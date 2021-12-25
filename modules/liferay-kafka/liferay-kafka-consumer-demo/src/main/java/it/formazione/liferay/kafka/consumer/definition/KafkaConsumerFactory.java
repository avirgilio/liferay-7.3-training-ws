package it.formazione.liferay.kafka.consumer.definition;

import org.apache.kafka.common.serialization.Deserializer;
import org.osgi.annotation.versioning.ProviderType;
import reactor.kafka.receiver.KafkaReceiver;

import java.util.function.Consumer;

/**
 * @author Virgilio Alessandro 12/dic/2021
 **/
@ProviderType
public interface KafkaConsumerFactory {

	public <A,B> KafkaReceiver<A, B> on(
		Consumer<KafkaConsumerBuilder> consumerBuilderConsumer);

	@ProviderType
	public interface KafkaConsumerBuilder {

		public KafkaConsumerFactory.KafkaConsumerBuilder topics(String... topicNames);

		public KafkaConsumerFactory.KafkaConsumerBuilder bootstrapServerConfig(
			String bootstrapServerConfig);

		public KafkaConsumerFactory.KafkaConsumerBuilder clientId(String client);
		public KafkaConsumerFactory.KafkaConsumerBuilder groupId(String groupId);

		public KafkaConsumerFactory.KafkaConsumerBuilder keyDeserializerClass(
			Class<? extends Deserializer<?>> clazz);

		public KafkaConsumerFactory.KafkaConsumerBuilder valueDeserializerClass(
			Class<? extends Deserializer<?>> clazz);

		public KafkaConsumerFactory.KafkaConsumerBuilder autoOffsetResetConfig(
			String autoOffsetResetConfig);

	}
}
