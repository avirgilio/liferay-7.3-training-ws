package it.formazione.liferay.rest.graph.todo.internal.resource.v1_0;

import it.formazione.liferay.rest.graph.todo.resource.v1_0.TodoResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Virgilio Alessandro
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/todo.properties",
	scope = ServiceScope.PROTOTYPE, service = TodoResource.class
)
public class TodoResourceImpl extends BaseTodoResourceImpl {
}