package pl.sdacademy.java.hibernate.tests;

import org.h2.tools.DeleteDbFiles;
import org.h2.tools.RunScript;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public abstract class AbstractTestDbHelper {

    private final String dbName;
    private final String dbFile;
    private final String jdbcUrl;

    protected AbstractTestDbHelper(String dbName, String dbFile) {
        this.dbName = dbName;
        this.dbFile = dbFile;
        this.jdbcUrl = "jdbc:h2:~/" + dbName + ";MODE=MYSQL;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE";
    }

    public Properties getProperties() {
        final var properties = new Properties();
        properties.put("jakarta.persistence.jdbc.url", jdbcUrl);
        return properties;
    }

    public void importDatabase() throws IOException, SQLException {
        try (
                final var worldInputStream = AbstractTestDbHelper.class.getResourceAsStream(dbFile);
                final var connection = DriverManager.getConnection(jdbcUrl)
        ) {
            if (worldInputStream == null) {
                throw new IllegalStateException(String.format("Missing '%s' resource!", dbFile));
            }

            RunScript.execute(connection, new InputStreamReader(worldInputStream));
        }
    }

    public <T> Optional<T> query(String sql, Class<T> resultType, Object... params) {
        try (final var connection = DriverManager.getConnection(jdbcUrl)) {
            final var preparedStatement = connection.prepareStatement(sql);

            applyParams(preparedStatement, params);
            final var rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }

            return Optional.ofNullable(rs.getObject(1)).map(resultType::cast);
        }
        catch(SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public void execute(String sql, Object... params) {
        try (final var connection = DriverManager.getConnection(jdbcUrl)) {
            final var preparedStatement = connection.prepareStatement(sql);

            applyParams(preparedStatement, params);
            preparedStatement.execute();
        }
        catch(SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    private void applyParams(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        for (int paramIndex = 1; paramIndex <= params.length; paramIndex++) {
            preparedStatement.setObject(paramIndex, params[paramIndex-1]);
        }
    }

    public void cleanUp() {
        DeleteDbFiles.execute(System.getProperty("user.home"), dbName, false);
    }
}
