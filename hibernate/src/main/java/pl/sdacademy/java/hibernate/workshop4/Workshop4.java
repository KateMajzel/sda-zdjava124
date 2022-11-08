package pl.sdacademy.java.hibernate.workshop4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import pl.sdacademy.java.hibernate.common.world.City;
import pl.sdacademy.java.hibernate.common.world.Country;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
    * Używając adnotacji @ManyToOne i @JoinColumn, dostosuj klasę City tak, aby obsługiwać powiązanie z Country.

    * Przygotuj metodę zwracającą List<City> z zapytaniem pobierającym miasta według podanego kodu kraju.

    * Przeanalizuj wygenerowane zapytania.
 */
public class Workshop4 {
    public static void main(String[] args) {
        System.out.println("Podaj kod kraju:");
        final String countryCode = new Scanner(System.in).nextLine();

        final List<City> cities = loadCities(countryCode, ApplicationPropertiesProvider.getWorldProperties());
        final String countriesString = cities.stream().map(City::getName).collect(Collectors.joining("\n"));
        System.out.println(countriesString);
    }

    public static List<City> loadCities(String countryCode, Properties properties) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WorldPU", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<City> cities;
        try{
            TypedQuery<City> typedQuery = entityManager.createQuery("""
                SELECT city FROM City city
                JOIN FETCH city.country
                WHERE city.country.code = :code ORDER BY city.name
                """,
                    City.class
            );

            typedQuery.setParameter("code", countryCode);

            cities = typedQuery.getResultList();
        }finally {
            entityManagerFactory.close();
        }

        return cities;
    }
}
