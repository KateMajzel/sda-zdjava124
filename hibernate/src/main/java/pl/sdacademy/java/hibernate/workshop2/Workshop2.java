package pl.sdacademy.java.hibernate.workshop2;

import pl.sdacademy.java.hibernate.common.world.Country;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/*
    * Użyj bazy danych world.
    * Utwórz EntityManager i obiekt TypedQuery ładujące listę krajów z kontynentu Europe:
        SELECT c FROM Country c WHERE c.continent = 'Europe' ORDER BY c.name
    * Wypisz tę listę na standardowe wyjście.
 */
public class Workshop2 {
    public static void main(String[] args) {
        final List<Country> countries = loadCountries(ApplicationPropertiesProvider.getWorldProperties());
        final String countriesString = countries.stream().map(Country::getName).collect(Collectors.joining("\n"));
        System.out.println(countriesString);
    }

    public static List<Country> loadCountries(Properties properties) {
        throw new UnsupportedOperationException("TODO");
    }
}
