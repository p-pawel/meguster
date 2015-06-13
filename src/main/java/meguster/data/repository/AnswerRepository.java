package meguster.data.repository;

import java.util.List;

import meguster.data.entity.Answer;
import meguster.data.entity.MegusterUser;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

	List<Answer> findByUser(MegusterUser user);

}
