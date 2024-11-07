package no.hvl.dat108;

import jakarta.persistence.Embeddable;

@Embeddable
public class Passord {
    private String hash;
    private String salt;

    public Passord() {
    }

    public Passord(String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
