package pl.sdacademy.java.hibernate.workshop6;

import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

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

    public static Map<String,List<String>> loadCities(Properties properties, String... countryCodes) {
        throw new UnsupportedOperationException("TODO");
    }
}
