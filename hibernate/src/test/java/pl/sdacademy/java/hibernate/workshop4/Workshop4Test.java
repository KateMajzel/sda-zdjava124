package pl.sdacademy.java.hibernate.workshop4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sdacademy.java.hibernate.common.world.City;
import pl.sdacademy.java.hibernate.tests.WorldDbHelper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop4Test {
    @BeforeAll
    static void setUp() throws Exception {
        WorldDbHelper.INSTANCE.importDatabase();
    }

    @AfterAll
    static void tearDown() {
        WorldDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldReturnCzechCities() {
        //WHEN
        final List<City> cities = Workshop4.loadCities("CZE", WorldDbHelper.INSTANCE.getProperties());

        //THEN
        assertThat(cities)
                .isNotNull()
                .isNotEmpty()
                .extracting(City::getName).containsExactly(
                    "Brno",
                    "Ceské Budejovice",
                    "Hradec Králové",
                    "Liberec",
                    "Olomouc",
                    "Ostrava",
                    "Pardubice",
                    "Plzen",
                    "Praha",
                    "Ústí nad Labem"
                );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "XYZ"})
    void shouldReturnEmptyList(String countryCode) {
        //WHEN
        final List<City> cities = Workshop4.loadCities(countryCode, WorldDbHelper.INSTANCE.getProperties());

        //THEN
        assertThat(cities).isEmpty();
    }
}
