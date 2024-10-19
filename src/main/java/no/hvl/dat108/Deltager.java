package no.hvl.dat108;
import jakarta.validation.constraints.*;

public class Deltager {
	public enum Kjonn {
		Mann,
		Kvinne
	}
	
    @NotNull(message = "Mobil kan ikke være tomt") @Pattern(regexp="(^$|[0-9]{8})", message = "Mobil må være eksakt 8 siffer")
	private String mobil;
    @NotNull(message = "Passord kan ikke være tomt") @Size(min = 5, message = "Passord må være lengre enn 5")
    private String passord;
    @NotNull(message = "Fornavn kan ikke være tomt") @Size(min = 2, max = 20, message = "Fornavn må være mellom 2-20")
    private String fornavn;
    @NotNull(message = "Etternavn kan ikke være tomt") @Size(min = 2, max = 20, message = "Etternavn må være mellom 2-20")
    private String etternavn;
    @NotNull(message = "Kjønn kan ikke være tomt")
    private Kjonn kjonn;
    
	public Kjonn getKjonn() {
		return kjonn;
	}

	public void setKjonn(Kjonn kjonn) {
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

	@Override
	public String toString() {
		return "Deltager [mobil=" + mobil + ", passord=" + passord + ", fornavn=" + fornavn + ", etternavn=" + etternavn
				+ ", kjonn=" + kjonn + "]";
	}

	public Deltager(String mobil, String passord, String fornavn, String etternavn, Kjonn kjonn) {
		this.mobil = mobil;
		this.passord = passord;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.kjonn = kjonn;
	}
    
}
