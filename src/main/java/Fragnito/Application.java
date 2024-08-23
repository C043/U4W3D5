package Fragnito;

import Fragnito.dao.LeggibileDAO;
import Fragnito.dao.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W3D5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        LeggibileDAO ld = new LeggibileDAO(em);
        UtenteDAO ud = new UtenteDAO(em);

        /*ud.generateNUtenti(5);*/

        /*ld.generateNLibri(5);
        ld.generateNRiviste(5);*/

        em.close();
        emf.close();
    }
}
