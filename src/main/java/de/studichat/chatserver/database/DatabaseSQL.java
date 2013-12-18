package main.java.de.studichat.chatserver.database;


import main.java.de.studichat.chatserver.Config;

import java.sql.*;

public class DatabaseSQL {

    private String Username = Config.DATABASE_LOGIN;
    private String Password = Config.DATABASE_PASSWORD;
    private String Driver = Config.DATABASE_DRIVER;
    private String URL = Config.DATABASE_URL;
    private Connection connection;

    // Die in der Klasse genutzten Werte werden genommen.

    public DatabaseSQL() {
        this.Connect();
    }

    // Speicher wieder frei geben

    public void Close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (Exception e) {
            }
        }
    }

    // Connect registriert den JDBC Treiber und versucht eine Verbindung
    // herzustellen. Sollte dies nicht m�glich sein, wird eine Exception
    // ausgel�st

    public void Connect() {
        try {
            Class.forName(this.Driver);
            this.connection = DriverManager.getConnection(this.URL,
                    this.Username, this.Password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Connecting with User:" + Username
                    + " and Password:" + Password);
        }
    }

    public boolean isConnected() {
        try {
            ResultSet rs = this.returnQuery("SELECT 1;");
            if (rs == null) {
                return false;
            }
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    // Sendet ein Query und erwartet eine R�ckgabe in Form eines ResultSet

    public ResultSet returnQuery(String query) {
        try {
            Statement stmt = this.connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        }
    }

    // F�hrt das query aus, erwartet aber keine R�ckantwort des Servers.

    public boolean runQuery(String query) {
        try {
            Statement stmt = this.connection.createStatement();
            return stmt.execute(query);
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }

}