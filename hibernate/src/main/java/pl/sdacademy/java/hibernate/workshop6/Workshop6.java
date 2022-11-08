package pl.sdacademy.java.hibernate.workshop6;

import jakarta.persistence.*;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.*;

/*
    Utwórz finalną klasę CityCountryPair z polami finalnymi city i country typu String.

    Zmodyfikuj kod z poprzedniego ćwiczenia tak, aby użyć tej klasy jako typu wyniku zapytania. Pamiętaj o modyfikacji zapytania.

    🤔 Spróbuj samodzielnie: poeksperymentuj z polami w tej klasie.
 */
public class Workshop6 {
    public static void main(String[] args) {
        System.out.println("Podaj kody krajów rozdzielone przecinkami:");
        final String countryCodes = new Scanner(System.in).nextLine();

        Map<String, List<String>> map = loadCities(ApplicationPropertiesProvider.getWorldProperties(), countryCodes.split(","));

        final StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("\n");
            for (String city : entry.getValue()) {
                sb.append("\t").append(city).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static Map<String, List<String>> loadCities(Properties properties, String... countryCodes) {
        if (countryCodes == null || countryCodes.length < 1 || Arrays.stream(countryCodes).anyMatch(Objects::isNull)) {
            return Collections.emptyMap();
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WorldPU", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<CityCountryPair> cityCountryPairs;
        try {
            TypedQuery<CityCountryPair> typedQuery = entityManager.createQuery("""
                    SELECT new pl.sdacademy.java.hibernate.workshop6.CityCountryPair(city.country.name, city.name)
                    FROM City city
                    JOIN city.country
                    WHERE city.country.code IN :countryCodes ORDER BY city.country.name, city.name
                    """,
                CityCountryPair.class
            );

            typedQuery.setParameter("countryCodes", Set.of(countryCodes));

            cityCountryPairs = typedQuery.getResultList();
        } finally {
            entityManagerFactory.close();
        }
        TreeMap<String, List<String>> map = new TreeMap<>();
        for (CityCountryPair cityCountryPair : cityCountryPairs) {
            String countryName = cityCountryPair.country();
            String cityName = cityCountryPair.city();
            map.computeIfAbsent(countryName, x -> new LinkedList<>()).add(cityName);
        }
        return map;
    }
}
