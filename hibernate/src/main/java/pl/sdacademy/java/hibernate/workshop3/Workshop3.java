package pl.sdacademy.java.hibernate.workshop3;

import pl.sdacademy.java.hibernate.common.world.Country;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
    * Zmodyfikuj poprzedni program tak, by ładować kraje według podanego przez użytkownika kontynentu (użyj klasy Scanner).
    * Użyj parametryzowanych zapytań.
 */
public class Workshop3 {
    public static void main(String[] args) {
        System.out.println("Podaj nazwę kontynentu:");
        final String continent = new Scanner(System.in).nextLine();

        final List<Country> countries = loadCountries(continent, ApplicationPropertiesProvider.getWorldProperties());
        final String countriesString = countries.stream().map(Country::getName).collect(Collectors.joining("\n"));
        System.out.println(countriesString);
    }

    public static List<Country> loadCountries(String continent, Properties properties) {
        throw new UnsupportedOperationException("TODO");
    }
}
