package pl.sdacademy.java.hibernate.workshop10;

import pl.sdacademy.java.hibernate.common.sakila.Country;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

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
        throw new UnsupportedOperationException("TODO");
    }
}
