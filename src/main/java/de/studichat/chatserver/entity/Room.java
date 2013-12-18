package main.java.de.studichat.chatserver.entity;

import main.java.de.studichat.chatserver.list.OnlineUserList;

import java.util.ListIterator;

public class Room {
    private int roomID;
    private String roomName;
    private OnlineUserList onlineUserList;

    public Room(int roomID, String roomName){
        this.roomID = roomID;
        this.roomName = roomName;
    }

    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    public String getRoomName(){
        return this.roomName;
    }

    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public int getRoomID(){
        return this.roomID;
    }


    public int getUserinRoom(){
        return onlineUserList.size();
    }

    public void addUserToRoom(User u){
        onlineUserList.add(u);
    }

    public void removeUserFromRoom(User u){
        onlineUserList.remove(u);
    }

    public User findUser(String username){
        return onlineUserList.findUser(username);
    }

    public User findUser(int id){
        return onlineUserList.findUser(id);
    }

    public ListIterator<User> onlineUserListIterator(){
        return onlineUserList.listIterator();
    }
}
