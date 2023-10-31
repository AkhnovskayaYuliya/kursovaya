package ru.netology.kursovaya.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    private static String getStatus(String query) {
        var connection = getConnect();
        String status = runner.query(connection, query, new ScalarHandler<String>());
        System.out.println(status);
        return status;
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }


}
