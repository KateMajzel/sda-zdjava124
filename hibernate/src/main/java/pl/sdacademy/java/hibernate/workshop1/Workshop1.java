package pl.sdacademy.java.hibernate.workshop1;

import pl.sdacademy.java.hibernate.common.world.Country;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.Properties;
import java.util.Scanner;

/*
    * Uzupełnij adnotacje w klasie Country.
    * Pozyskaj obiekt EntityManager korzystający z bazy world.
    * Używając metody find(...) załaduj kraj według podanego kodu.
    * Przetestuj program i przeanalizuj wykonane zapytania.
 */
public class Workshop1 {
    public static void main(String[] args) {
        System.out.println("Podaj kod kraju:");
        final var code = new Scanner(System.in).nextLine();

        final Country country = loadCountryByCode(ApplicationPropertiesProvider.getWorldProperties(), code);
        System.out.println(country);
    }

    public static Country loadCountryByCode(Properties properties, String code) {
        throw new UnsupportedOperationException("TODO");
    }
}
