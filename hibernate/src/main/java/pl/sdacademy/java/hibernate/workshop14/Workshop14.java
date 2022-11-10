package pl.sdacademy.java.hibernate.workshop14;

import pl.sdacademy.java.hibernate.common.sakila.City;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.Properties;
import java.util.Scanner;

/*
    Przygotuj metodę przyjmującą identyfikator kraju i nazwę miasta, niech zwraca obiekt zarządzany.

    Uzyskaj obiekt kraju przez referencję.

    Utwórz i utrwal obiekt miasta o podanej nazwie używając referencji do kraju.

    Przetestuj działanie programu. Zauważ, że obiekt kraju nie zostanie leniwie doładowany.
 */
public class Workshop14 {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        System.out.println("Podaj id kraju:");
        final long countryId = Long.parseLong(scanner.nextLine());
        System.out.println("Podaj nazwę miasta:");
        final String name = scanner.nextLine();

        final var city = createCity(ApplicationPropertiesProvider.getSakilaProperties(), name, countryId);
        System.out.printf("name: %s, id: %d\n", city.getName(), city.getCityId());
    }

    public static City createCity(Properties properties, String name, long countryId) {
        throw new UnsupportedOperationException("TODO");
    }
}
