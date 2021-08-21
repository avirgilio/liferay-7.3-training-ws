package it.formazione.liferay.dml.portlet.display.context.documents;

import it.formazione.liferay.dml.model.dto.FavoriteDocumentDTO;
import it.formazione.liferay.dml.provider.util.FavoriteDocumentProviderUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Virgilio Alessandro 20/ago/2021
 **/
public class MyFavoriteDocumentsDisplayContext {

	public MyFavoriteDocumentsDisplayContext(
		long userId, HttpServletRequest request) {

		List<FavoriteDocumentDTO> favoriteDocuments =
			FavoriteDocumentProviderUtil.getUserFavoriteDocuments(userId);

		request.setAttribute("favoriteDocuments", favoriteDocuments);
	}

}
