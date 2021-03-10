package lesson_07_ResultSet_SELECT;

import util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT * FROM flight_storage.ticket;";

        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet + "\n--------------------------------");
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("id"));
                System.out.println(resultSet.getString("passenger_no"));
                System.out.println(resultSet.getString("passenger_name"));
                System.out.println(resultSet.getDouble("cost"));
                System.out.println("------------");
            }
        }
    }
}
