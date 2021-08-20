package it.formazione.liferay.dml.provider;

import it.formazione.liferay.dml.model.dto.FavoriteDocumentDTO;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component(
	immediate = true,
	service = FavoriteDocumentProvider.class
)
public class CacheFavoriteDocumentProviderImpl
	implements FavoriteDocumentProvider {

	@Override
	public FavoriteDocumentDTO addEntry(long userId, FavoriteDocumentDTO doc) {

		List<FavoriteDocumentDTO> favoriteDocuments =
			_favoriteDocumentMap.getOrDefault(userId, new ArrayList<>());

		favoriteDocuments.add(doc);
		_favoriteDocumentMap.put(userId, favoriteDocuments);

		 return doc;
	}

	@Override
	public List<FavoriteDocumentDTO> getUserFavoriteDocuments(long userId) {

		return _favoriteDocumentMap.get(userId);
	}

	private ConcurrentHashMap<Long, List<FavoriteDocumentDTO>>
		_favoriteDocumentMap = new ConcurrentHashMap<>();
}
