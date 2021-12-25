package it.formazione.liferay.kafka.integration.api;

import reactor.core.Disposable;

/**
 * @author Virgilio Alessandro 19/dic/2021
 **/
public interface LiferayKafkaConsumer {
	public Disposable consume();
}
