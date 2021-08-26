package it.formazione.liferay.portlet.actions;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import it.formazione.liferay.constants.HelloWorldReactPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + HelloWorldReactPortletKeys.HELLO_WORLD_REACT,
		"mvc.command.name=demoActionCommand"
	},
	service = MVCActionCommand.class
)
public class DemoMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String text = ParamUtil.getString(actionRequest, "text");

		_log.info("CALLED ACTION COMMAND!");
		_log.info("TEXT: " + text);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DemoMVCActionCommand.class);
}
