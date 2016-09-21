package ra.jademy.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeSet;

import ra.jademy.domain.entities.CD;
import ra.jademy.domain.entities.DVD;
import ra.jademy.domain.entities.EBOOK;
import ra.jademy.domain.entities.Genre;
import ra.jademy.domain.entities.Media;
import ra.jademy.domain.entities.ProductType;

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
			String dbDirectors = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].directors");
			String dbProductionLabels = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].productionLabels");
			String dbAuthor = importFile.getProperty(productType.name().toLowerCase() + "[" + i + "].author");
			CD cd = new CD.Builder().title(dbTitle).artist(dbArtist).price(dbPrice).code(dbCode).genre(dbGenre).build();
			DVD dvd = new DVD.Builder().title(dbTitle).directors(dbDirectors).productionLabel(dbProductionLabels).price(dbPrice).code(dbCode).genre(dbGenre).build();
			EBOOK ebook = new EBOOK.Builder().title(dbTitle).author(dbAuthor).price(dbPrice).code(dbCode).genre(dbGenre).build();
			aList.add(cd);
		}
		return aList;
	}
	public Media getByCode(String productCode){
		int i=0;
		for (Entry pEntry:importFile.entrySet()){
			i++;
			if (pEntry.getValue().equals(productCode) && ((String)pEntry.getKey()).endsWith(".code")){
				
			}
		}
		return null;
	}
}