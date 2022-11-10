package pl.sdacademy.java.hibernate.workshop10;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import pl.sdacademy.java.hibernate.common.sakila.Country;
import pl.sdacademy.java.hibernate.common.sakila.Staff;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Properties;

/*
    Użyj bazy Sakila.

    Zaadnotuj @GeneratedValue identyfikator w klasie Country.

    Utwórz nową instancję Country i ustaw nazwę.

    Używając metody persist() w ramach transakcji spróbuj utrwalić ten obiekt.

    Sprawdź działanie programu i zawartość bazy.
 */
public class Workshop10 {
    public static void main(String[] args) {
        final var country = createCountry(ApplicationPropertiesProvider.getSakilaProperties(), "Narnia");
        System.out.printf("name: %s, id: %d\n", country.getName(), country.getCountryId());
    }

    public static Country createCountry(Properties properties, String name) {
        final Country country = new Country();
        country.setName(name);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SakilaPU", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(country);

            entityManager.getTransaction().commit();
        }
        catch(Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        finally {
            entityManagerFactory.close();
        }

        return country;
    }
}
