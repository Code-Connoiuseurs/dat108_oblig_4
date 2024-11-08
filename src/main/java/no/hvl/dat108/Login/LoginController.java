package no.hvl.dat108.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.Deltager.Deltager;
import no.hvl.dat108.Deltager.DeltagerRepo;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	DeltagerRepo deltagerRepo;

	@Autowired
	PassordService passordService;

	/*
	 * GET /login er forespørselen for å hente login-skjema.
	 */
	@GetMapping
	public String hentLoginSkjema() {
		return "loginView";
	}

	/*
	 * POST /login er forespørselen for å logge inn.
	 */
	@PostMapping
	public String provAaLoggeInn(@RequestParam String mobil, @RequestParam String passord, Model model,
			HttpServletRequest request, RedirectAttributes ra, HttpSession session) {

		LoginService.loggUtBruker(request.getSession());

		Deltager deltager = deltagerRepo.findByMobil(mobil);

		if (deltager != null && passordService.erKorrektPassord(passord, deltager.getPassord().getSalt(),
				deltager.getPassord().getHash())) {

			LoginService.loggInnBruker(request, deltager);
//			session.setAttribute("loggedInUser", mobil);

			return "redirect:deltagerliste";
		}
		model.addAttribute("loginError", "Ugyldig mobilnummer eller passord");

		return "loginView";
	}

}
