package meguster.data.repository;

import java.util.List;
import java.util.Set;

import meguster.data.entity.Art;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ArtRepository extends PagingAndSortingRepository<Art, Long> {

	@Query("select a from Art a where a.id not in (:art_ids)")
	List<Art> getRandomArtNotInIds(@Param("art_ids") Set<Long> ids);

	@Query("select a from Art a")
	List<Art> getRandomArt();

}
