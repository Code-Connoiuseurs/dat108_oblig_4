package no.hvl.dat108.Paamelding;
import jakarta.validation.constraints.*;

public class PaameldingForm {
	// Regex: Streng av tall med lengde 8
    @NotEmpty(message = "Mobil kan ikke være tomt")
	@Pattern(regexp="(^$|[0-9]{8})", message = "Mobil må være eksakt 8 siffer")
	private String mobil;

    @NotEmpty(message = "Passord kan ikke være tomt")
	@Size(min = 8, message = "Passord må være minst 8 tegn")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[\\W_])(?!.*\\s).{8,}$",
			message = "Passord må være minst 8 tegn langt og inneholde minst ett tall, " +
					  "minst én liten bokstav, minst én stor bokstav og minst ett spesialtegn. " +
					  "Mellomrom ikke tillatt.")
    private String passord;

    @NotEmpty(message = "Fornavn kan ikke være tomt")
	@Size(min = 2, max = 20, message = "Fornavn må være mellom 2-20 bokstaver")
	@Pattern(regexp = "^[A-ZÆØÅ][a-zæøåA-ZÆØÅ -]{1,19}$",
			message = "Fornavn må starte med stor forbokstav" +
					  "og kan inneholde mellomrom og bindestrek (-)")
    private String fornavn;

    @NotEmpty(message = "Etternavn kan ikke være tomt")
	@Size(min = 2, max = 20, message = "Etternavn må være mellom 2-20 bokstaver")
	@Pattern(regexp = "^[A-ZÆØÅ][a-zæøåA-ZÆØÅ -]{1,19}$",
			message = "Etternavn må starte med stor forbokstav" +
					"og kan inneholde mellomrom og bindestrek (-)")
    private String etternavn;

    // Regex: Streng med verdi enten "Mann" eller "Kvinne"
    @NotEmpty(message = "Kjønn kan ikke være tomt")
	@Pattern(regexp = "^mann$|^kvinne$", message = "Kjønn må være Mann eller Kvinne")
    private String kjonn;
    
	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}

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

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public PaameldingForm(String mobil, String passord, String fornavn, String etternavn, String kjonn) {
		this.mobil = mobil;
		this.passord = passord;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.kjonn = kjonn;
	}

	@Override
	public String toString() {
		return "Deltager [mobil=" + mobil + ", passord=" + passord + ", fornavn=" + fornavn + ", etternavn=" + etternavn
				+ ", kjonn=" + kjonn + "]";
	}
}
