package meguster.service.impl;

import meguster.data.entity.MegusterUser;
import meguster.data.repository.MegusterUserRepository;
import meguster.service.api.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	MegusterUserRepository userRepository;

	@Override
	public MegusterUser findUser(String name) {
		return userRepository.findFirstByName(name);
	}

	@Override
	public MegusterUser saveNewUser(String name) {
		MegusterUser user = new MegusterUser();
		user.setName(name);
		return userRepository.save(user);
	}
	
}
