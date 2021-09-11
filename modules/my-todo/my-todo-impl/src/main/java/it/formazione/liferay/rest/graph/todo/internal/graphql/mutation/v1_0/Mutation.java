package it.formazione.liferay.rest.graph.todo.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo;
import it.formazione.liferay.rest.graph.todo.resource.v1_0.TodoResource;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Virgilio Alessandro
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setTodoResourceComponentServiceObjects(
		ComponentServiceObjects<TodoResource>
			todoResourceComponentServiceObjects) {

		_todoResourceComponentServiceObjects =
			todoResourceComponentServiceObjects;
	}

	@GraphQLField
	public boolean deleteTodo(@GraphQLName("todoId") Integer todoId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_todoResourceComponentServiceObjects,
			this::_populateResourceContext,
			todoResource -> todoResource.deleteTodo(todoId));

		return true;
	}

	@GraphQLField
	public Response deleteTodoBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_todoResourceComponentServiceObjects,
			this::_populateResourceContext,
			todoResource -> todoResource.deleteTodoBatch(callbackURL, object));
	}

	@GraphQLField
	public Todo updateTodo(
			@GraphQLName("entityId") Integer entityId,
			@GraphQLName("todo") Todo todo)
		throws Exception {

		return _applyComponentServiceObjects(
			_todoResourceComponentServiceObjects,
			this::_populateResourceContext,
			todoResource -> todoResource.putTodo(entityId, todo));
	}

	@GraphQLField
	public Response updateTodoBatch(
			@GraphQLName("entityId") Integer entityId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_todoResourceComponentServiceObjects,
			this::_populateResourceContext,
			todoResource -> todoResource.putTodoBatch(
				entityId, callbackURL, object));
	}

	@GraphQLField
	public Todo createTodo(@GraphQLName("todo") Todo todo) throws Exception {
		return _applyComponentServiceObjects(
			_todoResourceComponentServiceObjects,
			this::_populateResourceContext,
			todoResource -> todoResource.postTodo(todo));
	}

	@GraphQLField
	public Response createTodoBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_todoResourceComponentServiceObjects,
			this::_populateResourceContext,
			todoResource -> todoResource.postTodoBatch(callbackURL, object));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(TodoResource todoResource)
		throws Exception {

		todoResource.setContextAcceptLanguage(_acceptLanguage);
		todoResource.setContextCompany(_company);
		todoResource.setContextHttpServletRequest(_httpServletRequest);
		todoResource.setContextHttpServletResponse(_httpServletResponse);
		todoResource.setContextUriInfo(_uriInfo);
		todoResource.setContextUser(_user);
		todoResource.setGroupLocalService(_groupLocalService);
		todoResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<TodoResource>
		_todoResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}