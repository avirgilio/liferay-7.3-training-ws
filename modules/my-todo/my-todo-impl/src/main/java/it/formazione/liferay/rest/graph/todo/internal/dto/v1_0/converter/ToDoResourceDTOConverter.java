package it.formazione.liferay.rest.graph.todo.internal.dto.v1_0.converter;

import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import my.todo.db.model.Todo;
import org.osgi.service.component.annotations.Component;

/**
 * @author Virgilio Alessandro 29/ago/2021
 **/
@Component(
	property = "dto.class.name=my.todo.db.model.Todo",
	service = {ToDoResourceDTOConverter.class, DTOConverter.class}
)
public class ToDoResourceDTOConverter implements
	DTOConverter<Todo, it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo> {

	@Override
	public String getContentType() {
		return it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo.class.getSimpleName();
	}

	@Override
	public it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo toDTO(
			DTOConverterContext dtoConverterContext, Todo todo)
		throws Exception {

		if (todo == null) {
			return null;
		}

		return new it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo() {
			{
				description = todo.getDescription();
				completed = todo.getCompleted();
				id = Math.toIntExact(todo.getTodoId());
			}
		};
	}
}
