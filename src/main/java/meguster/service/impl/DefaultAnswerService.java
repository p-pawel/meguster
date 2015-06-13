package meguster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meguster.data.entity.Answer;
import meguster.data.entity.Art;
import meguster.data.entity.MegusterUser;
import meguster.data.repository.AnswerRepository;
import meguster.data.repository.ArtRepository;
import meguster.data.repository.MegusterUserRepository;
import meguster.service.api.AnswerService;
import meguster.service.api.ArtService;
import meguster.service.api.UserService;

@Service
public class DefaultAnswerService implements AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	UserService userService;
	@Autowired
	ArtService artService;
	@Autowired
	MegusterUserRepository megusterUserRepository;
	@Autowired
	ArtRepository artRepository;
	
	@Override
	public void saveAnswer(String userName, Long artId, int answerValue) {
		MegusterUser megusterUser = userService.findUser(userName);
		if(megusterUser == null){
			megusterUser =  userService.saveNewUser(userName);
		}
		Art art = artRepository.findOne(artId);
		
		Answer answer = new Answer();
		answer.setUser(megusterUser);
		answer.setArt(art);
		answer.setValue(answerValue);
		
		answerRepository.save(answer);
	}

}
