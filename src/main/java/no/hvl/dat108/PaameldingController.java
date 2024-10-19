package no.hvl.dat108;	

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.hvl.dat108.Deltager.Kjonn;

@Controller
public class PaameldingController {

	@GetMapping("/paamelding")
	public String getPaamelding(Model model) {
		return "paameldingView";
	}
	
	@PostMapping("/paamelding")
	public String postPaamelding(Model model, RedirectAttributes redirectAttributes, String fornavn, String etternavn, String mobil, String passord, String passord_re, String kjonn, BindingResult bindRes) {
		if (bindRes.hasErrors()) {
			List<String> errors = bindRes.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();
			model.addAttribute("errors", errors);
			return "paameldingView";
		}
		
		Deltager newDeltager = new Deltager(mobil, passord, fornavn, etternavn, kjonn);
		redirectAttributes.addAttribute("deltager", newDeltager);
		return "redirect:/paameldt";
	}
	
	@GetMapping("/paameldt")
	public String getPaameldt(Model model) {
		return "deltagerliste";
	}
	
	@GetMapping("/deltagerliste")
	public String getDeltagerListe() {
		return "deltagerliste";
	}
}
