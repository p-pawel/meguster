package meguster.mvc;

import meguster.data.entity.Art;
import meguster.service.api.ArtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@Autowired
	ArtService artService;

	@RequestMapping("/")
	public String index() {

		Art art = artService.getRandomArtForLoggedUser();

		// model.addAttribute("art", art);

		return "question";
	}

}