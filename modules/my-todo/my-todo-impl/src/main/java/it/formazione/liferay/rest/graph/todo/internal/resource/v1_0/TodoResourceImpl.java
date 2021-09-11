package it.formazione.liferay.rest.graph.todo.internal.resource.v1_0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.pagination.Page;
import it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo;
import it.formazione.liferay.rest.graph.todo.internal.dto.v1_0.converter.ToDoResourceDTOConverter;
import it.formazione.liferay.rest.graph.todo.resource.v1_0.TodoResource;
import my.todo.db.service.TodoLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Virgilio Alessandro
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/todo.properties",
	scope = ServiceScope.PROTOTYPE,
	service = TodoResource.class
)
public class TodoResourceImpl extends BaseTodoResourceImpl {

	@Override
	public Todo getTodo(@NotNull Integer todoId) throws Exception {
		my.todo.db.model.Todo TodoDB = _todoLocalService.getTodo(todoId);

		return _toDTO(TodoDB);
	}

	@Override
	public Todo postTodo(Todo todo) throws Exception {

		my.todo.db.model.Todo createdTodo = _todoLocalService.createTodo(
			contextUser.getUserId(),
			contextCompany.getGroupId(),
			todo.getDescription(),
			todo.getCompleted()
		);

		return _toDTO(createdTodo);
	}

	@Override
	public Page<Todo> getTodosPage() throws Exception {

		List<Todo> todos = _todoLocalService.findByUserId(
			contextUser.getUserId())
			.stream()
			.map(dbModel -> {
				try {
					return _toDTO(dbModel);
				}
				catch (Exception e) {
					_log.error(e, e);
				}
				return null;
			})
			.collect(Collectors.toList());

		return Page.of(todos);
	}

	@Override
	public void deleteTodo(@NotNull Integer todoId) throws Exception {
		_todoLocalService.deleteTodo(todoId);
	}

	@Override
	public Todo putTodo(@NotNull Integer todoId, Todo todo) throws Exception {

		my.todo.db.model.Todo updatedTodo =
			_todoLocalService.updateTodo(
				todoId, todo.getCompleted(),
				todo.getDescription());

		return _toDTO(updatedTodo);
	}

	private Todo _toDTO(my.todo.db.model.Todo todo) throws Exception {
		return _toDoResourceDTOConverter.toDTO(todo);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TodoResourceImpl.class);

	@Reference
	private TodoLocalService _todoLocalService;

	@Reference
	private ToDoResourceDTOConverter _toDoResourceDTOConverter;
}