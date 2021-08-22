package it.formazione.liferay.portlet.actions;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import it.formazione.liferay.constants.HelloWorldReactPortletKeys;
import org.osgi.service.component.annotations.Component;

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

			_log.debug("demo resource command");

			Set<String> availableRequestParameters =
				resourceRequest.getParameterMap().keySet();

			_log.debug("Available parameters: "
					   + StringUtil.merge(availableRequestParameters));
		}

		String name = ParamUtil.getString(resourceRequest, "name");
		String surname = ParamUtil.getString(resourceRequest, "surname");
		String age = ParamUtil.getString(resourceRequest, "age");

		JSONObject values = JSONFactoryUtil.createJSONObject();

		values.put("age", age);
		values.put("name", name);
		values.put("surname", surname);

		JSONObject response = JSONFactoryUtil.createJSONObject();

		response.put("status", "ok");
		response.put("insertedValues", values);

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse, response);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DemoResourceCommandMVCResourceCommand.class);
}
