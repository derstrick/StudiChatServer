package main.java.de.studichat.chatserver.network.clientpackets;

import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.Statuscode;
import main.java.de.studichat.chatserver.network.Streamable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class RequestForgotPasswordMessage extends Message implements Streamable {

    private String username;
    private String email;

    public RequestForgotPasswordMessage() { super(Statuscode.FORGOTPASSWORD); }

    public RequestForgotPasswordMessage(String username, String password) {
        super(Statuscode.FORGOTPASSWORD);
        this.username = username;
        this.email = password;
    }

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(username);
        out.writeUTF(email);
    }

    @Override
    public void read(DataInput in) throws IOException {
        username = in.readUTF();
        email = in.readUTF();
    }
}
