package it.formazione.liferay.kafka.integration.api.model;

public class News {

	public static News of(String newsId, String title, String body) {
		return new News(newsId, title, body);
	}

	@Override
	public String toString() {
		return "News {" +
			   "newsId='" + newsId + '\'' +
			   ", title='" + title + '\'' +
			   ", body='" + body + '\'' +
			   '}';
	}

	public News(){};

	private News(String newsId, String title, String body) {
		this.newsId = newsId;
		this.title = title;
		this.body = body;
	}

	public String getNewsId() {
		return newsId;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	private String newsId;
	private String title;
	private String body;
}
