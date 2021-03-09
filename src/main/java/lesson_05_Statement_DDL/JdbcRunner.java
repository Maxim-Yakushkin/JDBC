package lesson_05_Statement_DDL;

import util.ConnectionManager;

import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS flight_storage.info (" +
                "id BIGSERIAL PRIMARY KEY ," +
                "data TEXT NOT NULL" +
                ");";

        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {
            System.out.println(connection.getTransactionIsolation());
            var execute = statement.execute(sql);
            System.out.println(execute);
        }
    }
}
