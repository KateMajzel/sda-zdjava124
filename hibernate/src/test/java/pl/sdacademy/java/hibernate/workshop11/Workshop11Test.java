package pl.sdacademy.java.hibernate.workshop11;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class Workshop11Test {
    @BeforeEach
    void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterEach
    void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldRemoveExistingCountry() {
        //GIVEN
        SakilaDbHelper.INSTANCE.execute("INSERT INTO country(country) VALUES ('Atlantis');");

        //WHEN
        final var result = Workshop11.removeCountry(SakilaDbHelper.INSTANCE.getProperties(), 110);

        //THEN
        assertThat(result).isTrue();

        final var atlantisExists = SakilaDbHelper.INSTANCE
            .query("SELECT EXISTS (SELECT * FROM country WHERE country.country_id = 110);", Boolean.class)
            .orElseThrow();

        assertThat(atlantisExists).isFalse();
    }

    @Test
    void shouldNotRemoveNonExistingCountry() {
        //WHEN
        final var result = Workshop11.removeCountry(SakilaDbHelper.INSTANCE.getProperties(), 999);

        //THEN
        assertThat(result).isFalse();
    }

    @Test
    void shouldFailOnForeignKeyConstraintViolation() {
        //WHEN
        final var thrown = catchThrowable(() ->
            Workshop11.removeCountry(SakilaDbHelper.INSTANCE.getProperties(), 20)
        );

        //THEN
        assertThat(thrown).isInstanceOf(PersistenceException.class);
    }
}
