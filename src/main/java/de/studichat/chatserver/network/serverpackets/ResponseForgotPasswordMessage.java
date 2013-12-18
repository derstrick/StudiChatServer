package main.java.de.studichat.chatserver.network.serverpackets;

import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.Statuscode;
import main.java.de.studichat.chatserver.network.Streamable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class ResponseForgotPasswordMessage extends Message implements Streamable {

    /*
    * @param response Kann folgende Nachrichten enthalten:
    *
    * PASSWORDRESET_OKAY  - > Alles Okay mit den Daten
    * PASSWORDRESET_FAIL -> Username stimmt mit E-mail nicht überein bzw anderes herum
    *
    */

    private String response;

    public ResponseForgotPasswordMessage() { super(Statuscode.FORGOTPASSWORDRESPONSE); }

    public ResponseForgotPasswordMessage(String response) {
        super(Statuscode.FORGOTPASSWORDRESPONSE);
        this.response = response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(response);
    }

    @Override
    public void read(DataInput in) throws IOException {
        response = in.readUTF();
    }

}

