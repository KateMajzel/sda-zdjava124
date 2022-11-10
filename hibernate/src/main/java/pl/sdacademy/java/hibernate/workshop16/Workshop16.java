package pl.sdacademy.java.hibernate.workshop16;

import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.Properties;
import java.util.Scanner;

public class Workshop16 {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        System.out.println("Podaj id kraju:");
        final long countryId = Long.parseLong(scanner.nextLine());
        System.out.println("Podaj nazwę kraju:");
        final String newName = scanner.nextLine();

        final var affectedRows = renameCountry(ApplicationPropertiesProvider.getSakilaProperties(), countryId, newName);
        System.out.println("Wynik: " + affectedRows);
    }

    public static int renameCountry(Properties properties, long countryId, String newName) {
        throw new UnsupportedOperationException("TODO");
    }
}
