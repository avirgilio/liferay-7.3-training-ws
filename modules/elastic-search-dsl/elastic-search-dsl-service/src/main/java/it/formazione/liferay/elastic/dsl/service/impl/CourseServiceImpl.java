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

package it.formazione.liferay.elastic.dsl.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.service.base.CourseServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the course remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>it.formazione.liferay.elastic.dsl.service.CourseService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=elastic-dsl",
		"json.web.service.context.path=Course"
	},
	service = AopService.class
)
public class CourseServiceImpl extends CourseServiceBaseImpl {

	@Override
	public Course createCourse(
			String courseName, String courseDescription, int courseType)
		throws PortalException {

		User user = getUser();

		return courseLocalService.createCourse(
			user.getUserId(), user.getGroupId(), courseName, courseDescription,
			courseType);
	}

	@Override
	public List<Course> getCoursesByGroupId() throws PortalException {
		return courseLocalService.getCoursesByGroupId(getUser().getGroupId());
	}

	@Override
	public List<Course> getCoursesByCourseType(int courseType)
		throws PortalException {

		return courseLocalService.getCoursesByCourseType(
			courseType, getUser().getGroupId());
	}

	@Override
	public int getCoursesByCourseTypeCount(int courseType)
		throws PortalException {

		return courseLocalService.getCoursesByCourseTypeCount(
			courseType, getUser().getGroupId());
	}

	@Override
	public int getCoursesByGroupIdCount() throws PortalException {
		return courseLocalService.getCoursesByGroupIdCount(getUser().getGroupId());
	}

}