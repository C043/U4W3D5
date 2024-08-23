package Fragnito.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Rivista extends Leggibile {
    @Column(name = "periodicità", nullable = false)
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Rivista(String titolo, Integer anno, Integer pagine, Periodicita periodicita) {
        super(titolo, anno, pagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                "} " + super.toString();
    }
}
