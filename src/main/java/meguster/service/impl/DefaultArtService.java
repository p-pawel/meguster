package meguster.service.impl;

import meguster.data.entity.Art;
import meguster.data.entity.Art.ArtType;
import meguster.service.api.ArtService;

import org.springframework.stereotype.Service;

@Service
public class DefaultArtService implements ArtService {

	@Override
	public Art getRandomArtForLoggedUser() {

		// TODO mock
		Art art = new Art();
		art.setType(ArtType.IMAGE);
		art.setUrl("http://d26uhratvi024l.cloudfront.net/gsc/FGO4LW/33/73/ef/3373efd17b764de29231be2c9325fac0/images/page_1/u16.jpg?token=fe542caff01fd3fa471c5b27a175f171");
		
		return art;
	}

}
