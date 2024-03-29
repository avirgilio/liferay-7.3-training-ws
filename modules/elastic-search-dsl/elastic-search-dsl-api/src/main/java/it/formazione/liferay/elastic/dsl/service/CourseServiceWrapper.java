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

package it.formazione.liferay.elastic.dsl.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CourseService}.
 *
 * @author Brian Wing Shun Chan
 * @see CourseService
 * @generated
 */
public class CourseServiceWrapper
	implements CourseService, ServiceWrapper<CourseService> {

	public CourseServiceWrapper(CourseService courseService) {
		_courseService = courseService;
	}

	@Override
	public it.formazione.liferay.elastic.dsl.model.Course createCourse(
			long groupId, String courseName, String courseDescription,
			int courseType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseService.createCourse(
			groupId, courseName, courseDescription, courseType);
	}

	@Override
	public it.formazione.liferay.elastic.dsl.model.Course deleteCourse(
			long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseService.deleteCourse(courseId);
	}

	@Override
	public it.formazione.liferay.elastic.dsl.model.Course getCourse(
			long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseService.getCourse(courseId);
	}

	@Override
	public java.util.List<it.formazione.liferay.elastic.dsl.model.Course>
			getCoursesByCourseType(int courseType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseService.getCoursesByCourseType(courseType);
	}

	@Override
	public int getCoursesByCourseTypeCount(int courseType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseService.getCoursesByCourseTypeCount(courseType);
	}

	@Override
	public java.util.List<it.formazione.liferay.elastic.dsl.model.Course>
			getCoursesByGroupId()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseService.getCoursesByGroupId();
	}

	@Override
	public int getCoursesByGroupIdCount()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseService.getCoursesByGroupIdCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseService.getOSGiServiceIdentifier();
	}

	@Override
	public it.formazione.liferay.elastic.dsl.model.Course updateCourse(
			long courseId, String courseName, String courseDescription,
			int courseType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseService.updateCourse(
			courseId, courseName, courseDescription, courseType);
	}

	@Override
	public CourseService getWrappedService() {
		return _courseService;
	}

	@Override
	public void setWrappedService(CourseService courseService) {
		_courseService = courseService;
	}

	private CourseService _courseService;

}