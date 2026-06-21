package com.collectionsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Aplicação do Factory Pattern para isolamento de infraestrutura de conexão.
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/collection_system_db";
    private static final String USER = "root";
    private static final String PASSWORD = "LMPc14082009!";

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não localizado.", e);
        }
    }
}