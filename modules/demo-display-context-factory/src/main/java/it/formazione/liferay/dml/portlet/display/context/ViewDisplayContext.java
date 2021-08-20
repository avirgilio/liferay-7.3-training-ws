package it.formazione.liferay.dml.portlet.display.context;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileVersionServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * @author Virgilio Alessandro 19/ago/2021
 **/
public class ViewDisplayContext {

	public ViewDisplayContext(long fileVersionId, HttpServletRequest request)
		throws PortalException {

		_fileVersion = DLFileVersionServiceUtil.getFileVersion(fileVersionId);
		_themeDisplay = (ThemeDisplay) request.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public String getDocumentTitle() {
		return _fileVersion.getTitle();
	}

	public String getDocumentCreatorUserName() {
		return _fileVersion.getUserName();
	}

	public String getDocumentDescription() {

		String documentDescription = _fileVersion.getDescription();

		ResourceBundle resourceBundle = _getResourceBundle();

		return Validator.isBlank(documentDescription)
			? resourceBundle.getString("document-has-no-description")
			: documentDescription;
	}

	private ResourceBundle _getResourceBundle() {
		return ResourceBundleUtil.getBundle(
			_themeDisplay.getLocale(), getClass());
	}

	public String getDocumentCreationDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(_fileVersion.getCreateDate());
	}

	private final ThemeDisplay _themeDisplay;
	private final DLFileVersion _fileVersion;
}
