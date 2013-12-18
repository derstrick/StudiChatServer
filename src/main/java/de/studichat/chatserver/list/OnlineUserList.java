package main.java.de.studichat.chatserver.list;


import main.java.de.studichat.chatserver.entity.User;

import java.util.ArrayList;
import java.util.ListIterator;

public class OnlineUserList {

    private ArrayList<User> list;

    public OnlineUserList(){
        list = new ArrayList<>();
    }

    public boolean add(User u){
        return list.add(u);
    }

    public boolean remove(User u){
        return list.remove(u);
    }

    public int size(){
        return list.size();
    }

    public User findUser(int id){
        for (ListIterator<User> u = list.listIterator(); u.hasNext(); )
            if (u.next().getId() == id)
                return u.previous();

        return null;
    }

    public User findUser(String username){
        for (ListIterator<User> u = list.listIterator(); u.hasNext(); )
            if (u.next().getUsername().equalsIgnoreCase(username))
                return u.previous();

        return null;
    }

    public ListIterator<User> listIterator()
    {
        return list.listIterator();
    }
}
