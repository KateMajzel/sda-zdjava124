package pl.sdacademy.java.jdbc.workshop3;

import pl.sdacademy.java.jdbc.model.Film;
import pl.sdacademy.java.jdbc.model.Rating;
import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/*
    * Użyj przygotowanej klasy `Film`.
    * Zaimplementuj metodę ładującą listę filmów według fragmentu tytułu.
    * Wielkość znaków nie powinna mieć znaczenia.
    * W przypadku podania blanka, czyli ciągu znaków, który jest pusty, złożony
      z samych białych znaków (spacje) lub null, metoda ta powinna zwracać pustą listę.
      Lista powinna być posortowana według tytułu.
 */
public class Workshop3 {
    //SELECT title, description,release_year,rating FROM film WHERE UPPER(title) LIKE UPPER('%beast%');
    public static void main(String[] args) {
        final String jdbcUrl = ApplicationPropertiesProvider.getApplicationProperties().getProperty("jdbc.url");

        System.out.println("Podaj fragment tytułu filmu:");
        final var query = new Scanner(System.in).nextLine();

        final var filmStrings = getFilms(jdbcUrl, query).stream()
                .map(Film::toString)
                .collect(Collectors.toList());

        System.out.printf("Filmy pasujące do frazy '%s':\n\n%s", query, String.join("\n", filmStrings));
    }

    public static List<Film> getFilms(String jdbcUrl, String query) {
        if(query==null || query.isBlank()){
            return Collections.emptyList();
        }

        final List<Film> films = new LinkedList<>();
        try (final Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT title, description,release_year,rating FROM film WHERE UPPER(title) LIKE UPPER(?) ORDER BY title ;");
            preparedStatement.setString(1, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final var calendar = Calendar.getInstance();
                calendar.setTime(resultSet.getDate("release_year"));
                int year = calendar.get(Calendar.YEAR);

                String ratingString = resultSet.getString("rating");
                Rating rating = Rating.getByCode(ratingString).orElseThrow(() -> new IllegalArgumentException("Brak rating"));

                Film film = new Film(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        year,
                        rating
                );
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

}
