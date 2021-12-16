/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package it.formazione.liferay.kafka.producer.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import it.formazione.liferay.kafka.producer.definition.KafkaProducerFactory;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Virgilio Alessandro
 */
@Component(
	enabled = false,
	immediate = true,
	service = KafkaSimpleProducerSchedulerMessagesListener.class
)
public class KafkaSimpleProducerSchedulerMessagesListener
	extends BaseMessageListener {

	@interface KafkaSchedulerConfig {
		String clientId() default "simple-producer";
		String bootstrapServerConfig() default "localhost:9092";
		String acksConfig() default "all";
		String cronExpression() default "0 * * ? * *";
	}

	@Activate
	@Modified
	protected void activate(KafkaSchedulerConfig config) {

		String cronExpression = GetterUtil.getString(
			config.cronExpression(), _DEFAULT_CRON_EXPRESSION);

		Class<?> clazz = getClass();

		Trigger trigger = _triggerFactory.createTrigger(
			clazz.getName(), clazz.getName(), new Date(), null, cronExpression);

		_schedulerEntryImpl = new SchedulerEntryImpl(clazz.getName(), trigger);

		if (_initialized) {
			deactivate();
		}

		_schedulerEngineHelper.register(this, _schedulerEntryImpl,
			DestinationNames.SCHEDULER_DISPATCH);

		_initialized = true;

		_sender = _kafkaProducer.builder(
			producer ->
				producer
					.bootstrapServerConfig(config.bootstrapServerConfig())
					.clientId(config.clientId())
					.acksConfig(config.acksConfig())
					.keySerializerClass(IntegerSerializer.class)
					.valueSerializerClass(StringSerializer.class)
		);
	}

	@Deactivate
	protected void deactivate() {

		if (_initialized) {
			try {
				if (_log.isDebugEnabled()) {
					_log.debug("Unscheduling trigger");
				}

				_schedulerEngineHelper.unschedule(
					_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);
			}
			catch (SchedulerException schedulerException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to unschedule trigger", schedulerException);
				}
			}

			_schedulerEngineHelper.unregister(this);
		}

		_initialized = false;
		_sender.close();
	}

	@Override
	protected void doReceive(Message message) throws Exception {

		SimpleDateFormat dateFormat =
			new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");

		_sender.send(Flux.range(1, 10)
			.map(i -> SenderRecord.create
				(new ProducerRecord<>("lfr-events-topic", i, "Message_" + i), i)))
			.doOnError(e -> _log.error("Send failed", e))
			.subscribe(r -> {

				RecordMetadata metadata = r.recordMetadata();

				_log.info(
					"Message sent successfully in topic: "
					+ metadata.topic()
					+ " value: " + r.correlationMetadata()
					+  " timestamp = " +
						dateFormat.format(new Date(metadata.timestamp()))
					);
			});
	}

	private static final String _DEFAULT_CRON_EXPRESSION = "0 0 0 * * ?";

	private static final Log _log = LogFactoryUtil.getLog(
		KafkaSimpleProducerSchedulerMessagesListener.class);

	private volatile boolean _initialized;
	private KafkaSender<Integer, String> _sender;

	@Reference
	private KafkaProducerFactory _kafkaProducer;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	private SchedulerEntryImpl _schedulerEntryImpl;

	@Reference
	private TriggerFactory _triggerFactory;

}