package meguster.mvc;

import meguster.data.entity.Art;
import meguster.service.api.ArtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {

	@Autowired
	ArtService artService;
	
	@RequestMapping("/question")
	public String getQuestion(Model model) {
		
		System.out.println("getQuestion");
		
		Art art = artService.getRandomArtForLoggedUser();
		
		model.addAttribute("art", art);
			
		return "question";
	}
}
