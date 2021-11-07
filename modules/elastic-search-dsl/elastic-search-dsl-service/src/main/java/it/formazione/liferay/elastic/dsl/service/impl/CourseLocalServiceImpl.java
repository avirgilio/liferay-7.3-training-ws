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
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import it.formazione.liferay.elastic.dsl.model.Course;
import it.formazione.liferay.elastic.dsl.service.base.CourseLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the course local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>it.formazione.liferay.elastic.dsl.service.CourseLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=it.formazione.liferay.elastic.dsl.model.Course",
	service = AopService.class
)
public class CourseLocalServiceImpl extends CourseLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Course createCourse(
			long userId, long groupId, String courseName,
			String courseDescription, int courseType)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long courseId = counterLocalService.increment(Course.class.getName());

		Course course = coursePersistence.create(courseId);

		// AUDIT FIELDS
		course.setGroupId(groupId);
		course.setCompanyId(user.getCompanyId());
		course.setUserId(userId);
		course.setUserName(user.getFullName());

		Date now = new Date();

		course.setCreateDate(now);
		course.setModifiedDate(now);

		// CUSTOM FIELD

		course.setCourseName(courseName);
		course.setCourseType(courseType);
		course.setCourseDescription(courseDescription);

		return coursePersistence.update(course);
	}

	@Override
	public List<Course> getCoursesByGroupId(long groupId) {
		return coursePersistence.findByGroupId(groupId);
	}

	@Override
	public List<Course> getCoursesByCourseType(int courseType, long groupId) {
		return coursePersistence.findByCourseTypeAndGroupId(courseType, groupId);
	}

	@Override
	public int getCoursesByCourseTypeCount(int courseType, long groupId) {
		return coursePersistence.countByCourseTypeAndGroupId(
			courseType, groupId);
	}

	@Override
	public int getCoursesByGroupIdCount(long groupId) {
		return coursePersistence.countByGroupId(groupId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Course updateCourse(
			long courseId, String courseName,
			String courseDescription, int courseType)
		throws PortalException {

		Course course = coursePersistence.findByPrimaryKey(courseId);

		Date now = new Date();
		course.setModifiedDate(now);

		// CUSTOM FIELD

		course.setCourseName(courseName);
		course.setCourseType(courseType);
		course.setCourseDescription(courseDescription);

		return coursePersistence.update(course);
	}
}