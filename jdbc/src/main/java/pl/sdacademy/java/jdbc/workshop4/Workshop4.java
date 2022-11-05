package pl.sdacademy.java.jdbc.workshop4;

import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

/*
    * Zaimplementuj brakujące metody:
      * isInventoryInStock(Connection, int)
      * addRental(Connection,int,int,int)
      * getRentalRate(Connection,int)
      * addPayment(Connection,int,int,int,BigDecimal)
    * Użyj `PreparedStatement` i metod `executeQuery()` oraz `executeUpdate()`.
    * Zaimplementuj logikę wypożyczania filmu w metodzie rentDvd(...)
      z użyciem powyższych metod.
 */
public class Workshop4 {
    public static void main(String[] args) throws RentalException {
        final String jdbcUrl = ApplicationPropertiesProvider.getApplicationProperties().getProperty("jdbc.url");

        final var result = rentDvd(jdbcUrl, 10, 3, 1);

        if (result.isPresent()) {
            System.out.printf("Id wypożyczenia: %d\n", result.get());
        }
        else {
            System.out.println("Brak towaru na stanie :(");
        }
    }

    /**
     * This method adds a rental and a payment with amount equal to rate retrieved from the database.
     * For sake of simplicity it is assumed that all the given identifiers are valid.
     *
     * @return rentalId or {@code null} if given inventory is either not present or rented.
     */
    public static Optional<Integer> rentDvd(String jdbcUrl, int inventoryId, int customerId, int staffId) throws RentalException {
        try (final var connection = DriverManager.getConnection(jdbcUrl)) {
            try {
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                if (!isInventoryInStock(connection, inventoryId)) {
                    return Optional.empty();
                }

                final var rentalId = addRental(connection, inventoryId, customerId, staffId);

                final var rentalRate = getRentalRate(connection, inventoryId)
                    .orElseThrow(() -> new RentalException("Missing rental rate for inventoryId: " + inventoryId));

                addPayment(connection, customerId, staffId, rentalId, rentalRate);

                connection.commit();
                return Optional.of(rentalId);
            } catch (Exception exception) {
                connection.rollback();
                throw exception;
            }
        }
        catch (SQLException sqlException) {
            throw new RentalException(sqlException);
        }
    }

    /*
        SELECT COUNT(rental_id) = 0
        FROM inventory LEFT JOIN rental USING(inventory_id)
        WHERE inventory.inventory_id = P_INVENTORY_ID
        AND rental.return_date IS NULL;
     */

    /**
     * @return {@code true} if inventory is available in stock, {@code false} otherwise.
     */
    private static boolean isInventoryInStock(Connection connection, int inventoryId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(rental_id) = 0" +
                "        FROM inventory LEFT JOIN rental USING(inventory_id)" +
                "        WHERE inventory.inventory_id = ?" +
                "        AND rental.return_date IS NULL;");
        preparedStatement.setInt(1, inventoryId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean(1);

    }

    /*
        INSERT INTO rental(rental_date, inventory_id, customer_id, staff_id)
        VALUES(NOW(), P_INVENTORY_ID, P_CUSTOMER_ID, P_STAFF_ID);
     */

    /**
     * @return id of the newly created rental record.
     */
    private static int addRental(Connection connection, int inventoryId, int customerId, int staffId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rental(rental_date, inventory_id, customer_id, staff_id)" +
                "VALUES(NOW(), ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, inventoryId);
        preparedStatement.setInt(2, customerId);
        preparedStatement.setInt(3, staffId);
        preparedStatement.executeUpdate();
        final var rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);

    }

    /*
        SELECT rental_rate FROM inventory JOIN film USING(film_id) WHERE inventory_id = P_INVENTORY_ID AND rental_rate <> 0;
    */

    /**
     * @return rental rate of given inventory or empty optional if not available or zero.
     */
    private static Optional<BigDecimal> getRentalRate(Connection connection, int inventoryId) throws SQLException {


        PreparedStatement preparedStatement = connection.prepareStatement("SELECT rental_rate FROM inventory JOIN film USING(film_id) WHERE inventory_id = ? AND rental_rate <> 0;");
        preparedStatement.setInt(1, inventoryId);

        ResultSet resultSet = preparedStatement.executeQuery();


        if (!resultSet.next()) {
            return Optional.empty();
        }

        return  Optional.ofNullable(resultSet.getBigDecimal(1));

    }

    /*
        INSERT INTO payment (customer_id, staff_id, rental_id, amount, payment_date)
        VALUES(P_CUSTOMER_ID, P_STAFF_ID, P_RENTAL_ID, P_AMOUNT, NOW());
     */
    private static void addPayment(Connection connection, int customerId, int staffId, int rentalId, BigDecimal amount) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO payment (customer_id, staff_id, rental_id, amount, payment_date)\n" +
                "        VALUES(?, ?, ?, ?, NOW());");
        preparedStatement.setInt(1, customerId);
        preparedStatement.setInt(2, staffId);
        preparedStatement.setInt(3, rentalId);
        preparedStatement.setBigDecimal(4, amount);
        preparedStatement.executeUpdate();
    }
}

