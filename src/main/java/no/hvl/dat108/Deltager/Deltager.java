package no.hvl.dat108.Deltager;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig_4")
public class Deltager {

    @Id
    private String mobil;
    @Embedded
    private Passord passord; // Hash + salt
    private String fornavn;
    private String etternavn;
    private String kjonn;

    public Deltager() {
    }

    public Deltager(String mobil, Passord passord, String fornavn, String etternavn, String kjonn) {
        this.mobil = mobil;
        this.passord = passord;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.kjonn = kjonn;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public Passord getPassord() {
        return passord;
    }

    public void setPassord(Passord passord) {
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

    public String getKjonn() {
        return kjonn;
    }

    public void setKjonn(String kjonn) {
        this.kjonn = kjonn;
    }

}