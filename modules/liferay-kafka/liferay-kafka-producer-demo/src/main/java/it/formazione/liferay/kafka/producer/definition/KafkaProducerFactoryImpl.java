package it.formazione.liferay.kafka.producer.definition;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.osgi.service.component.annotations.Component;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Virgilio Alessandro 12/dic/2021
 **/
@Component(immediate = true, service = KafkaProducerFactory.class)
public class KafkaProducerFactoryImpl implements KafkaProducerFactory {

	@Override
	public <A, B> KafkaSender<A, B> builder(
		Consumer<KafkaProducerBuilder> kafkaProducerBuilderConsumer) {

		KafkaProducerBuilderImpl builder = new KafkaProducerBuilderImpl();
		kafkaProducerBuilderConsumer.accept(builder);

		return (KafkaSender<A, B>) builder.build();
	}

	private static class KafkaProducerBuilderImpl
		implements KafkaProducerBuilder {

		public KafkaProducerFactory.KafkaProducerBuilder bootstrapServerConfig(
			String bootstrapServerConfig) {

			_bootstrapServer = bootstrapServerConfig;
			return this;
		}

		public KafkaProducerFactory.KafkaProducerBuilder clientId(
			String clientId) {

			_clientId = clientId;
			return this;
		}

		public KafkaProducerFactory.KafkaProducerBuilder acksConfig(
			String acksConfig) {

			_acksConfig = acksConfig;
			return this;
		}

		public KafkaProducerFactory.KafkaProducerBuilder keySerializerClass(
			Class<? extends Serializer<?>> clazz) {

			_keySerializer = clazz;
			return this;
		}

		public KafkaProducerFactory.KafkaProducerBuilder valueSerializerClass(
			Class<? extends Serializer<?>> clazz) {

			_valueSerializer = clazz;
			return this;
		}

		public KafkaSender<?, ?> build() {

			Map<String, Object> props = new HashMap<>();

			props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, _bootstrapServer);
			props.put(ProducerConfig.CLIENT_ID_CONFIG, _clientId);
			props.put(ProducerConfig.ACKS_CONFIG, _acksConfig);

			props.put(
				ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				_keySerializer);

			props.put(
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				_valueSerializer);

			SenderOptions<String, String> senderOptions =
				SenderOptions.create(props);

			return KafkaSender.create(senderOptions);
		}

		private String _bootstrapServer;
		private String _clientId;
		private String _acksConfig;
		private Class<?> _keySerializer;
		private Class<?> _valueSerializer;
	}
}
