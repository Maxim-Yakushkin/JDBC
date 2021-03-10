package lesson_10_PrepareStatement;

import util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {

//        List<Long> ticketsIdByFlightId = getTicketsIdByFlightId(2L);
//        System.out.println(ticketsIdByFlightId);

        List<Long> flightsBetween = getFlightsBetween(LocalDate.of(2020, 10, 1).atStartOfDay(), LocalDateTime.now());
        System.out.println(flightsBetween);

    }

    public static List<Long> getFlightsBetween(LocalDateTime start, LocalDateTime end) throws SQLException {
        List<Long> result = new ArrayList<>();

        String sql = "SELECT id FROM flight_storage.flight WHERE departure_date BETWEEN ? AND ?";

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            System.out.println(preparedStatement);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(start));
            System.out.println(preparedStatement);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(end));
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getLong("id"));
            }

            return result;
        }
    }

    public static List<Long> getTicketsIdByFlightId(Long flightId) throws SQLException {
        List<Long> result = new ArrayList<>();

        var sqlQuery = "SELECT id " +
                "FROM flight_storage.ticket " +
                "WHERE flight_id = ?"; // вопросами установим наши значения

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setLong(1, flightId); // вопросу под индексом 1 устанавлваем в запрос нашу переменную flightId

            ResultSet resultSet = preparedStatement.executeQuery(); // выполняем запрос и получаем resultSet
            while (resultSet.next()) {
                result.add(resultSet.getLong("id")); // получаем из resultSet все значения поля id
            }

            return result;
        }
    }
}
