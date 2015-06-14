package meguster.mvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import meguster.data.entity.Art;
import meguster.service.api.AnswerService;
import meguster.service.api.ArtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

	private final static String USER_NAME_COOKIE = "username";
	
	@Autowired
	ArtService artService;

	@Autowired
	AnswerService answerService;

	@RequestMapping(value = "/question")
	public String getQuestion(
			Model model,
			@RequestParam(required = false, value = "username") String forceUserName,
			@CookieValue(defaultValue = "ArtOfCode", value = USER_NAME_COOKIE) String cookieUserName,
			HttpServletResponse httpServletResponse) {

		String username = forceUserName != null ? forceUserName
				: cookieUserName;

		saveUserNameCookie(httpServletResponse, username);

		Art art = artService.getRandomArtForLoggedUser(username);

		if (art == null) {
			return "redirect:http://fgo4lw.axshare.com/listing_osob.html";
		} else {
			model.addAttribute("art", art);
			return "question";
		}
	}

	private void saveUserNameCookie(HttpServletResponse httpServletResponse,
			String username) {
		Cookie cookie = new Cookie(USER_NAME_COOKIE, username);
		httpServletResponse.addCookie(cookie);
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public String nextAnswer(
			@RequestParam(required = false, value = "answer") Integer answer,
			@RequestParam(required = false, value = "art_id") Long artId,
			@CookieValue(defaultValue = "ArtOfCode", value = USER_NAME_COOKIE) String cookieUserName) {

		answerService.saveAnswer(cookieUserName, artId, answer);

		return "redirect:question";
	}

}
