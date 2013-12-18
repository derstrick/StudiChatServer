package main.java.de.studichat.chatserver.dao;

import main.java.de.studichat.chatserver.entity.Room;


public interface RoomDAO {

    public void add(Room room);

    public void delete(int roomId);

    public void delete(String roomName);

    public void displayAllRooms();

    public void loadAllRooms();
}
