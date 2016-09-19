package ra.jademy.domain;

import java.util.List;

import ra.jademy.persistance.MediaDAO;

public class MediaService {

	public List<? extends Media> getAllMedia(ProductType productType) {
		return MediaDAO.getInstance().getAllMedia(productType);	
	}

}
