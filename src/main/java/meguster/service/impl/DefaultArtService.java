package meguster.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import meguster.data.entity.Answer;
import meguster.data.entity.Art;
import meguster.data.entity.MegusterUser;
import meguster.data.repository.AnswerRepository;
import meguster.data.repository.ArtRepository;
import meguster.service.api.ArtService;
import meguster.service.api.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

		Set<Long> ids = getPreviousUserArts(user);
		ids.add(0L); // another hack to do not query with empty collection

		long count = artRepository.countArtsNotInIds(ids);
		
		int number = (int) (Math.random() * (count - 1));

		final PageRequest pageRequest = new PageRequest(number, 1, Direction.DESC, "id");

		Page<Art> artsPage = artRepository.getArtsNotInIds(ids, pageRequest);

		return artsPage.getContent().stream().findFirst().orElse(null);
	}

	private Set<Long> getPreviousUserArts(MegusterUser user) {

		Set<Long> ids = new HashSet<>();

		if (user != null) {
			List<Answer> answers = answerRepository.findByUser(user);
			return answers.stream().map(Answer::getArt).map(Art::getId).collect(Collectors.toSet());
		}

		return ids;
	}

}
