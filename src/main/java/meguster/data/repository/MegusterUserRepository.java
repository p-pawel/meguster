package meguster.data.repository;

import meguster.data.entity.MegusterUser;

import org.springframework.data.repository.CrudRepository;

public interface MegusterUserRepository extends CrudRepository<MegusterUser, Long> {

	MegusterUser findFirstByName(String name);

}
