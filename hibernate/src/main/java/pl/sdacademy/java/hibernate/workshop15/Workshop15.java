package pl.sdacademy.java.hibernate.workshop15;

import pl.sdacademy.java.hibernate.common.sakila.City;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.Properties;
import java.util.Scanner;

/*
    Przygotuj metodę przyjmującą nazwę kraju i nazwę miasta, niech zwraca obiekt zarządzany.

    Zmodyfikuj klasę City tak, by kaskadowo tworzyć obiekt kraju.

    Utwórz obiekty miasta i kraju o podanych nazwach. Setterem ustaw powiązanie między obiektami miasta i kraju.

    Utrwal obiekt miasta.

    Przetestuj działanie programu.
 */
public class Workshop15 {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        System.out.println("Podaj nazwę kraju:");
        final String countryName = scanner.nextLine();
        System.out.println("Podaj nazwę miasta:");
        final String cityName = scanner.nextLine();

        final var city = createCity(ApplicationPropertiesProvider.getSakilaProperties(), countryName, cityName);
        System.out.printf("cityName: %s, cityId: %d, countryName: %s, countryId: %d\n",
            city.getName(), city.getCityId(), city.getCountry().getName(), city.getCountry().getCountryId()
        );
    }

    public static City createCity(Properties properties, String countryName, String cityName) {
        throw new UnsupportedOperationException("TODO");
    }
}
