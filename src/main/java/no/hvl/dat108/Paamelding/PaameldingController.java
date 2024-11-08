package no.hvl.dat108.Paamelding;

import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.Deltager.Deltager;
import no.hvl.dat108.Login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class PaameldingController {

	@Autowired
	private PaameldingSvervice paameldingSvervice;
    @Autowired
    private LoginService loginService;

	@GetMapping("/")
	public String getRoot() {
		return "redirect:/paamelding";
	}
	
	@GetMapping("/paamelding")
	public String getPaamelding() {
		return "paameldingView";
	}
	
	@PostMapping("/paamelding")
	public String postPaamelding(
			Model model,
			@Valid PaameldingForm paameldingForm, BindingResult bindingResult,
			@RequestParam String passord_re,
			RedirectAttributes redirectAttributes,
			HttpServletResponse response
	) {
		// Vi gjennomfører programmatisk validering
		paameldingSvervice.validerUnikMobil(bindingResult, paameldingForm.getMobil());
		paameldingSvervice.validerLiktPassord(bindingResult, paameldingForm.getPassord(), passord_re);
		if (bindingResult.hasErrors()) {
			// Det har oppstått en valideringsfeil, vi rendrer siden på nytt og viser alle feilmeldinger
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();
			model.addAttribute("errors", errors);
			response.setStatus(400);
			return "paameldingView";
		}

		// All validering er OK, vi registrerer deltageren og redirecter til kvittering
		Deltager nyDeltager = paameldingSvervice.registrerDeltager(paameldingForm);
		redirectAttributes.addFlashAttribute("deltager", nyDeltager);
		return "redirect:/paameldt";
	}
	
	@GetMapping("/paameldt")
	public String getPaameldt() {
		return "paameldtView";
	}
	
	@GetMapping("/deltagerliste")
	public String getDeltagerListe(Model model, HttpSession session, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		if (!loginService.erBrukerInnlogget(session)) {
			redirectAttributes.addFlashAttribute("errors", List.of("Du må logge inn for å se deltagerliste"));
			return "redirect:/login";
		}

		List<Deltager> regDeltagere = paameldingSvervice.hentRegistrerteDeltagere();
		model.addAttribute("deltagere", regDeltagere);
		return "deltagerListeView";
	}
}
