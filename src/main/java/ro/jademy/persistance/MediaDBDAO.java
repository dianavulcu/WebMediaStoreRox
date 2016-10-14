package ro.jademy.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import ro.jademy.domain.entities.AgeCategory;
import ro.jademy.domain.entities.CD;
import ro.jademy.domain.entities.DVD;
import ro.jademy.domain.entities.EBOOK;
import ro.jademy.domain.entities.MediaGenre;
import ro.jademy.domain.entities.PriceCategory;
import ro.jademy.domain.entities.Media;
import ro.jademy.domain.entities.ProductType;

import java.util.Properties;
import java.util.TreeSet;

public class MediaDBDAO {
	private Properties importFile;
	private static MediaDBDAO soleInstance = new MediaDBDAO();

	private MediaDBDAO() {
		importFile = new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public synchronized Enumeration<Object> keys() {
				return Collections.enumeration(new TreeSet<Object>(keySet()));
			}
		};

		try {
			FileInputStream stream = new FileInputStream("media-store-database.properties");
			importFile.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open user.properties", e);
		}
	}

	public static MediaDBDAO getInstance() {
		return soleInstance;
	}
	
	public List<Media> getMediaByQuery(PreparedStatement statement) throws SQLException {
		ResultSet resultSet = statement.executeQuery();
		List<Media> aList = new ArrayList<>();
		while (resultSet.next()) {
			Media media = null;
			String title = resultSet.getString("title");
			String code = resultSet.getString("code");
			MediaGenre genre = MediaGenre.valueOf(resultSet.getString("genre"));
			AgeCategory ageCategory = AgeCategory.valueOf(resultSet.getString("age_category"));
			PriceCategory priceCategory = PriceCategory.valueOf(resultSet.getString("price_category"));
			Double price = resultSet.getDouble("price");
			ProductType productType = ProductType.valueOf(resultSet.getString("product_type"));
			switch (productType) {
			case CD:
				media = new CD();
				String artist = resultSet.getString("artist");
				media = new CD.Builder().title(title).artist(artist).price(price).code(code).genre(genre).build();
				break;
			case DVD:
				media = new DVD();
				String productionLabel = resultSet.getString("production_label");
				String directors = resultSet.getString("directors");
				media = new DVD.Builder().title(title).productionLabel(productionLabel).directors(directors)
						.price(price).code(code).genre(genre).build();
				break;
			case EBOOK:
				media = new EBOOK();
				String author = resultSet.getString("author");
				media = new EBOOK.Builder().title(title).author(author).price(price).code(code).genre(genre)
						.build();
				break;
			}
			media.setAgeCategory(ageCategory);
			media.setPriceCategory(priceCategory);
			aList.add(media);
		}
		return aList;
	}
	
	public List<Media> getMediaByProductType(ProductType productType) {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM  MEDIA WHERE PRODUCT_TYPE = ?");
			statement.setString(1, productType.name());
			return getMediaByQuery(statement);
			
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to db", e);
		}
	}

	public Media getProductbyCode(ProductType productType, String productCode) {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM MEDIA WHERE CODE = ?");
			statement.setString(1, productCode);
			
			List<Media> media = getMediaByQuery(statement);
			if(media.size() != 1) {
				throw new IllegalStateException("Multiple media with same code: " +productCode);
			}
			
			return media.get(0);
			
			
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to db", e);
		}

	}
}
