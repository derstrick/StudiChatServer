package main.java.de.studichat.chatserver.dao;



public interface UserDAO {

    public boolean findMatrikelnummer(int matrikelnummer);

    public boolean findEmailAddress(String emailadress);

    public boolean doUserAuthentication(String username, String password);

    public boolean findUsername(String username);

    public void updatePassword(String username, String password);

    public void add(int position, int anrede, String titel, String vorname, String nachname, String emailAddress, int matrikelnummer, String password , String username);

    public boolean findUsernameAndEmail(String username, String email);

    public boolean hasNoBan(String username);

    boolean isUserOffline(String username);

    boolean isActive(String username);
}
