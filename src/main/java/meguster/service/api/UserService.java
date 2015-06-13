package meguster.service.api;

import meguster.data.entity.MegusterUser;

public interface UserService {

	MegusterUser findUser(String name);
	
	MegusterUser saveNewUser(String name);

}
