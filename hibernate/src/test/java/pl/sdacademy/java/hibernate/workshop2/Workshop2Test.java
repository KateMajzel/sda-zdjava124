package pl.sdacademy.java.hibernate.workshop2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.common.world.Country;
import pl.sdacademy.java.hibernate.tests.WorldDbHelper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop2Test {

    @BeforeAll
    static void setUp() throws Exception {
        WorldDbHelper.INSTANCE.importDatabase();
    }

    @AfterAll
    static void tearDown() {
        WorldDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldReturnEuropeanCountries() {
        //WHEN
        final List<Country> countries = Workshop2.loadCountries(WorldDbHelper.INSTANCE.getProperties());

        //THEN
        assertThat(countries)
                .isNotNull()
                .isNotEmpty()
                .extracting(Country::getCode).containsExactly(
                        "ALB", "AND", "AUT", "BLR", "BEL",
                        "BIH", "BGR", "HRV", "CZE", "DNK",
                        "EST", "FRO", "FIN", "FRA", "DEU",
                        "GIB", "GRC", "VAT", "HUN", "ISL",
                        "IRL", "ITA", "LVA", "LIE", "LTU",
                        "LUX", "MKD", "MLT", "MDA", "MCO",
                        "NLD", "NOR", "POL", "PRT", "ROM",
                        "RUS", "SMR", "SVK", "SVN", "ESP",
                        "SJM", "SWE", "CHE", "UKR", "GBR",
                        "YUG"
                );
    }
}
