package main.java.de.studichat.chatserver.network.serverpackets;


import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.Statuscode;
import main.java.de.studichat.chatserver.network.Streamable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ResponseRegisterMessage extends Message implements Streamable {

    /*
    * @param response Kann folgende Nachrichten enthalten:
    *
    * REGISTER_OKAY  - > Alles Okay mit den Daten
    * REGISTER_FAIL_EMAIL- > Email Adresse existiert bereits
    * REGISTER_FAIL_MATRIKELNUMMER - > Matrikelnummer existiert bereits
    *
    */

    private String response;

    public ResponseRegisterMessage() { super(Statuscode.REGISTERRESPONE); }

    public ResponseRegisterMessage(String response) {
        super(Statuscode.REGISTERRESPONE);
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
