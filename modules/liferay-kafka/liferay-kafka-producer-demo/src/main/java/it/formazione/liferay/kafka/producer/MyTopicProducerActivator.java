package it.formazione.liferay.kafka.producer;

import it.formazione.liferay.kafka.integration.api.LiferayKafkaProducer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;

@Component(immediate = true, service = MyTopicProducerActivator.class)
public class MyTopicProducerActivator {

	@Activate
	public void activate() {
		_liferayKafkaProducer
			.produce(Arrays.asList("MY FIRST MESSAGE", "MSG 2"));
	}

	@Reference(target = "(component.name=it.formazione.liferay.kafka.producer.MyTopicProducer)")
	private LiferayKafkaProducer<String> _liferayKafkaProducer;
}
