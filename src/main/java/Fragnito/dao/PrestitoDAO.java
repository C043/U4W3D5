package Fragnito.dao;

import Fragnito.entities.Leggibile;
import Fragnito.entities.Prestito;
import Fragnito.entities.Utente;
import Fragnito.exceptions.NotFoundException;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PrestitoDAO {
    private EntityManager em;

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
}
