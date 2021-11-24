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

import com.liferay.portal.kernel.exception.PortalException;

import it.formazione.liferay.elastic.dsl.model.Course;

import java.util.List;

/**
 * Provides the remote service utility for Course. This utility wraps
 * <code>it.formazione.liferay.elastic.dsl.service.impl.CourseServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CourseService
 * @generated
 */
public class CourseServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>it.formazione.liferay.elastic.dsl.service.impl.CourseServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Course createCourse(
			long groupId, String courseName, String courseDescription,
			int courseType)
		throws PortalException {

		return getService().createCourse(
			groupId, courseName, courseDescription, courseType);
	}

	public static Course deleteCourse(long courseId) throws PortalException {
		return getService().deleteCourse(courseId);
	}

	public static Course getCourse(long courseId) throws PortalException {
		return getService().getCourse(courseId);
	}

	public static List<Course> getCoursesByCourseType(int courseType)
		throws PortalException {

		return getService().getCoursesByCourseType(courseType);
	}

	public static int getCoursesByCourseTypeCount(int courseType)
		throws PortalException {

		return getService().getCoursesByCourseTypeCount(courseType);
	}

	public static List<Course> getCoursesByGroupId() throws PortalException {
		return getService().getCoursesByGroupId();
	}

	public static int getCoursesByGroupIdCount() throws PortalException {
		return getService().getCoursesByGroupIdCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static Course updateCourse(
			long courseId, String courseName, String courseDescription,
			int courseType)
		throws PortalException {

		return getService().updateCourse(
			courseId, courseName, courseDescription, courseType);
	}

	public static CourseService getService() {
		return _service;
	}

	private static volatile CourseService _service;

}