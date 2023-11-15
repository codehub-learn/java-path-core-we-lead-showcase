import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.exit;
import static java.lang.System.getenv;

public class DatabaseConnection {
    private static final String LINE_DELIMITER = "---------------------------------------";
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static final Lorem generator = LoremIpsum.getInstance();
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
    private static final Properties sqlCommands = new Properties();

    public static void main(String[] args) {
        try (
//                Without connection pooling
//                Connection connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE)

                // With connection pooling
                Connection connection = createDataSource().getConnection()
        ) {
            logger.info("Valid connection: {}", connection.isValid(0));

            loadSQLCommands();

            createTable(connection);
            insertData(connection);

            commitData(connection);

            readData(connection);

            rollbackData(connection);
            readData(connection);

            batchInsertGenerateData(connection, 20);
            readData(connection);

            updateData(connection);
            readData(connection);

            deleteData(connection);
            readData(connection);

            commitData(connection);
        } catch (SQLException e) {
            logger.error("Could not connect to the database", e);
            exit(-1);
        }
    }

    private static void loadSQLCommands() {
        try (InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream(
                "sql.properties")) {
            if (inputStream == null) {
                logger.error("Unable to find sql.properties, exiting application");
                exit(-1);
            }
            sqlCommands.load(inputStream);
        } catch (IOException e) {
            logger.error("Unable to find sql.properties, exiting application.");
            exit(-1);
        }
    }

    private static void createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(sqlCommands.getProperty("create.table"));
            logger.info("Create table command was successful with result {}", result);
        } catch (SQLException e) {
            logger.error("Error while creating table.", e);
            exit(-1);
        }
    }

    private static void insertData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            int rowsAffected = statement.executeUpdate("""
                                                            INSERT INTO Registration (firstname, lastname, age)
                                                            VALUES
                                                            ('John', 'Smith', 18),
                                                            ('Mathew', 'Johnson', 41)
                                                            """);
            logger.info("Insert command was successful with {} row(s) affected", rowsAffected);
        } catch (SQLException e) {
            logger.error("Error while inserting data.", e);
        }
    }

    private static void batchInsertGenerateData(Connection connection, int howManyStatements) {
        String sql = """
                        INSERT INTO Registration
                        (firstname, lastname, age)
                        VALUES
                        (?, ?, ?)
                        """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 1; i <= howManyStatements; i++) {
                preparedStatement.clearParameters();

                preparedStatement.setString(1, generator.getFirstName());
                preparedStatement.setString(2, generator.getLastName());
                preparedStatement.setInt(3, ThreadLocalRandom.current().nextInt(18, 75));

                preparedStatement.addBatch();
            }

            int[] rowsAffectedArray = preparedStatement.executeBatch();
            logger.info("Insert batch statement command was successfully executed with {} row(s) affected",
                    Arrays.stream(rowsAffectedArray).summaryStatistics().getSum());
        } catch (SQLException e) {
            logger.error("Error while inserting data.", e);
        }
    }

    private static void readData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                                                            SELECT *
                                                            FROM Registration
                                                            ORDER BY id
                                                            """);
            logger.info(LINE_DELIMITER);
            int rowCount = 1;
            while (resultSet.next()) {
                logger.info("{}. {}:{}, {}:{}, {}:{}, {}:{}", rowCount++,
                        resultSet.getMetaData().getColumnName(1), resultSet.getInt(1),
                        resultSet.getMetaData().getColumnName(2), resultSet.getString(2),
                        resultSet.getMetaData().getColumnName(3), resultSet.getString(3),
                        resultSet.getMetaData().getColumnName(4), resultSet.getInt(4)
                        );
            }
            logger.info(LINE_DELIMITER);
        } catch (SQLException e) {
            logger.error("Error while reading data", e);
        }
    }

    private static void updateData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = """
                            UPDATE Registration
                            SET age = 100
                            WHERE id > 10
                            """;
            int rowsAffected = statement.executeUpdate(sql);
            logger.info("Update command was successful with {} row(s) affected", rowsAffected);
        } catch (SQLException e) {
            logger.error("Error while updating data.", e);
        }
    }

    private static void deleteData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate("""
                                                              DELETE FROM Registration
                                                              WHERE age = 100
                                                              """);
            logger.info("Delete command was successful with {} row(s) affected", rowsAffected);
        } catch (SQLException e) {
            logger.error("Error while updating data.", e);
        }
    }

    private static void commitData(Connection connection) {
        try {
            connection.commit();
            logger.debug(LINE_DELIMITER);
            logger.debug("Transaction was committed");
            logger.debug(LINE_DELIMITER);
        } catch (SQLException e) {
            logger.warn("Unable to commit transaction");
        }
    }

    private static void rollbackData(Connection connection) {
        try {
            connection.rollback();
            logger.debug(LINE_DELIMITER);
            logger.debug("Transaction was rolled back.");
            logger.debug(LINE_DELIMITER);
        } catch (SQLException e) {
            logger.warn("Unable to rollback transaction");
        }
    }

    private static DataSource createDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(DB_CONNECTION_URL_MEMORY_MODE);
        return ds;
    }
}
