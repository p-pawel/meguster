package meguster.data.repository;

import java.util.Set;

import meguster.data.entity.Art;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ArtRepository extends PagingAndSortingRepository<Art, Long> {

	@Query("select a from Art a where a.id not in (:art_ids) ")
	Page<Art> getArtsNotInIds(@Param("art_ids") Set<Long> ids, Pageable pageRequest);

	@Query("select count(*) from Art a where a.id not in (:art_ids) ")
	Long countArtsNotInIds(@Param("art_ids") Set<Long> ids);

}
