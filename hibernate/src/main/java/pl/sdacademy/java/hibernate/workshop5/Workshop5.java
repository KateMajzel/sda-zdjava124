package pl.sdacademy.java.hibernate.workshop5;

import jakarta.persistence.*;
import pl.sdacademy.java.hibernate.common.world.City;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.*;

/*
    Metoda przyjmująca kody krajów w postaci vararg String..., a zwracająca Map<String,List<String>> (nazwa kraju → lista nazw miast).

    Mapa powinna zachowywać kolejność – sortowanie według nazwy kraju oraz nazwy miasta.

    Użyj projekcji, krotki (Tuple) jako typu wyniku i wyrażenia IN.

    Upewnij się, że generowane jest pojedyncze zapytanie. Nie używaj JOIN FETCH.

    🤔 Spróbuj samodzielnie: poeksperymentuj z sekcją SELECT.
 */
public class Workshop5 {
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
        if(countryCodes == null || countryCodes.length < 1 || Arrays.stream(countryCodes).anyMatch(Objects::isNull)){
            return Collections.emptyMap();
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WorldPU", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Tuple> tuples;
        try {
            TypedQuery<Tuple> typedQuery = entityManager.createQuery("""
                            SELECT city.name AS cityName, city.country.name AS countryName 
                            FROM City city
                            JOIN city.country
                            WHERE city.country.code IN :countryCodes ORDER BY city.country.name, city.name
                            """,
                    Tuple.class
            );

            typedQuery.setParameter("countryCodes", Set.of(countryCodes));

            tuples = typedQuery.getResultList();
        } finally {
            entityManagerFactory.close();
        }
        TreeMap<String, List<String>> map = new TreeMap<>();
        for (Tuple tuple : tuples) {
            String countryName = tuple.get("countryName", String.class);
            String cityName = tuple.get("cityName", String.class);
            map.computeIfAbsent(countryName, x-> new LinkedList<>()).add(cityName);
        }
        return map;
    }
}
