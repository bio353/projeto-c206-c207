package controller;
import java.sql.*;

public abstract class Database {
    protected Connection connection;
    protected Statement statement;
    protected ResultSet result;
    protected PreparedStatement pst;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DB_NAME = "projeto";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME
            + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // System.out.println("Successfully connected to the database! Info: " + connection);
        } catch (SQLException error) {
            System.out.println("Connection Error: " + error.getMessage());
        }
    }
}
