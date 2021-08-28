package it.formazione.liferay.rest.graph.todo.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Virgilio Alessandro
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false", "osgi.jaxrs.application.base=/my-todo",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=MyTodo"
	},
	service = Application.class
)
@Generated("")
public class MyTodoApplication extends Application {
}