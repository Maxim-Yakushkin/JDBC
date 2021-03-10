package lesson_08_ResultSet_Generated_keys;

import util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        String sql = "INSERT INTO flight_storage.info (data) VALUES ('data123');";

        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {

            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getString("data"));
                System.out.println(generatedKeys.getLong("id"));
            }
        }
    }
}
