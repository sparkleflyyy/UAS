package org.itenas.is.crudproject.dbconfig;

import java.sql.Connection;

public class TestConnectionDb {
    public static void main(String[] args) {
        // Create DatabaseConnection instance
        ConnectionManager connMan = new ConnectionManager();

         //Connect to the database
        Connection conn = connMan.connectDb();

        // Disconnect from the database
        connMan.disconnectDb(conn);
    }
}