package lesson_09_SQL_Injection;

import util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {

//        List<Long> ticketsIdByFlightId = getTicketsIdByFlightId("2"); // хороший запрос
        List<Long> ticketsIdByFlightId = getTicketsIdByFlightId("2 OR 1 = 1"); // плохой запрос (хакерский). Вернуться ВСЕ id, а не только 2
        System.out.println(ticketsIdByFlightId);

    }

    public static List<Long> getTicketsIdByFlightId(String flightId) throws SQLException {
        var sqlQuery = "SELECT id FROM flight_storage.ticket WHERE flight_id = %s;".formatted(flightId);


        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {

            List<Long> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                result.add(resultSet.getLong("id"));
            }

            return result;
        }
    }
}
