package pl.sdacademy.java.hibernate.workshop4;

import pl.sdacademy.java.hibernate.common.world.City;
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
        throw new UnsupportedOperationException("TODO");
    }
}
