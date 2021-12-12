package it.formazione.liferay.kafka.consumer.definition;

import com.liferay.portal.kernel.util.ListUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.osgi.service.component.annotations.Component;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Virgilio Alessandro 12/dic/2021
 **/
@Component(immediate = true, service = KafkaConsumer.class)
public class KafkaConsumerImpl implements KafkaConsumer{

	@Override
	public KafkaReceiver<?, ?> on(
		Consumer<KafkaConsumerBuilder> consumerBuilderConsumer) {

		KafkaConsumerBuilderImpl kafkaConsumerBuilder =
			new KafkaConsumerBuilderImpl();

		consumerBuilderConsumer.accept(kafkaConsumerBuilder);

		return kafkaConsumerBuilder.build();
	}

	private static class KafkaConsumerBuilderImpl
		implements KafkaConsumerBuilder {

		public KafkaReceiver<?, ?> build() {

			Map<String, Object> props = new HashMap<>();

			props.put(
				ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				_bootstrapServerConfig);

			props.put(ConsumerConfig.CLIENT_ID_CONFIG, _clientId);
			props.put(ConsumerConfig.GROUP_ID_CONFIG, _groupId);

			props.put(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				_keyDeserializerClass);

			props.put(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				_valueDeserializerClass);

			props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
				_autoOffsetResetConfig);

			ReceiverOptions<?, ?> receiverOptions =
				ReceiverOptions.create(props);

			receiverOptions.subscription(ListUtil.fromArray(_topics));

			return KafkaReceiver.create(receiverOptions);
		}

		@Override
		public KafkaConsumerBuilder topics(String... topicNames) {
			_topics = topicNames;
			return this;
		}

		@Override
		public KafkaConsumerBuilder bootstrapServerConfig(
			String bootstrapServerConfig) {
			_bootstrapServerConfig = bootstrapServerConfig;
			return this;
		}

		@Override
		public KafkaConsumerBuilder clientId(String client) {
			_clientId = client;
			return this;
		}

		@Override
		public KafkaConsumerBuilder groupId(String groupId) {
			_groupId = groupId;
			return this;
		}

		@Override
		public KafkaConsumerBuilder keyDeserializerClass(
			Class<? extends Deserializer<?>> clazz) {
			_keyDeserializerClass = clazz;
			return this;
		}

		@Override
		public KafkaConsumerBuilder valueDeserializerClass(
			Class<? extends Deserializer<?>> clazz) {
			_valueDeserializerClass = clazz;
			return this;
		}

		@Override
		public KafkaConsumerBuilder autoOffsetResetConfig(
			String autoOffsetResetConfig) {

			_autoOffsetResetConfig = autoOffsetResetConfig;
			return this;
		}

		private String _bootstrapServerConfig;
		private String _clientId;
		private String _groupId;
		private String[] _topics;
		private Class<? extends Deserializer<?>> _keyDeserializerClass;
		private Class<? extends Deserializer<?>> _valueDeserializerClass;
		private String _autoOffsetResetConfig;
	}
}
