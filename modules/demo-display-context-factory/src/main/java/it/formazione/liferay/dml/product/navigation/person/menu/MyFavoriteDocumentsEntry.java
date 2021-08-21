package it.formazione.liferay.dml.product.navigation.person.menu;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.product.navigation.personal.menu.BasePersonalMenuEntry;
import com.liferay.product.navigation.personal.menu.PersonalMenuEntry;
import it.formazione.liferay.dml.constants.MyCustomContextFactoryPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import java.util.Locale;

/**
 * @author Virgilio Alessandro 20/ago/2021
 **/
@Component(
	immediate = true,
	property = {
		"product.navigation.personal.menu.entry.order:Integer=300",
		"product.navigation.personal.menu.group:Integer=200"
	},
	service = PersonalMenuEntry.class
)
public class MyFavoriteDocumentsEntry extends BasePersonalMenuEntry {

	@Override
	public String getPortletId() {
		return MyCustomContextFactoryPortletKeys.MY_CUSTOM_CONTEXT_FACTORY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			getResourceBundle(locale), "favorite-documents-menu-entry-label");
	}

	@Override
	public String getIcon(PortletRequest portletRequest) {
		return ENTRY_ICON;
	}

	private static final String ENTRY_ICON = "documents-and-media";
}
