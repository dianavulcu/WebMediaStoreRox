package ra.jademy.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import ra.jademy.domain.CD;
import ra.jademy.domain.Genre;
import ra.jademy.domain.Media;
import ra.jademy.domain.ProductType;
import ra.jademy.domain.User;

public class MediaDAO {
	private Properties importFile;
	private static MediaDAO soleInstance = new MediaDAO();

	private MediaDAO() {
		importFile = new Properties() {
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

	public static MediaDAO getInstance() {
		return soleInstance;
	}

	public List<Media> getAllMedia(ProductType productType) {
		List<Media> aList = new ArrayList<>();
		int i = 0;
		while (true) {
			i++;
			String dbCode = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].code");
			if (dbCode == null) {
				break;
			}
			String dbTitle = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].title");
			Double dbPrice = Double
					.valueOf(importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].price"));
			Genre dbGenre = Genre
					.valueOf(importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].genre"));
			String dbArtist = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].artist");
			CD cd = new CD.Builder().title(dbTitle).artist(dbArtist).price(dbPrice).code(dbCode).genre(dbGenre).build();
			aList.add(cd);
		}
		return aList;
	}
}
