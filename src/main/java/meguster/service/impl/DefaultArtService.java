package meguster.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import meguster.data.entity.Answer;
import meguster.data.entity.Art;
import meguster.data.entity.MegusterUser;
import meguster.data.repository.AnswerRepository;
import meguster.data.repository.ArtRepository;
import meguster.service.api.ArtService;
import meguster.service.api.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultArtService implements ArtService {

	@Autowired
	ArtRepository artRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	UserService userService;
	
	@Override
	public Art getRandomArtForLoggedUser(String username) {

		MegusterUser user = userService.findUser(username);
		
		Set<Long> ids = getPreviousUserArt(user);

		List<Art> list;
		if (ids.size() > 0) {
			list = artRepository.getRandomArtNotInIds(ids);
		} else {
			list = artRepository.getRandomArt();
		}

		if (list == null || list.size() == 0) {
			return null;
		}
		int count = list.size();

		// long count = artRepository.count();
		//
		int number = (int) (Math.random() * count);
		return list.get(number);

		//
		// final PageRequest pageRequest = new PageRequest(number, 1,
		// Direction.DESC, "id");
		//
		// Page<Art> artsPage = artRepository.findAll(pageRequest);
		//
		// if (artsPage.getContent().size()>0) {
		// return artsPage.getContent().get(0);
		// }
		//
		// return null;
	}

	private Set<Long> getPreviousUserArt(MegusterUser user) {

		Set<Long> ids = new HashSet<>();

		if (user != null) {
			List<Answer> answers = answerRepository.findByUser(user);

			for (Answer answer : answers) {
				ids.add(answer.getArt().getId());
			}
		}
		return ids;
	}

}
