package meguster.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import meguster.data.entity.Art;
import meguster.data.repository.ArtRepository;
import meguster.service.api.ArtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultArtService implements ArtService {

	@Autowired
	ArtRepository artRepository;
	
	@Override
	public Art getRandomArtForLoggedUser() {

		Set<Long> ids = new HashSet<>();
		
		List<Art> list;
		if (ids.size()>0) {
			list = artRepository.getRandomArtNotInIds(ids);
		} else {
			list = artRepository.getRandomArt();
		}
		
		if (list==null || list.size()==0) {
			return null;
		}
		int count = list.size();
		
//		long count = artRepository.count();
//		
		int number = (int) (Math.random()*count);
		return list.get(number);
		
//		
//		final PageRequest pageRequest = new PageRequest(number, 1, Direction.DESC, "id");
//
//		Page<Art> artsPage = artRepository.findAll(pageRequest);
//		
//		if (artsPage.getContent().size()>0) {
//			return artsPage.getContent().get(0);
//		}
//				
//		return null;
	}

}
