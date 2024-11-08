package no.hvl.dat108.Paamelding;

import no.hvl.dat108.Deltager.Deltager;
import no.hvl.dat108.Deltager.DeltagerRepo;
import no.hvl.dat108.Deltager.Passord;
import no.hvl.dat108.Login.PassordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;


@Service
public class PaameldingService {

    @Autowired
    PassordService passordService;
    @Autowired
    DeltagerRepo deltagerRepo;

    public void validerUnikMobil(BindingResult bindingResult, String mobil) {
        if (deltagerRepo.existsById(mobil)) {
            bindingResult.addError(new FieldError("Deltager", "mobil", "Mobilnummer allerede registrert"));
        }
    }

    public void validerLiktPassord(BindingResult bindingResult, String passord, String passord_re) {
        if (!passord.equals(passord_re)) {
            bindingResult.addError(new FieldError("Deltager", "passord", "Passord feltene må være like"));
        }
    }

    public Deltager registrerDeltager(PaameldingForm paameldingForm) {
        // Vi oppretter en salt og hash til deltageren
        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(paameldingForm.getPassord(), salt);

        Deltager nyDeltager = new Deltager(
                paameldingForm.getMobil(),
                new Passord(hash, salt),
                paameldingForm.getFornavn(),
                paameldingForm.getEtternavn(),
                paameldingForm.getKjonn()
        );

        // Vi lagrer den nye deltageren i databasen
        deltagerRepo.save(nyDeltager);
        return nyDeltager;
    }

    public List<Deltager> hentRegistrerteDeltagere() {
        return deltagerRepo.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "fornavn").and(Sort.by(Sort.DEFAULT_DIRECTION, "etternavn")));
    }
}
