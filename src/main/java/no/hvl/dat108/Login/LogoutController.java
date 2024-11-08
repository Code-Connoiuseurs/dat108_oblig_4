package no.hvl.dat108.Login;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class LogoutController {
    /*
     * POST /logout er forespørselen for å logge ut.
     */
    @PostMapping
    public String loggUt(HttpSession session, RedirectAttributes ra) {

        LoginService.loggUtBruker(session);

        ra.addFlashAttribute("redirectMessage", "Du er nå logget ut");
        return "redirect:login";
    }
}
