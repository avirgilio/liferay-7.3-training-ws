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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import it.formazione.liferay.elastic.dsl.service.CourseServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>CourseServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseServiceSoap
 * @generated
 */
public class CourseServiceHttp {

	public static it.formazione.liferay.elastic.dsl.model.Course createCourse(
			HttpPrincipal httpPrincipal, long groupId, String courseName,
			String courseDescription, int courseType)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "createCourse",
				_createCourseParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, courseName, courseDescription, courseType);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (it.formazione.liferay.elastic.dsl.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static it.formazione.liferay.elastic.dsl.model.Course getCourse(
			HttpPrincipal httpPrincipal, long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "getCourse",
				_getCourseParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (it.formazione.liferay.elastic.dsl.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static it.formazione.liferay.elastic.dsl.model.Course deleteCourse(
			HttpPrincipal httpPrincipal, long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "deleteCourse",
				_deleteCourseParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (it.formazione.liferay.elastic.dsl.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<it.formazione.liferay.elastic.dsl.model.Course>
			getCoursesByGroupId(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "getCoursesByGroupId",
				_getCoursesByGroupIdParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<it.formazione.liferay.elastic.dsl.model.Course>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<it.formazione.liferay.elastic.dsl.model.Course>
			getCoursesByCourseType(HttpPrincipal httpPrincipal, int courseType)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "getCoursesByCourseType",
				_getCoursesByCourseTypeParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseType);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<it.formazione.liferay.elastic.dsl.model.Course>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCoursesByCourseTypeCount(
			HttpPrincipal httpPrincipal, int courseType)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "getCoursesByCourseTypeCount",
				_getCoursesByCourseTypeCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseType);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCoursesByGroupIdCount(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "getCoursesByGroupIdCount",
				_getCoursesByGroupIdCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static it.formazione.liferay.elastic.dsl.model.Course updateCourse(
			HttpPrincipal httpPrincipal, long courseId, String courseName,
			String courseDescription, int courseType)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "updateCourse",
				_updateCourseParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, courseName, courseDescription, courseType);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (it.formazione.liferay.elastic.dsl.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CourseServiceHttp.class);

	private static final Class<?>[] _createCourseParameterTypes0 = new Class[] {
		long.class, String.class, String.class, int.class
	};
	private static final Class<?>[] _getCourseParameterTypes1 = new Class[] {
		long.class
	};
	private static final Class<?>[] _deleteCourseParameterTypes2 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getCoursesByGroupIdParameterTypes3 =
		new Class[] {};
	private static final Class<?>[] _getCoursesByCourseTypeParameterTypes4 =
		new Class[] {int.class};
	private static final Class<?>[]
		_getCoursesByCourseTypeCountParameterTypes5 = new Class[] {int.class};
	private static final Class<?>[] _getCoursesByGroupIdCountParameterTypes6 =
		new Class[] {};
	private static final Class<?>[] _updateCourseParameterTypes7 = new Class[] {
		long.class, String.class, String.class, int.class
	};

}