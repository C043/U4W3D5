package Fragnito.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_prestato_id")
    private Leggibile leggibile;

    @Column(name = "data_inizio_prestito", nullable = false)
    private LocalDate inizioPrestito;

    @Column(name = "data_restituzione_prestito", nullable = false)
    private LocalDate finePrestito;

    @Column(name = "data_restituzione_effettuata")
    private LocalDate restituzioneEffettuata;

    public Prestito() {
    }

    public Prestito(Utente utente, Leggibile leggibile, LocalDate inizioPrestito) {
        this.utente = utente;
        this.leggibile = leggibile;
        this.inizioPrestito = inizioPrestito;
        this.finePrestito = inizioPrestito.plusDays(30);
    }

    public UUID getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Leggibile getLeggibile() {
        return leggibile;
    }

    public void setLeggibile(Leggibile leggibile) {
        this.leggibile = leggibile;
    }

    public LocalDate getInizioPrestito() {
        return inizioPrestito;
    }

    public void setInizioPrestito(LocalDate inizioPrestito) {
        this.inizioPrestito = inizioPrestito;
    }

    public LocalDate getFinePrestito() {
        return finePrestito;
    }

    public void setFinePrestito(LocalDate finePrestito) {
        this.finePrestito = finePrestito;
    }

    public LocalDate getRestituzioneEffettuata() {
        return restituzioneEffettuata;
    }

    public void setRestituzioneEffettuata(LocalDate restituzioneEffettuata) {
        this.restituzioneEffettuata = restituzioneEffettuata;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", leggibile=" + leggibile +
                ", inizioPrestito=" + inizioPrestito +
                ", finePrestito=" + finePrestito +
                ", restituzioneEffettuata=" + restituzioneEffettuata +
                '}';
    }
}
