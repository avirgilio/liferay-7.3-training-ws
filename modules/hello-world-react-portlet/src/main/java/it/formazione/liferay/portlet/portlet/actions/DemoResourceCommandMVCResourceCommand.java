package it.formazione.liferay.portlet.portlet.actions;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import it.formazione.liferay.portlet.constants.HelloWorldReactPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Set;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + HelloWorldReactPortletKeys.HELLO_WORLD_REACT,
		"mvc.command.name=myResourceCommand"
	},
	service = MVCResourceCommand.class)
public class DemoResourceCommandMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		if (_log.isDebugEnabled()) {

			_log.debug("Render user notification handler");

			Set<String> availableRequestParameters =
				resourceRequest.getParameterMap().keySet();

			_log.debug("Available parameters: " +
					   StringUtil.merge(availableRequestParameters));
		}

		JSONObject notificationPayload = JSONFactoryUtil.createJSONObject();

		notificationPayload.put(
			"name", ParamUtil.getString(resourceRequest, "name"));

		notificationPayload.put(
			"surname", ParamUtil.getString(resourceRequest, "surname"));

		notificationPayload.put(
			"age", ParamUtil.getString(resourceRequest, "age"));

		NotificationEvent notificationEvent =
			NotificationEventFactoryUtil.createNotificationEvent(
				System.currentTimeMillis(),
				HelloWorldReactPortletKeys.HELLO_WORLD_REACT,
				notificationPayload);

		_userNotificationEventLocalService.addUserNotificationEvent(
			PortalUtil.getUserId(resourceRequest), notificationEvent);
	}

	@Reference
	private UserNotificationEventLocalService
		_userNotificationEventLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		DemoResourceCommandMVCResourceCommand.class);
}
