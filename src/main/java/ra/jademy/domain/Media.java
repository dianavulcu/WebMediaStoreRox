package ra.jademy.domain;

public abstract class Media {
	private String title;
	private double price;
	private String code;
	private Genre genre;

	public Media(String title, double price, String code, Genre genre) {
		this.title = title;
		this.price = price;
		this.code = code;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public String getCode() {
		return code;
	}

	public Genre getGenre() {
		return genre;
	}

	public abstract String getDescription();
	

}
