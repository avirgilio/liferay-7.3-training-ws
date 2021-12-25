package it.formazione.liferay.kafka.integration.api;

import reactor.core.Disposable;

import java.util.List;

/**
 * @author Virgilio Alessandro 14/dic/2021
 **/
public interface LiferayKafkaProducer<T> {
	public Disposable produce(List<T> entity);
}
