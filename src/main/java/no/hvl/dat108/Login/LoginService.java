package no.hvl.dat108.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginService {

    public static void loggUtBruker(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    public static void loggInnBruker(HttpServletRequest request, String username) {

        //NB!
        loggUtBruker(request.getSession());

        HttpSession sesjon = request.getSession();
        sesjon.setAttribute("username", username);
        sesjon.setAttribute("cart", new Cart());
        sesjon.setMaxInactiveInterval(60 * 60 * 24); //sekunder
    }

    public static boolean erBrukerInnlogget(HttpSession session) {
        return session != null && session.getAttribute("username") != null;
    }
}
