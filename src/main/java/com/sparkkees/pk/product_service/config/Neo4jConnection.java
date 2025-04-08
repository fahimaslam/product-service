package com.sparkkees.pk.product_service.config;

import org.neo4j.driver.*;

public class Neo4jConnection {
    public Session openConnection() {
        String uri = "neo4j://localhost:7687";  // Change to your Neo4j server URI
        String user = "neo4j";
        String password = "your_password";  // Set your actual password
        Session session = null;
        Config config = Config.builder()
                .withMaxConnectionPoolSize(10)
                .build();
        // Create a Neo4j driver instance
        try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password), config)) {
            session = driver.session();
            // Execute a simple Cypher query
            String cypherQuery = "MATCH (n) RETURN count(n) AS nodeCount";
            Result result = session.run(cypherQuery);
            int nodeCount = result.single().get("nodeCount").asInt();

            // Print the result
            System.out.println("Total nodes in the database: " + nodeCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
}
