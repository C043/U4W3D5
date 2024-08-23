package Fragnito;

import Fragnito.dao.LeggibileDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W3D5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        LeggibileDAO ld = new LeggibileDAO(em);

       /* ld.generateNLibri(5);
        ld.generateNRiviste(5);*/

        em.close();
        emf.close();
    }
}
