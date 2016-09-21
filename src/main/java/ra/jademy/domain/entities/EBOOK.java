package ra.jademy.domain.entities;

import ra.jademy.domain.entities.EBOOK.Builder;

public class EBOOK extends Media{
	private String author;

	public EBOOK(String title, double price, String code, Genre genre, String autor) {
		super(title, price, code, genre);
		this.author = author;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return author;
	}
	
	 public static class Builder {
		 String title;
		 double price; 
		 String code;
		 Genre genre;
		 String author;
		 
		 public Builder title(String title) {
			 this. title= title;
			 return this;
		 }
		 public Builder author(String author) {
			 this.author = author;
			 return this;
		 }
		 public Builder price(double price) {
			 this. price= price;
			 return this;
		 }
		 public Builder code(String code) {
			 this. code= code;
			 return this;
		 }
		 public Builder genre(Genre genre) {
			 this. genre= genre;
			 return this;
		 }
		 
		 public EBOOK build() {
			 return new EBOOK(title, price, code, genre, author);
		 }
		}
	 }
	


