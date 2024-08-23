package Fragnito.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Libro extends Leggibile {
    @Column(nullable = false)
    private String autore;

    @Column(nullable = false)
    private String genere;

    public Libro(String titolo, Integer anno, Integer pagine, String autore, String genere) {
        super(titolo, anno, pagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                "} " + super.toString();
    }
}
