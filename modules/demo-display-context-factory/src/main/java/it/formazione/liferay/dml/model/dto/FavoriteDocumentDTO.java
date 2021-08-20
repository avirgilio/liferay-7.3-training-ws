package it.formazione.liferay.dml.model.dto;

/**
 * @author Virgilio Alessandro 20/ago/2021
 **/
public class FavoriteDocumentDTO {

	public static FavoriteDocumentDTO of(
		long userId, String title, String description, String creationDate) {

		return new FavoriteDocumentDTO(userId, title, description, creationDate);
	}

	private FavoriteDocumentDTO(
		long userId, String title, String description, String creationDate) {

		this._userId = userId;
		this._title = title;
		this._description = description;
		this._creationDate = creationDate;
	}

	public String getTitle() {
		return _title;
	}

	public String getDescription() {
		return _description;
	}

	public String getCreationDate() {
		return _creationDate;
	}

	public long getUserId() {
		return _userId;
	}

	@Override
	public String toString() {
		return "FavoriteDocumentDTO {" +
				   "userId=" + _userId +
				   ", title='" + _title + '\'' +
				   ", description='" + _description + '\'' +
				   ", creationDate='" + _creationDate + '\'' +
			   '}';
	}

	private final long _userId;
	private final String _title;
	private final String _description;
	private final String _creationDate;
}
