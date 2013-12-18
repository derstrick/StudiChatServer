package main.java.de.studichat.chatserver.dao.impl;

import main.java.de.studichat.chatserver.dao.UserDAO;
import main.java.de.studichat.chatserver.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserSqlDAO implements UserDAO {


    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    // SQL Commands:
    private static final String SQL_FIND_MATRIKELNUMMER = "";
    private static final String SQL_FIND_USERNAME = "";
    private static final String SQL_FIND_EMAIL = "";
    private static final String SQL_USER_AUTH = "SELECT * FROM test_user WHERE username=? and password=?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE test_user SET password=? WHERE username=?";
    private static final String SQL_INSTERT_USER = "INSERT INTO `test_user`( `position`, `anrede`, `titel`, `vorname`, `nachname`, `emailAddress`, `matrikelnummer`, `password`, `username`, `offline`, `accessLevel`, `isBanned`) VALUES (?,?,?,?,?,?,?,?,?,0,-1,0)";
    private static final String SQL_IS_USER_OFFLINE = "";
    private static final String SQL_IS_USER_BANNED = "";
    private static final String SQL_FIND_USERNAME_AND_EMAIL = "";
    private static final String SQL_USER_IS_ACTIVED = "";



    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }


    @Override
    public boolean findMatrikelnummer(int matrikelnummer) {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_FIND_MATRIKELNUMMER);
            resultSet = ptmt.executeQuery();
            ptmt.setInt(1, matrikelnummer);
            if (resultSet.first()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean findEmailAddress(String emailadress) {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_FIND_EMAIL);
            resultSet = ptmt.executeQuery();
            ptmt.setString(1, emailadress);
            if (resultSet.first()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean doUserAuthentication(String username, String password) {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_USER_AUTH);
            resultSet = ptmt.executeQuery();
            ptmt.setString(1, username);
            ptmt.setString(2, password);
            if (resultSet.first()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean findUsername(String username) {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_FIND_USERNAME);
            resultSet = ptmt.executeQuery();
            ptmt.setString(1, username);
            if (resultSet.first()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public void updatePassword(String username, String password) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_UPDATE_PASSWORD);
            ptmt.setString(1, username);
            ptmt.setString(2, password);
            ptmt.executeUpdate();
            System.out.println("Neues Passwort von Benutzer: "+username+" wurde in die Datenbank gespeichert worden!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(int position, int anrede, String titel, String vorname, String nachname, String emailAddress, int matrikelnummer, String password, String username) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_INSTERT_USER);
            ptmt.setInt(1, position);
            ptmt.setInt(2, anrede);
            ptmt.setString(3, titel);
            ptmt.setString(4, vorname);
            ptmt.setString(5, nachname);
            ptmt.setString(6, emailAddress);
            ptmt.setInt(7, matrikelnummer);
            ptmt.setString(8,password);
            ptmt.setString(9,username);
            ptmt.executeUpdate();
            System.out.println("Neuer Benutzer: "+username+" wurde in der Datenbank angelegt!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean findUsernameAndEmail(String username, String email) {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_FIND_USERNAME_AND_EMAIL);
            resultSet = ptmt.executeQuery();
            ptmt.setString(1, username);
            ptmt.setString(2, email);
            if (resultSet.first()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean hasNoBan(String username) {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_IS_USER_BANNED);
            resultSet = ptmt.executeQuery();
            ptmt.setString(1, username);
            ptmt.setInt(2, 0);
            if (resultSet.first()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean isUserOffline(String username) {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_IS_USER_OFFLINE);
            resultSet = ptmt.executeQuery();
            ptmt.setString(1, username);
            ptmt.setInt(2, 0);
            if (resultSet.first()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean isActive(String username) {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_USER_IS_ACTIVED);
            resultSet = ptmt.executeQuery();
            ptmt.setString(1, username);
            ptmt.setInt(2, 0);
            if (resultSet.first()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}
