package pl.sdacademy.java.hibernate.workshop13;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sdacademy.java.hibernate.common.sakila.Country;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;


class Workshop13Test {
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
        final var country = new Country();
        country.setCountryId(20L);
        country.setName("Narnia");

        final var managedCountry = Workshop13.mergeCountry(SakilaDbHelper.INSTANCE.getProperties(), country);

        //THEN
        assertThat(managedCountry.getCountryId()).isEqualTo(20L);
        assertThat(managedCountry.getName()).isEqualTo("Narnia");

        final var newCanadaName = SakilaDbHelper.INSTANCE
            .query("SELECT country FROM country WHERE country.country_id = 20;", String.class)
            .orElseThrow();

        assertThat(newCanadaName).isEqualTo("Narnia");
    }

    @ParameterizedTest
    @ValueSource(longs = 999L)
    @NullSource
    void shouldCreateNonExistingCountryAndSetAutogeneratedId(Long id) {
        //WHEN
        final var country = new Country();
        country.setCountryId(id);
        country.setName("Narnia");

        final var managedCountry = Workshop13.mergeCountry(SakilaDbHelper.INSTANCE.getProperties(), country);

        //THEN
        assertThat(managedCountry.getCountryId()).isEqualTo(110L);
        assertThat(managedCountry.getName()).isEqualTo("Narnia");

        final var newCountryName = SakilaDbHelper.INSTANCE
            .query("SELECT country FROM country WHERE country.country_id = 110;", String.class)
            .orElseThrow();

        assertThat(newCountryName).isEqualTo("Narnia");
    }
}
