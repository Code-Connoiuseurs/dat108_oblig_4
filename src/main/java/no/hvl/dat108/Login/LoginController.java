package no.hvl.dat108.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import no.hvl.dat108.Deltager.Deltager;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    PassordService passordService;

    /*
     * GET /login er forespørselen for å hente login-skjema.
     */
    @GetMapping("/login")
    public String hentLoginSkjema() {
        return "loginView";
    }

    /*
     * POST /login er forespørselen for å logge inn.
     */
    @PostMapping("/login")
    public String provAaLoggeInn(
        @Valid LoginForm loginForm, BindingResult bindingResult,
        HttpServletRequest request,
        Model model,
        Response response
    ) {
        //Hvis ugyldig, gå til login
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .toList();
            model.addAttribute("errors", errors);
            response.setStatus(400);
            return "loginView";
        }

        Optional<Deltager> optionalDeltager = loginService.finnDeltager(loginForm.getMobil());

        if (optionalDeltager.isEmpty()) {
            model.addAttribute("errors", List.of("Mobil eller passord er feil"));
            response.setStatus(400);
            return "loginView";
        }

        Deltager deltager = optionalDeltager.get();

        if (!passordService.erKorrektPassord(
            loginForm.getPassord(), deltager.getPassord().getSalt(), deltager.getPassord().getHash()
        )) {
            model.addAttribute("errors", List.of("Mobil eller passord er feil"));
            response.setStatus(400);
            return "loginView";
        }

        //Innlogging
        loginService.loggInnDeltager(request, deltager);

        return "redirect:/deltagerliste";
    }

    /*
     * POST /logout er forespørselen for å logge ut.
     */
    @PostMapping("/logout")
    public String loggUt(HttpSession session, RedirectAttributes ra) {

        loginService.loggUtBruker(session);

        ra.addFlashAttribute("redirectMessage", "Du er nå logget ut");
        return "redirect:login";
    }

}
