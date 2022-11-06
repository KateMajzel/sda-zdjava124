package pl.sdacademy.java.hibernate.workshop6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sdacademy.java.hibernate.tests.WorldDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop6Test {
    @BeforeAll
    static void setUp() throws Exception {
        WorldDbHelper.INSTANCE.importDatabase();
    }

    @AfterAll
    static void tearDown() {
        WorldDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldReturnCountriesAndCitiesInExpectedOrder() {
        //WHEN
        final var countriesCities = Workshop6.loadCities(
                WorldDbHelper.INSTANCE.getProperties(),
                "LVA", "ARM", "HND"
        );

        //THEN
        assertThat(countriesCities).isNotNull();
        assertThat(countriesCities.keySet()).containsExactly("Armenia", "Honduras", "Latvia");
        assertThat(countriesCities.get("Armenia")).containsExactly("Gjumri", "Vanadzor", "Yerevan");
        assertThat(countriesCities.get("Honduras")).containsExactly("La Ceiba", "San Pedro Sula", "Tegucigalpa");
        assertThat(countriesCities.get("Latvia")).containsExactly("Daugavpils", "Liepaja", "Riga");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "XYZ"})
    void shouldNotFailOnEmptyOrInvalidQuery(String countryCode) {
        //WHEN
        final var countriesCities = Workshop6.loadCities(
                WorldDbHelper.INSTANCE.getProperties(), countryCode
        );

        //THEN
        assertThat(countriesCities).isEmpty();
    }
}
