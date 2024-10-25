package no.hvl.dat108;
import jakarta.validation.constraints.*;

public class Deltager {
	// Regex: Streng av tall med lengde 8
    @NotEmpty(message = "Mobil kan ikke være tomt")
	@Pattern(regexp="(^$|[0-9]{8})", message = "Mobil må være eksakt 8 siffer")
	private String mobil;

    @NotEmpty(message = "Passord kan ikke være tomt")
	@Size(min = 5, message = "Passord må være lengre enn 5")
    private String passord;

    @NotEmpty(message = "Fornavn kan ikke være tomt")
	@Size(min = 2, max = 20, message = "Fornavn må være mellom 2-20")
	@Pattern(regexp = "^[a-zæøåA-ZÆØÅ -]{2,20}$")
    private String fornavn;

	// Regex: Streng med enten 'mann' ekker 'kvinne'
    @NotEmpty(message = "Etternavn kan ikke være tomt")
	@Size(min = 2, max = 20, message = "Etternavn må være mellom 2-20")
	@Pattern(regexp = "^[a-zæøåA-ZÆØÅ -]{2,20}$")
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

	public Deltager(String mobil, String passord, String fornavn, String etternavn, String kjonn) {
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
