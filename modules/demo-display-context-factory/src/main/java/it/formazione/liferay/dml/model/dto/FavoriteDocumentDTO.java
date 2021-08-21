package it.formazione.liferay.dml.model.dto;

/**
 * @author Virgilio Alessandro 20/ago/2021
 **/
public class FavoriteDocumentDTO {

	public static FavoriteDocumentDTO of(
		long userId, String userName,
		String title, String description, String creationDate,
		String downloadURL) {

		return new FavoriteDocumentDTO(
			userId, userName, title, description, creationDate, downloadURL);
	}

	private FavoriteDocumentDTO(
		long userId, String userName, String title, String description,
		String creationDate, String downloadURL) {

		this._userId = userId;
		this._userName = userName;
		this._title = title;
		this._description = description;
		this._creationDate = creationDate;
		this._downloadURL = downloadURL;
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

	public String getUserName() {
		return _userName;
	}

	public String getDownloadURL() {
		return _downloadURL;
	}

	@Override
	public String toString() {
		return "FavoriteDocumentDTO{" +
			   "_userId=" + _userId +
			   ", _userName='" + _userName + '\'' +
			   ", _downloadURL='" + _downloadURL + '\'' +
			   ", _title='" + _title + '\'' +
			   ", _description='" + _description + '\'' +
			   ", _creationDate='" + _creationDate + '\'' +
			   '}';
	}

	private final long _userId;
	private final String _userName;
	private final String _downloadURL;
	private final String _title;
	private final String _description;
	private final String _creationDate;
}
