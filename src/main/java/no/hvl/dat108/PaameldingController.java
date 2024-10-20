package no.hvl.dat108;	

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class PaameldingController {
	private List<Deltager> regDeltagere = new ArrayList<Deltager>();

	@GetMapping("/")
	public String getRoot() {
		return "redirect:/paamelding";
	}
	
	@GetMapping("/paamelding")
	public String getPaamelding(Model model) {
		return "paameldingView";
	}
	
	@PostMapping("/paamelding")
	public String postPaamelding(Model model, @Valid @ModelAttribute("deltager") Deltager deltager, @RequestParam(required = true, name = "passord_re") String passord_re, BindingResult bindRes, RedirectAttributes redirectAttributes) {
		if (regDeltagere.stream().anyMatch(d -> d.getMobil().equals(deltager.getMobil()))) {
			bindRes.addError(new FieldError("Deltager", "mobil", "Mobilnummer allerede registrert"));			
		}
		
		if (!deltager.getPassord().equals(passord_re)) {
			bindRes.addError(new FieldError("Deltager", "passord", "Passord feltene må være like"));
		}
		
		if (bindRes.hasErrors()) {
			List<String> errors = bindRes.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();
			model.addAttribute("errors", errors);
			return "paameldingView";
		}
		
		// All validering er OK, vi registrerer deltageren og redirecter til kvittering
		regDeltagere.add(deltager);
		redirectAttributes.addFlashAttribute("deltager", deltager);
		return "redirect:/paameldt";
	}
	
	@GetMapping("/paameldt")
	public String getPaameldt( Model model) {
		return "paameldtView";
	}
	
	@GetMapping("/deltagerliste")
	public String getDeltagerListe(Model model) {
		model.addAttribute("deltagere", regDeltagere);
		return "deltagerListeView";
	}
}
