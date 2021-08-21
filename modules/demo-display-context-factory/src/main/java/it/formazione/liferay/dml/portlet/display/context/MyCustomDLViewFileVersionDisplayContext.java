package it.formazione.liferay.dml.portlet.display.context;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.display.context.BaseDLViewFileVersionDisplayContext;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.petra.io.unsync.UnsyncStringWriter;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.taglib.ui.JavaScriptMenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import it.formazione.liferay.dml.constants.MyCustomContextFactoryPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author Virgilio Alessandro 18/ago/2021
 **/
public class MyCustomDLViewFileVersionDisplayContext
	extends BaseDLViewFileVersionDisplayContext {

	public MyCustomDLViewFileVersionDisplayContext(
		DLViewFileVersionDisplayContext parentDLDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		FileVersion fileVersion
	) {

		super(_UUID, parentDLDisplayContext, httpServletRequest,
			httpServletResponse,
			fileVersion);

		_themeDisplay =
			(ThemeDisplay) httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_httpServletRequest = httpServletRequest;
	}

	@Override
	public Menu getMenu() throws PortalException {

		_log.info(
			"DL VIEW FILE VERSION: GET MENU'");

		Menu menu = super.getMenu();

		if (_log.isDebugEnabled()) {
			_log.debug("Adding custom entry");
		}

		JavaScriptMenuItem showDocumentInfoMenuItem =
			_getShowDocumentInfoJavascriptMenuItem(fileVersion);

		menu.getMenuItems().add(showDocumentInfoMenuItem);

		return menu;
	}

	private JavaScriptMenuItem _getShowDocumentInfoJavascriptMenuItem(
			FileVersion fileVersion)
		throws PortalException {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", _themeDisplay.getLocale(), getClass());

		JavaScriptMenuItem javaScriptMenuItem = new JavaScriptMenuItem();

		javaScriptMenuItem.setKey(
			MyCustomDLViewFileVersionDisplayContext.class.getName()
			+ "#showItem");

		javaScriptMenuItem.setLabel(
			LanguageUtil.get(resourceBundle, "show-add-to-favorite-panel"));

		String portletNamespace = PortalUtil.getPortletNamespace(
			MyCustomContextFactoryPortletKeys.MY_CUSTOM_CONTEXT_FACTORY);

		String title = LanguageUtil.format(
			resourceBundle, "show-document-x",
			DLAppServiceUtil
				.getFileEntry(fileVersion.getFileEntryId())
				.getFileName());

		javaScriptMenuItem.setOnClick(
			StringBundler.concat(
				portletNamespace,
				"showView(", "\"", title, "\"", ", ",
				"\"", _createViewURL(fileVersion), "\"", ");"));

		String javaScript =
			"/it/formazione/liferay/dml/display/context/" +
			"dependencies/popup_js.ftl";

		Class<?> clazz = getClass();

		URLTemplateResource urlTemplateResource = new URLTemplateResource(
			javaScript, clazz.getResource(javaScript));

		Template template = TemplateManagerUtil.getTemplate(
			TemplateConstants.LANG_TYPE_FTL, urlTemplateResource, false);

		javaScriptMenuItem.setJavaScript(
			_processJSTemplate(
				portletNamespace, template,
				_themeDisplay.getPortletDisplay().getNamespace())
		);

		return javaScriptMenuItem;
	}

	private String _createViewURL(FileVersion fileVersion)  {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			_httpServletRequest, DLPortletKeys.DOCUMENT_LIBRARY,
			_themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		try {

			portletURL.setParameter(
				"mvcRenderCommandName", "/action/myViewRenderCommand");

			FileEntry fileEntry = fileVersion.getFileEntry();

			portletURL.setWindowState(LiferayWindowState.POP_UP);

			String previewURL = DLURLHelperUtil.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), _themeDisplay,
				StringPool.BLANK);

			String downloadURL = DLURLHelperUtil.getDownloadURL(fileEntry,
				fileEntry.getFileVersion(),
				_themeDisplay, StringPool.BLANK);

			portletURL.setParameter("fileVersionId",
				String.valueOf(fileVersion.getFileVersionId()));

			portletURL.setParameter("previewURL", previewURL);
			portletURL.setParameter("downloadURL", downloadURL);

			portletURL.setParameter("redirect", _themeDisplay.getURLCurrent());
		}
		catch (WindowStateException | PortalException e) {
			_log.error(e,e);
		}

		return portletURL.toString();
	}

	private String _processJSTemplate(
			String portletNamespace, Template template,
			String originalPortletNamespace)
		throws PortalException {

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.put("jsNamespace", portletNamespace);
		template.put("originalPortletNamespace", originalPortletNamespace);

		template.processTemplate(unsyncStringWriter);

		return unsyncStringWriter.toString();
	}

	private final ThemeDisplay _themeDisplay;
	private final HttpServletRequest _httpServletRequest;

	private static final UUID _UUID =
		UUID.fromString("42aad0e4-f916-45fd-bdb7-268797013987");

	private static final Log _log = LogFactoryUtil.getLog(
		MyCustomDLViewFileVersionDisplayContext.class);
}
