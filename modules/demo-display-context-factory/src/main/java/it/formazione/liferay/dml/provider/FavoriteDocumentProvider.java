package it.formazione.liferay.dml.provider;

import it.formazione.liferay.dml.model.dto.FavoriteDocumentDTO;

import java.util.List;

/**
 * @author Virgilio Alessandro 20/ago/2021
 **/
public interface FavoriteDocumentProvider {

	public FavoriteDocumentDTO addEntry(long userId, FavoriteDocumentDTO doc);

	public List<FavoriteDocumentDTO> getUserFavoriteDocuments(
		long userId);


}