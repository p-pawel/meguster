package meguster.service.impl;

import meguster.data.entity.Art;
import meguster.data.repository.ArtRepository;
import meguster.service.api.ArtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class DefaultArtService implements ArtService {

	@Autowired
	ArtRepository artRepository;
	
	@Override
	public Art getRandomArtForLoggedUser() {

		long count = artRepository.count();
		
		int number = (int) (Math.random()*count);
		
		final PageRequest pageRequest = new PageRequest(number, 1, Direction.DESC, "id");

		Page<Art> artsPage = artRepository.findAll(pageRequest);
		
		if (artsPage.getContent().size()>0) {
			return artsPage.getContent().get(0);
		}
				
		return null;
		
//		// TODO mock
//		Art art = new Art();
//		art.setType(ArtType.IMAGE);
//		art.setUrl("http://d26uhratvi024l.cloudfront.net/gsc/FGO4LW/33/73/ef/3373efd17b764de29231be2c9325fac0/images/page_1/u16.jpg?token=fe542caff01fd3fa471c5b27a175f171");
//		
//		return art;
	}

}
