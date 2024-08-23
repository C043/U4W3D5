package Fragnito.dao;

import Fragnito.entities.Leggibile;
import Fragnito.entities.Libro;
import Fragnito.entities.Rivista;
import Fragnito.exceptions.NotFoundException;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

import static Fragnito.entities.Periodicita.randomPeriodicita;

public class LeggibileDAO {
    private final EntityManager em;

    public LeggibileDAO(EntityManager em) {
        this.em = em;
    }

    public void generateNLibri(int n) {
        Faker faker = new Faker();

        for (int i = 0; i < n; i++) {
            Libro newLibro = new Libro(faker.book().title(), faker.number().numberBetween(1453, 2024), faker.number().numberBetween(50, 750), faker.book().author(), faker.book().genre());
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(newLibro);
            transaction.commit();
            System.out.println("Il libro " + newLibro.getTitolo() + " è stato aggiunto con successo.");
        }
    }

    public void generateNRiviste(int n) {
        Faker faker = new Faker();

        for (int i = 0; i < n; i++) {
            Rivista newRivista = new Rivista(faker.book().title(), faker.number().numberBetween(1453, 2024), faker.number().numberBetween(50, 750), randomPeriodicita());
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(newRivista);
            transaction.commit();
            System.out.println("La rivista " + newRivista.getTitolo() + " è stata aggiunta con successo.");
        }
    }

    public void save(Leggibile leggibile) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(leggibile);
        transaction.commit();

        System.out.println("Elemento leggibile " + leggibile.getTitolo() + " aggiunto con successo.");
    }

    public Leggibile getLeggibileById(UUID id) {
        Leggibile found = em.find(Leggibile.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }
}
