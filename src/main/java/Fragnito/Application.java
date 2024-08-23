package Fragnito;

import Fragnito.dao.LeggibileDAO;
import Fragnito.dao.PrestitoDAO;
import Fragnito.dao.UtenteDAO;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4W3D5");

    public static void main(String[] args) {
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();

        LeggibileDAO ld = new LeggibileDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        PrestitoDAO pd = new PrestitoDAO(em);

/*
        pd.generatePrestito(UUID.fromString("2877421f-9f6c-4804-a6dc-d2dc8d3d5650"), UUID.fromString("17590749-558f-4ea8-9230-92b7eb7bbc2f"));
*/

        /*ld.save(new Libro(faker.book().title(), faker.number().numberBetween(1453, 2024), faker.number().numberBetween(50, 750), faker.book().author(), faker.book().genre()));
        ld.save(new Rivista(faker.book().title(), faker.number().numberBetween(1453, 2024), faker.number().numberBetween(50, 750), randomPeriodicita()));*/

        /*try {
            ld.deleteElement(UUID.fromString("5da97ad9-91d1-4e5a-99c0-db56e18f92ac"));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }*/

        /*ud.generateNUtenti(5);*/

        /*ld.generateNLibri(5);
        ld.generateNRiviste(5);*/
        /*System.out.println(ld.getLeggibileById(UUID.fromString("0278aa10-653f-4fd2-80c5-516891dfde42")));*/

        ld.getLeggibileByAnno(1546).forEach(System.out::println);
        ld.getLeggibileByAutore("Alix Price").forEach(System.out::println);
        ld.getLeggibileByTitle("In Dubious Battle").forEach(System.out::println);

        em.close();
        emf.close();
    }
}
