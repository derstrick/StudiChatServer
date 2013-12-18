package main.java.de.studichat.chatserver.network.clientpackets;

import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.Statuscode;
import main.java.de.studichat.chatserver.network.Streamable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class RequestLoginMessage extends Message implements Streamable{

    private String username;
    private String password;

    public RequestLoginMessage() { super(Statuscode.LOGIN); }

    public RequestLoginMessage(String username, String password) {
        super(Statuscode.LOGIN);
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(username);
        out.writeUTF(password);
    }

    @Override
    public void read(DataInput in) throws IOException {
        username = in.readUTF();
        password = in.readUTF();
    }
}