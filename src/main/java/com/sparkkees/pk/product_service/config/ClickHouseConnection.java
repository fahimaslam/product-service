package com.sparkkees.pk.product_service.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ClickHouseConnection {

    public void openConnection(){
        // Database URL (Replace with your ClickHouse instance details)
        String url = "jdbc:ch://localhost:8123/default"; // Default database: "default"
        String user = "default";  // Default ClickHouse user
        String password = "";     // No password by default

        // Establish Connection
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to ClickHouse!");

            // Execute Query
            String query = "SELECT version()";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    System.out.println("ClickHouse Version: " + rs.getString(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
