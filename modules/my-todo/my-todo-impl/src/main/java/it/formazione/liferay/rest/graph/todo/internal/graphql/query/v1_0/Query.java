package it.formazione.liferay.rest.graph.todo.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo;
import it.formazione.liferay.rest.graph.todo.resource.v1_0.TodoResource;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Virgilio Alessandro
 * @generated
 */
@Generated("")
public class Query {

	public static void setTodoResourceComponentServiceObjects(
		ComponentServiceObjects<TodoResource>
			todoResourceComponentServiceObjects) {

		_todoResourceComponentServiceObjects =
			todoResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {todo(todoId: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public void todo(@GraphQLName("todoId") Integer todoId) throws Exception {
		return _applyComponentServiceObjects(
			_todoResourceComponentServiceObjects,
			this::_populateResourceContext,
			todoResource -> todoResource.getTodo(todoId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {todos{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TodoPage todos() throws Exception {
		return _applyComponentServiceObjects(
			_todoResourceComponentServiceObjects,
			this::_populateResourceContext,
			todoResource -> new TodoPage(todoResource.getTodosPage()));
	}

	@GraphQLName("TodoPage")
	public class TodoPage {

		public TodoPage(Page todoPage) {
			actions = todoPage.getActions();

			items = todoPage.getItems();
			lastPage = todoPage.getLastPage();
			page = todoPage.getPage();
			pageSize = todoPage.getPageSize();
			totalCount = todoPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Todo> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

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
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}