package com.news;


//Entity Articles

public class Article {

	public String content,date,image,title,authors,website;

	
	public Article(String content, String date, String image, String title,
			String authors, String website) {
		super();
		this.content = content;
		this.date = date;
		this.image = image;
		this.title = title;
		this.authors = authors;
		this.website = website;
	}

	public Article() {
		super();
		
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	
	
}
