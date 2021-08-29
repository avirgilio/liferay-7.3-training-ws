package it.formazione.liferay.rest.graph.todo.resource.v1_0;

import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;

import it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo;

import java.util.Locale;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/my-todo/v1.0
 *
 * @author Virgilio Alessandro
 * @generated
 */
@Generated("")
@ProviderType
public interface TodoResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public void deleteTodo(Integer todoId) throws Exception;

	public Response deleteTodoBatch(String callbackURL, Object object)
		throws Exception;

	public Todo getTodo(Integer todoId) throws Exception;

	public Todo putTodo(Integer entityId, Todo todo) throws Exception;

	public Response putTodoBatch(
			Integer entityId, String callbackURL, Object object)
		throws Exception;

	public Page<Todo> getTodosPage() throws Exception;

	public Todo postTodo(Todo todo) throws Exception;

	public Response postTodoBatch(String callbackURL, Object object)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser);

	public void setGroupLocalService(GroupLocalService groupLocalService);

	public void setRoleLocalService(RoleLocalService roleLocalService);

	public static class FactoryHolder {

		public static volatile Factory factory;

	}

	@ProviderType
	public interface Builder {

		public TodoResource build();

		public Builder checkPermissions(boolean checkPermissions);

		public Builder httpServletRequest(
			HttpServletRequest httpServletRequest);

		public Builder preferredLocale(Locale preferredLocale);

		public Builder user(com.liferay.portal.kernel.model.User user);

	}

	@ProviderType
	public interface Factory {

		public Builder create();

	}

}