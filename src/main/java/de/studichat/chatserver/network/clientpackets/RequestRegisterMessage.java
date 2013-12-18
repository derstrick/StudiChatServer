package main.java.de.studichat.chatserver.network.clientpackets;

import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.Statuscode;
import main.java.de.studichat.chatserver.network.Streamable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class RequestRegisterMessage extends Message implements Streamable {

    private int position;
    private int anrede;
    private String titel;
    private String vorname;
    private String nachname;
    private String email;
    private int matrikelnummer;

    public RequestRegisterMessage() { super(Statuscode.REGISTER); }

    public RequestRegisterMessage(int position, int anrede, String titel, String vorname, String nachname, String email, int matrikelnummer) {
        super(Statuscode.REGISTER);
        this.position = position;
        this.anrede = anrede;
        this.titel = titel;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.matrikelnummer = matrikelnummer;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAnrede() {
        return anrede;
    }

    public void setAnrede(int anrede) {
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

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(position);
        out.writeInt(anrede);
        out.writeUTF(titel);
        out.writeUTF(vorname);
        out.writeUTF(nachname);
        out.writeUTF(email);
        out.writeInt(matrikelnummer);
    }

    @Override
    public void read(DataInput in) throws IOException {
        position = in.readInt();
        anrede = in.readInt();
        titel = in.readUTF();
        vorname = in.readUTF();
        nachname = in.readUTF();
        email = in.readUTF();
        matrikelnummer = in.readInt();
    }
}
