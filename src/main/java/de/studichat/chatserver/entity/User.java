package main.java.de.studichat.chatserver.entity;


public class User {

    private int id;
    private String username;
    private String password;

    private int accessLevel;
    private int isOnline;
    private int isBanned;

    private Position position;
    private Anrede anrede;
    private String titel;
    private String vorname;
    private String nachname;
    private String email;
    private int matrikelnummer;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getOnline() {
        return isOnline;
    }

    public void setOnline(int online) {
        isOnline = online;
    }

    public int getBanned() {
        return isBanned;
    }

    public void setBanned(int banned) {
        isBanned = banned;
    }



    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Anrede getAnrede() {
        return anrede;
    }

    public void setAnrede(Anrede anrede) {
        this.anrede = anrede;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatrikelnummer() {
        return matrikelnummer;
    }

    public void setMatrikelnummer(int matrikelnummer) {
        this.matrikelnummer = matrikelnummer;
    }

    enum Position{

        Professor,
        Student

    }

    enum Anrede{

        Frau,
        Herr

    }
}
