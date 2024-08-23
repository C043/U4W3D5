package Fragnito.dao;

import Fragnito.entities.Utente;
import Fragnito.exceptions.NotFoundException;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class UtenteDAO {
    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void generateNUtenti(int n) {
        Faker faker = new Faker();

        for (int i = 0; i < n; i++) {
            Date date = faker.date().birthday(5, 110);
            LocalDate randomLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Utente newUtente = new Utente(faker.name().firstName(), faker.name().lastName(), randomLocalDate, faker.number().randomNumber());
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(newUtente);
            transaction.commit();
            System.out.println("L'utente " + newUtente.getCognome() + " è stato aggiunto con successo.");
        }
    }

    public Utente getUtenteById(UUID id) {
        Utente found = em.find(Utente.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }
}
