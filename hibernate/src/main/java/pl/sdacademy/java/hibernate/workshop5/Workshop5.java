package pl.sdacademy.java.hibernate.workshop5;

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

        Map<String,List<String>> map = loadCities(ApplicationPropertiesProvider.getWorldProperties(), countryCodes.split(","));

        final StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("\n");
            for (String city : entry.getValue()) {
                sb.append("\t").append(city).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static Map<String,List<String>> loadCities(Properties properties, String... countryCodes) {
        throw new UnsupportedOperationException("TODO");
    }
}
