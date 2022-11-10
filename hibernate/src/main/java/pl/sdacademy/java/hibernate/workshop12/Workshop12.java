package pl.sdacademy.java.hibernate.workshop12;

import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.Properties;
import java.util.Scanner;

/*
    Przygotuj metodę zmieniającą kraj według podanego identyfikatora i nazwy, niech zwraca true jeśli istniał i false jeśli nie (find(...) zwróci null).

    Zmień stan zarządzanego obiektu w trakcie transakcji używając zwykłego settera.

    Przetestuj następujące scenariusze:
        * Kraj nie istnieje (ma zwrócić false).
        * Dowolny inny kraj (ma go zmienić i zwrócić true).
 */
public class Workshop12 {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        System.out.println("Podaj id kraju:");
        final long countryId = Long.parseLong(scanner.nextLine());
        System.out.println("Podaj nazwę kraju:");
        final String newName = scanner.nextLine();

        final var result = renameCountry(ApplicationPropertiesProvider.getSakilaProperties(), countryId, newName);
        System.out.println("Wynik: " + result);
    }

    public static boolean renameCountry(Properties properties, long countryId, String newName) {
        throw new UnsupportedOperationException("TODO");
    }
}
