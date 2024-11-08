package no.hvl.dat108.Login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginForm {
    // Regex: Streng av tall med lengde 8
    @NotEmpty(message = "Mobil kan ikke være tomt")
    @Pattern(regexp="(^$|[0-9]{8})", message = "Mobil må være eksakt 8 siffer")
    private String mobil;

    @NotEmpty(message = "Passord kan ikke være tomt")
    @Size(min = 8, message = "Passord må være minst 8 tegn")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[\\W_])(?!.*\\s).{8,}$",
            message = "Passord må inneholde minst ett tall, " +
                    "minst én liten bokstav, minst én stor bokstav og minst ett spesialtegn. " +
                    "Mellomrom ikke tillatt.")
    private String passord;

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public LoginForm(String mobil, String passord) {
        this.mobil = mobil;
        this.passord = passord;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "mobil='" + mobil + '\'' +
                ", passord='" + passord + '\'' +
                '}';
    }
}
