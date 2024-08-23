package Fragnito.dao;

import Fragnito.entities.Leggibile;
import Fragnito.entities.Prestito;
import Fragnito.entities.Utente;
import Fragnito.exceptions.NotFoundException;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PrestitoDAO {
    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void generatePrestito(UUID utenteId, UUID leggibileId) {
        Faker faker = new Faker();
        Date date = faker.date().between(faker.date().past(50, TimeUnit.DAYS), new Date());
        LocalDate randomLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        UtenteDAO ud = new UtenteDAO(em);
        LeggibileDAO ld = new LeggibileDAO(em);
        try {
            Utente utente = ud.getUtenteById(utenteId);
            Leggibile leggibile = ld.getLeggibileById(leggibileId);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Prestito prestito = new Prestito(utente, leggibile, randomLocalDate);
            em.persist(prestito);
            transaction.commit();
            System.out.println("Prestito di " + utente.getCognome() + " aggiunto con successo.");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(UUID utenteId, UUID leggibileId, LocalDate date) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        LeggibileDAO ld = new LeggibileDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        em.persist(new Prestito(ud.getUtenteById(utenteId), ld.getLeggibileById(leggibileId), date));
        transaction.commit();
        System.out.println("Prestito effettuato con successo.");
    }

    public Prestito getPrestitoById(UUID id) {
        Prestito found = em.find(Prestito.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void consegna(UUID prestitoId) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query updateQuery = em.createQuery("UPDATE Prestito p SET p.restituzioneEffettuata = :now WHERE p.id = :id").setParameter("now", LocalDate.now()).setParameter("id", prestitoId);
        int numModificati = updateQuery.executeUpdate();
        transaction.commit();

        System.out.println("Modificati " + numModificati + " elementi.");
    }

    public List<Prestito> getPrestitiScaduti() {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.restituzioneEffettuata IS NULL AND :now > p.finePrestito").setParameter("now", LocalDate.now()).getResultList();
    }
}
