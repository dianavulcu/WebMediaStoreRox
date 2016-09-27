package ro.jademy.domain.entities;

import ro.jademy.domain.entities.CD.Builder;

public class CD extends Media {
	private String artist;
	
	public CD(String title, double price, String code, MediaGenre genre, String artist) {
		super(title, price, code, genre);
		this.artist = artist;
	}

	public CD() {
		// TODO Auto-generated constructor stub
	}

	public String getArtist() {
		return artist;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return artist;
	}
	
	 public static class Builder {
		 String title;
		 double price; 
		 String code;
		 MediaGenre genre;
		 String artist;
		 
		 public Builder title(String title) {
			 this. title= title;
			 return this;
		 }
		 public Builder artist(String artist) {
			 this. artist= artist;
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
		 public Builder genre(MediaGenre genre) {
			 this. genre= genre;
			 return this;
		 }
		 
		 public CD build() {
			 return new CD(title, price, code, genre, artist);
		 }
	 }

}
