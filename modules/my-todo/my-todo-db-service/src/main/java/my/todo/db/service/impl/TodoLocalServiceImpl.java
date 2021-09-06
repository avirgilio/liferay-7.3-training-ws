/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package my.todo.db.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import my.todo.db.model.Todo;
import my.todo.db.service.base.TodoLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.Collection;
import java.util.Date;

/**
 * The implementation of the todo local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>my.todo.db.service.TodoLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TodoLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=my.todo.db.model.Todo",
	service = AopService.class
)
public class TodoLocalServiceImpl extends TodoLocalServiceBaseImpl {

	@Override
	public Todo createTodo(
			long userId, long groupId, String description, boolean completed)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long todoId = counterLocalService.increment(Todo.class.getName());

		Todo todo = todoPersistence.create(todoId);

		// AUDIT FIELDS
		todo.setGroupId(groupId);
		todo.setCompanyId(user.getCompanyId());
		todo.setUserId(userId);
		todo.setUserName(user.getFullName());

		Date now = new Date();

		todo.setCreateDate(now);
		todo.setModifiedDate(now);

		// CUSTOM FIELD

		todo.setCompleted(completed);
		todo.setDescription(description);

		return todoPersistence.update(todo);
	}

	@Override
	public Todo updateTodo(
			long todoId, boolean completed, String description)
		throws PortalException {

		Todo todo =
			todoPersistence.findByPrimaryKey(todoId);

		Date now = new Date();

		todo.setModifiedDate(now);

		todo.setDescription(description);
		todo.setCompleted(completed);

		return todoPersistence.update(todo);
	}

	@Override
	public Collection<Todo> findByUserId(long userId) {
		return todoPersistence.findByUserId(userId);
	}

}