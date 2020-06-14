package com.zomboplugin.data.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDatabase {
	/**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public static void createNewDatabase(String fileName) {
    	String path = "plugins/ZombocalypseConfig/db/";

    	File file = new File(path);
    	if(!file.exists()) {
    		file.mkdirs();
            System.out.println("A folder for a new database has been created.");
    	}
        Connection conn = null;

        try {
            String url = "jdbc:sqlite:" + path + fileName;
        	conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Connection to SQLite has been established");
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        
    }

}
