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

package it.formazione.liferay.elastic.dsl.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import it.formazione.liferay.elastic.dsl.service.CourseServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CourseServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>it.formazione.liferay.elastic.dsl.model.CourseSoap</code>. If the method in the
 * service utility returns a
 * <code>it.formazione.liferay.elastic.dsl.model.Course</code>, that is translated to a
 * <code>it.formazione.liferay.elastic.dsl.model.CourseSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class CourseServiceSoap {

	public static it.formazione.liferay.elastic.dsl.model.CourseSoap
			createCourse(
				long groupId, String courseName, String courseDescription,
				int courseType)
		throws RemoteException {

		try {
			it.formazione.liferay.elastic.dsl.model.Course returnValue =
				CourseServiceUtil.createCourse(
					groupId, courseName, courseDescription, courseType);

			return it.formazione.liferay.elastic.dsl.model.CourseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static it.formazione.liferay.elastic.dsl.model.CourseSoap getCourse(
			long courseId)
		throws RemoteException {

		try {
			it.formazione.liferay.elastic.dsl.model.Course returnValue =
				CourseServiceUtil.getCourse(courseId);

			return it.formazione.liferay.elastic.dsl.model.CourseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static it.formazione.liferay.elastic.dsl.model.CourseSoap
			deleteCourse(long courseId)
		throws RemoteException {

		try {
			it.formazione.liferay.elastic.dsl.model.Course returnValue =
				CourseServiceUtil.deleteCourse(courseId);

			return it.formazione.liferay.elastic.dsl.model.CourseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static it.formazione.liferay.elastic.dsl.model.CourseSoap[]
			getCoursesByGroupId()
		throws RemoteException {

		try {
			java.util.List<it.formazione.liferay.elastic.dsl.model.Course>
				returnValue = CourseServiceUtil.getCoursesByGroupId();

			return it.formazione.liferay.elastic.dsl.model.CourseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static it.formazione.liferay.elastic.dsl.model.CourseSoap[]
			getCoursesByCourseType(int courseType)
		throws RemoteException {

		try {
			java.util.List<it.formazione.liferay.elastic.dsl.model.Course>
				returnValue = CourseServiceUtil.getCoursesByCourseType(
					courseType);

			return it.formazione.liferay.elastic.dsl.model.CourseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCoursesByCourseTypeCount(int courseType)
		throws RemoteException {

		try {
			int returnValue = CourseServiceUtil.getCoursesByCourseTypeCount(
				courseType);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCoursesByGroupIdCount() throws RemoteException {
		try {
			int returnValue = CourseServiceUtil.getCoursesByGroupIdCount();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static it.formazione.liferay.elastic.dsl.model.CourseSoap
			updateCourse(
				long courseId, String courseName, String courseDescription,
				int courseType)
		throws RemoteException {

		try {
			it.formazione.liferay.elastic.dsl.model.Course returnValue =
				CourseServiceUtil.updateCourse(
					courseId, courseName, courseDescription, courseType);

			return it.formazione.liferay.elastic.dsl.model.CourseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CourseServiceSoap.class);

}