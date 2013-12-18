package main.java.de.studichat.chatserver.dao.impl;


import main.java.de.studichat.chatserver.dao.RoomDAO;
import main.java.de.studichat.chatserver.database.ConnectionFactory;
import main.java.de.studichat.chatserver.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoomSqlDAO implements RoomDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    // SQL Commands:
    String SQL_ADD_ROOM = "";
    String SQL_DELETE_ROOM_BY_ID = "";
    String SQL_DELETE_ROOM_BY_NAME ="";
    String SQL_DISPLAY_ALL_ROOMS = "";


    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }


    @Override
    public void add(Room room) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_ADD_ROOM);
            ptmt.setInt(1, room.getRoomID());
            ptmt.setString(2, room.getRoomName());
            ptmt.executeUpdate();
            System.out.println("Raum wurde zur Datenbank hinzugefügt!");
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void delete(int roomId) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_DELETE_ROOM_BY_ID);
            ptmt.setInt(1, roomId);
            ptmt.executeUpdate();
            System.out.println("Raum mit der ID" + roomId + "wurde gelöscht!");
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void delete(String roomName) {
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_DELETE_ROOM_BY_NAME);
            ptmt.setString(1, roomName);
            ptmt.executeUpdate();
            System.out.println("Raum [" + roomName + "] wurde gelöscht!");
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void displayAllRooms() {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_DISPLAY_ALL_ROOMS);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()){

                System.out.println("Raum ID[" + resultSet.getInt("roomID") + "] Chat Raum Name: [" + resultSet.getString("roomName"));

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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void loadAllRooms() {
        try{
            connection = getConnection();
            ptmt = connection.prepareStatement(SQL_DISPLAY_ALL_ROOMS);
            resultSet = ptmt.executeQuery();
            System.out.println("Lade Chat Räume:");
            while (resultSet.next()){

                System.out.println("Raum ID[" + resultSet.getInt("roomID") + "] Chat Raum Name: [" + resultSet.getString("roomName"));

                Room r = new Room(resultSet.getInt("roomID"), resultSet.getString("roomName"));
                //ChatServer.roomList.add(r);

            }
            System.out.println("Alle Räume wurden geladen!");
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
