package it.formazione.liferay.kafka.producer.model;

public class News {

	public static News of(String newsId, String title, String body) {
		return new News(newsId, title, body);
	}

	public News(){};

	private News(String newsId, String title, String body) {
		_newsId = newsId;
		_title = title;
		_body = body;
	}

	public String getNewsId() {
		return _newsId;
	}

	public String getTitle() {
		return _title;
	}

	public String getBody() {
		return _body;
	}

	public void setNewsId(String newsId) {
		_newsId = newsId;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setBody(String body) {
		_body = body;
	}

	private String _newsId;
	private String _title;
	private String _body;
}
