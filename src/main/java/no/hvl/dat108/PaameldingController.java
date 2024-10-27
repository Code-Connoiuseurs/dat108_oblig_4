package no.hvl.dat108;	

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class PaameldingController {
	List<Deltager> regDeltagere = new ArrayList<>(
			List.of(new Deltager("12345678", "Comp!ex9", "Helmut", "Habbedask", "mann"),
					new Deltager("11111111", "Domp_ex0", "Harry", "Habbo", "mann"),
					new Deltager("22222222", "Com!lex1", "Kneisert", "Pukkelmage", "kvinne"),
					new Deltager("33333333", "Co!plex2", "Fettleif", "Furefjes", "mann"),
					new Deltager("44444444", "C!mplex3", "Usse", "Ladjus", "mann"),
					new Deltager("55555555", "C?mp!ex5", "Anon", "Ymous", "kvinne"),
					new Deltager("66666666", "C@mp!ex6", "Jogust Auhan", "Stangevik-Haaland", "mann"),
					new Deltager("77777777", "co!p!ex7", "Malo", "Karpatan", "kvinne"),
					new Deltager("88888888", "Comp!ex8", "Kalo", "Ri", "kvinne")));

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
		@Valid @ModelAttribute("deltager") Deltager deltager, BindingResult bindRes,
		@RequestParam(required = true, name = "passord_re") String passord_re,
		RedirectAttributes redirectAttributes
	) {
		if (regDeltagere.stream().anyMatch(d -> d.getMobil().equals(deltager.getMobil()))) {
			bindRes.addError(new FieldError("Deltager", "mobil", "Mobilnummer allerede registrert"));			
		}
		if (!deltager.getPassord().equals(passord_re)) {
			bindRes.addError(new FieldError("Deltager", "passord", "Passord feltene må være like"));
		}
		if (bindRes.hasErrors()) {
			// Det har oppstått en validerings error, vi rendrer siden på nytt og viser alle errors
			List<String> errors = bindRes.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();
			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("deltager", deltager);
			return "redirect:/paamelding";
		}
		
		// All validering er OK, vi registrerer deltageren og redirecter til kvittering
		regDeltagere.add(deltager);
		redirectAttributes.addFlashAttribute("deltager", deltager);
		return "redirect:/paameldt";
	}
	
	@GetMapping("/paameldt")
	public String getPaameldt() {
		return "paameldtView";
	}
	
	@GetMapping("/deltagerliste")
	public String getDeltagerListe(Model model) {
		regDeltagere.sort(Comparator.comparing(Deltager::getFornavn).thenComparing(Deltager::getEtternavn));
		model.addAttribute("deltagere", regDeltagere);
		return "deltagerListeView";
	}
}
