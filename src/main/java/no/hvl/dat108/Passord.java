package no.hvl.dat108;

import jakarta.persistence.Embeddable;

@Embeddable
public class Passord {
    private String hash;
    private String salt;
}
