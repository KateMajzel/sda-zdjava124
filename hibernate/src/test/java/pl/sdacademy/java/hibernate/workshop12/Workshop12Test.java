package pl.sdacademy.java.hibernate.workshop12;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop12Test {
    @BeforeEach
    void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterEach
    void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldRenameExistingCountry() {
        //WHEN
        final var result = Workshop12.renameCountry(SakilaDbHelper.INSTANCE.getProperties(), 20, "Narnia");

        //THEN
        assertThat(result).isTrue();

        final var newCanadaName = SakilaDbHelper.INSTANCE
            .query("SELECT country FROM country WHERE country.country_id = 20;", String.class)
            .orElseThrow();

        assertThat(newCanadaName).isEqualTo("Narnia");
    }

    @Test
    void shouldNotRenameNonExistingCountry() {
        //WHEN
        final var result = Workshop12.renameCountry(SakilaDbHelper.INSTANCE.getProperties(), 999, "Narnia");

        //THEN
        assertThat(result).isFalse();
    }
}
