package my.todo.web.product.navigation.person.menu;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.product.navigation.personal.menu.BasePersonalMenuEntry;
import com.liferay.product.navigation.personal.menu.PersonalMenuEntry;
import my.todo.web.constants.MyTodoWebPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import java.util.Locale;

/**
 * @author Virgilio Alessandro 30/ago/2021
 **/
@Component(
	immediate = true,
	property = {
		"product.navigation.personal.menu.entry.order:Integer=400",
		"product.navigation.personal.menu.group:Integer=200"
	},
	service = PersonalMenuEntry.class
)
public class MyTodoEntry extends BasePersonalMenuEntry {

	@Override
	public String getPortletId() {
		return MyTodoWebPortletKeys.MY_TODO_WEB;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(getResourceBundle(locale), "my-todo");
	}

	@Override
	public String getIcon(PortletRequest portletRequest) {
		return ENTRY_ICON;
	}

	private static final String ENTRY_ICON = "check-circle";
}
