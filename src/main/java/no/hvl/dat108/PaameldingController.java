package no.hvl.dat108;	

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaameldingController {

	@GetMapping("/paamelding")
	public String getPaamelding(Model model) {
		return "paameldingView";
	}
	
	@PostMapping("/paamelding")
	public String postPaamelding(Model model, RedirectAttributes redirectAttributes, String fornavn, String etternavn, String mobil, String passord, String passord_re, String kjonn) {
		List<String> errors = new ArrayList<String>();
		
		Deltager newDeltager = new Deltager(mobil, passord, fornavn, etternavn, kjonn);
		
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "paameldingView";			
		} else {
			redirectAttributes.addAttribute("deltager", newDeltager);
			return "redirect:/paameldt";
		}
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
