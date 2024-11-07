package no.hvl.dat108;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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


    public Passord getPassord() {
        return passord;
    }
}