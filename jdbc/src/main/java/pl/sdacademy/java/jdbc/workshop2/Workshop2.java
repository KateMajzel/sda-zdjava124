package pl.sdacademy.java.jdbc.workshop2;

import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * Zmodyfikuj poprzedni program tak, aby ładować miasta według podanego przez użytkownika kraju.
 * Co się stanie, jeśli użytkownik poda tekst `Narnia ' OR 'x' = 'x`?
 * Jak można temu zapobiec?
 */
public class Workshop2 {

    public static void main(String[] args) {
        final String jdbcUrl = ApplicationPropertiesProvider.getApplicationProperties().getProperty("jdbc.url");

        System.out.println("Podaj nazwę kraju z którego załadować miasta:");
        final var countryName = new Scanner(System.in).nextLine();

        final var cities = getCities(jdbcUrl, countryName);
        System.out.printf("Miasta w '%s':\n\n%s", countryName, String.join("\n", cities));
    }

    public static List<String> getCities(String jdbcUrl, String countryName) {
        final List<String> cities = new LinkedList<>();
        try (final Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT city FROM city JOIN country ON country.country_id = city.country_id WHERE country = ?");
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cities.add(resultSet.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}