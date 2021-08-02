package it.formazione.liferay.personal.menu.entry.product.navigation.personal.menu;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.product.navigation.personal.menu.BasePersonalMenuEntry;
import com.liferay.product.navigation.personal.menu.PersonalMenuEntry;
import it.formazione.liferay.personal.menu.entry.constants.MyPersonalMenuEntryPortletKeys;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

@Component(
	immediate = true,
	property = {
		"product.navigation.personal.menu.entry.order:Integer=400",
		"product.navigation.personal.menu.group:Integer=100"
	},
	service = PersonalMenuEntry.class
)
public class MyPersonalMenuEntry extends BasePersonalMenuEntry {

	@Override
	public String getPortletId() {
		return MyPersonalMenuEntryPortletKeys.MY_PERSONAL_MENU_ENTRY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			getResourceBundle(locale), "my-personal-menu-entry-label");
	}

}
