package pl.sdacademy.java.hibernate.workshop8;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import pl.sdacademy.java.hibernate.common.sakila.Film;
import pl.sdacademy.java.hibernate.common.sakila.Staff;
import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/*
    Przygotuj zapytanie ładujące cały personel posortowany według nazwiska i imienia.

    Jakie zapytania się wykonują i dlaczego?

    Przebuduj zapytanie JPQL tak, by ładować komplet danych jednym zapytaniem SQL.
 */
public class Workshop8a {
    public static void main(String[] args) {
        final List<Staff> staff = findAllStaff(ApplicationPropertiesProvider.getSakilaProperties());
        final String staffString = staff.stream().map(Workshop8a::staffToString).collect(Collectors.joining("\n"));
        System.out.println(staffString);
    }

    public static String staffToString(Staff staff) {
        return String.format("""
            %s %s
            %s
            %s (%s)
            """,
            staff.getFirstName(), staff.getLastName(),
            staff.getAddress().getAddress(),
            staff.getAddress().getCity().getName(),
            staff.getAddress().getCity().getCountry().getName()
        );
    }

    public static List<Staff> findAllStaff(Properties properties) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SakilaPU", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Staff> query = entityManager.createQuery("""
            SELECT staff FROM Staff staff
            JOIN FETCH staff.address.city.country
            ORDER BY staff.lastName, staff.firstName
            """, Staff.class);

            List<Staff> staff = query.getResultList();
            return staff;

        } finally {
            entityManagerFactory.close();
        }
    }
}
