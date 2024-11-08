package no.hvl.dat108.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.Deltager.Deltager;
import no.hvl.dat108.Deltager.DeltagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private DeltagerRepo deltagerRepo;

    public void loggUtBruker(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    public void loggInnDeltager(HttpServletRequest request, Deltager deltager) {

        //NB!
        loggUtBruker(request.getSession());

        HttpSession sesjon = request.getSession();
        sesjon.setAttribute("innloggetDeltager", deltager);
        sesjon.setMaxInactiveInterval(60 * 60 * 24); //24 timer
    }

    public boolean erBrukerInnlogget(HttpSession session) {
        return session != null && session.getAttribute("innloggetDeltager") != null;
    }

    public Optional<Deltager> finnDeltager(String mobil) {
        return deltagerRepo.findById(mobil);
    }
}
