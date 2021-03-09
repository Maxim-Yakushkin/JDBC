package lesson_06_Statement_DML;

import util.ConnectionManager;

import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
//        String sql1 = "INSERT INTO flight_storage.info (data) VALUES " +
//                "('Test1')," +
//                "('Test2')," +
//                "('Test3')," +
//                "('Test4');";

//        String sql2 = "UPDATE flight_storage.info " +
//                "SET data = 'TestTest'" +
//                "WHERE id = 3;";

        String sql3 = "UPDATE flight_storage.info " +
                "SET data = 'TestTest'" +
                "WHERE id = 3 " +
                "RETURNING *;";

        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {

//            int execute = statement.execute(sql1);
//            int executeUpdate = statement.executeUpdate(sql2);
            boolean execute = statement.execute(sql3);
            System.out.println(execute);
        }
    }
}
