package pl.sdacademy.java.hibernate.workshop8;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.hibernate.tests.SakilaDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop8aTest {

    @BeforeAll
    static void setUp() throws Exception {
        SakilaDbHelper.INSTANCE.importDatabase();
    }

    @AfterAll
    static void tearDown() {
        SakilaDbHelper.INSTANCE.cleanUp();
    }

    @Test
    void shouldReturnAllStaffDataInExpectedOrder() {
        //WHEN
        final var staff = Workshop8a.findAllStaff(SakilaDbHelper.INSTANCE.getProperties());

        //THEN
        assertThat(staff).hasSize(2);
        assertThat(staff.get(0).getLastName()).isEqualTo("Hillyer");
        assertThat(staff.get(0).getAddress().getCity().getName()).isEqualTo("Lethbridge");
        assertThat(staff.get(0).getAddress().getCity().getCountry().getName()).isEqualTo("Canada");

        assertThat(staff.get(1).getLastName()).isEqualTo("Stephens");
        assertThat(staff.get(1).getAddress().getCity().getName()).isEqualTo("Woodridge");
        assertThat(staff.get(1).getAddress().getCity().getCountry().getName()).isEqualTo("Australia");
    }
}
