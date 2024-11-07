package no.hvl.dat108;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

@Autowired
private PassordService passordService;

@Autowired
private DeltagerRepo deltagerRepo;

@GetMapping("/login")
public String loginSide() {
    return "login";
}

@PostMapping("/login")
public String login(
    @RequestParam String mobil,
    @RequestParam String passord,
    Model model,
    HttpSession session) {

        Deltager deltager = deltagerRepo.finnMedMobil(mobil);
        if(deltager != null) {
            boolean validBruker = passordService.erKorrektPassord(
                passord, deltager.getPassord().getSalt(), deltager.getPassord().getHash());

        if(validBruker) {
            session.setAttribute("loggedInUser", mobil);
            return "redirect:/deltagerliste";
            }
        }
        model.addAttribute("loginError", "Ugyldig mobilnummer eller passord");
        return "login";
    }

    @GetMapping("/logout") 
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}