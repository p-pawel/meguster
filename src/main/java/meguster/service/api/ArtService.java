package meguster.service.api;

import meguster.data.entity.Art;

public interface ArtService {

	Art getRandomArtForLoggedUser(String username);

}
