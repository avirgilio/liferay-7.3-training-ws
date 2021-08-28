package it.formazione.liferay.rest.graph.todo.internal.graphql.servlet.v1_0;

import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import it.formazione.liferay.rest.graph.todo.internal.graphql.mutation.v1_0.Mutation;
import it.formazione.liferay.rest.graph.todo.internal.graphql.query.v1_0.Query;
import it.formazione.liferay.rest.graph.todo.resource.v1_0.TodoResource;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Virgilio Alessandro
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setTodoResourceComponentServiceObjects(
			_todoResourceComponentServiceObjects);

		Query.setTodoResourceComponentServiceObjects(
			_todoResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/my-todo-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TodoResource>
		_todoResourceComponentServiceObjects;

}