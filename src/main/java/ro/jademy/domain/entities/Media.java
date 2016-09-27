package ro.jademy.domain.entities;

public abstract class Media {
	private String title;
	private double price;
	private String code;
	private MediaGenre genre;

	public Media(){}
	public Media(String title, double price, String code, MediaGenre genre) {
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

	public MediaGenre getGenre() {
		return genre;
	}

	public abstract String getDescription();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	

}
