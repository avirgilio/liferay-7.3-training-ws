package it.formazione.liferay.dml.portlet.action;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import it.formazione.liferay.dml.exception.ExistingFavoriteDocumentException;
import it.formazione.liferay.dml.model.dto.FavoriteDocumentDTO;
import it.formazione.liferay.dml.provider.FavoriteDocumentProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Virgilio Alessandro 20/ago/2021
 **/
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"mvc.command.name=/action/add-document-to-favorite"
	},
	service = MVCActionCommand.class
)
public class AddDocumentToFavoriteMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userId = ParamUtil.getLong(actionRequest, "userId");

		String creationDate =
			ParamUtil.getString(actionRequest, "creationDate");

		String title = ParamUtil.getString(actionRequest, "docName");

		String creator =
			ParamUtil.getString(actionRequest, "creator");

		String description =
			ParamUtil.getString(actionRequest, "docDescription");

		String downloadURL =
			ParamUtil.getString(actionRequest, "downloadURL");

		String previewURL =
			ParamUtil.getString(actionRequest, "previewURL");

		FavoriteDocumentDTO documentDTO =
			FavoriteDocumentDTO.of(
				userId, creator, title, description, creationDate,
				downloadURL, previewURL);

		if (_log.isDebugEnabled()) {
			_log.debug("Added document to favorites: ");
			_log.debug(documentDTO.toString());
		}

		hideDefaultSuccessMessage(actionRequest);
		hideDefaultErrorMessage(actionRequest);

		try {

			_favoriteDocumentProvider.addEntry(userId, documentDTO);
			SessionMessages.add(actionRequest, "addedDocumentToFavorite");

		} catch (ExistingFavoriteDocumentException e) {
			_log.error(e);
			SessionErrors.add(actionRequest, e.getClass().getName());
		}

		sendRedirect(
			actionRequest, actionResponse,
			ParamUtil.getString(actionRequest, "redirect"));
	}

	@Reference
	private FavoriteDocumentProvider _favoriteDocumentProvider;

	private static final Log _log = LogFactoryUtil.getLog(
		AddDocumentToFavoriteMVCActionCommand.class);
}
