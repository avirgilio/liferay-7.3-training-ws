package it.formazione.liferay.portlet.notifications;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import it.formazione.liferay.portlet.constants.HelloWorldReactPortletKeys;
import org.osgi.service.component.annotations.Component;

import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = "javax.portlet.name=" +
			   HelloWorldReactPortletKeys.HELLO_WORLD_REACT,
	service = UserNotificationHandler.class
)
public class DemoUserNotificationHandler extends BaseUserNotificationHandler {

	public DemoUserNotificationHandler() {
		setPortletId(HelloWorldReactPortletKeys.HELLO_WORLD_REACT);
		setActionable(false);
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		String name = jsonObject.getString("name", StringPool.BLANK);
		String surname = jsonObject.getString("surname", StringPool.BLANK);
		Integer age = jsonObject.getInt("age");

		ResourceBundle resourceBundle =
			ResourceBundleUtil.getBundle(
				serviceContext.getLocale(), DemoUserNotificationHandler.class);

		String title = ResourceBundleUtil.getString(
			resourceBundle, "x-y-was-selected-age-y",
			surname, name, age);

		String body = StringPool.BLANK;

		return StringUtil.replace(
			getBodyTemplate(),
			new String[] {"[$TITLE$]", "[$BODY$]"},
			new String[] { title, body }
		);
	}
}
