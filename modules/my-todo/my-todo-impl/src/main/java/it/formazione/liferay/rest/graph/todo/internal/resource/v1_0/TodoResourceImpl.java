package it.formazione.liferay.rest.graph.todo.internal.resource.v1_0;

import com.liferay.portal.vulcan.pagination.Page;
import it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo;
import it.formazione.liferay.rest.graph.todo.resource.v1_0.TodoResource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Virgilio Alessandro
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/todo.properties",
	scope = ServiceScope.PROTOTYPE,
	service = TodoResource.class
)
public class TodoResourceImpl extends BaseTodoResourceImpl {

	Map<Integer, Todo> entities = new HashMap<>();

	@Override
	public Todo getTodo(@NotNull Integer todoId) throws Exception {
		return entities.get(todoId);
	}

	@Override
	public Todo postTodo(Todo todo) throws Exception {
		return entities.put(todo.getId(), todo);
	}

	@Override
	public Page<Todo> getTodosPage() throws Exception {
		return Page.of(entities.values());
	}

	@Override
	public void deleteTodo(@NotNull Integer todoId) throws Exception {
		entities.remove(todoId);
	}

	@Override
	public Todo putTodo(@NotNull Integer entityId, Todo todo) throws Exception {
		return entities.put(entityId, todo);
	}
}