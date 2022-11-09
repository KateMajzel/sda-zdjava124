package pl.sdacademy.java.hibernate.workshop8;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import pl.sdacademy.java.hibernate.common.sakila.Address;
import pl.sdacademy.java.hibernate.common.sakila.City;
import pl.sdacademy.java.hibernate.common.sakila.Country;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Properties;

/*
    Przygotuj zapytanie JPQL ładujące wszystkie kraje.

    Przyjrzyj się wygenerowanym zapytaniom SQL.

    W jaki sposób najlepiej rozwiązać występujące problemy?
 */
public class Workshop8b {
    public static void main(String[] args) {
        final List<Country> countries = findAllCountries(ApplicationPropertiesProvider.getSakilaProperties());
        System.out.println(countriesToString(countries));
    }

    public static String countriesToString(List<Country> countries) {
        final StringBuilder sb = new StringBuilder();
        for (Country country : countries) {
            sb.append(country.getName()).append("\n");
            for (City city : country.getCities()) {
                sb.append("\t").append(city.getName()).append("\n");
                for (Address address : city.getAddresses()) {
                    sb.append("\t\t").append(address.getAddress()).append("\n");
                }
            }
        }

        return sb.toString();
    }

    public static List<Country> findAllCountries(Properties properties) {
        throw new UnsupportedOperationException("TODO");
    }
}
