package meguster.mvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import meguster.data.entity.Art;
import meguster.service.api.AnswerService;
import meguster.service.api.ArtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuestionController {

	@Autowired
	ArtService artService;

	@Autowired
	AnswerService answerService;

	@RequestMapping(value = "/question")
	public String getQuestion(Model model) {

		System.out.println("getQuestion");

		Art art = artService.getRandomArtForLoggedUser();

		model.addAttribute("art", art);

		return "question";
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public String nextAnswer(Model model, HttpServletRequest req) {
		System.out.println("nextAnswer");
		String username = "a";
		String answer = req.getParameter("answer");
		String art_id = req.getParameter("art_id");

		for (Cookie c : req.getCookies()) {
			if (c.getName().equals("username")) {
				username = c.getValue();
				break;
			}
		}

		answerService.saveAnswer(username, Long.valueOf(art_id), Integer.parseInt(answer));

		Art art = artService.getRandomArtForLoggedUser();
		model.addAttribute("art", art);
		return "question";
	}
}
