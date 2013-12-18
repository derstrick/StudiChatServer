package main.java.de.studichat.chatserver.network.serverpackets;


import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.Statuscode;
import main.java.de.studichat.chatserver.network.Streamable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UnknownMessage extends Message implements Streamable {

    /*
    * @param unknown Es wurden eine Unbekannte Nachricht geschickt
    *
    */

    private String unknown;

    public UnknownMessage() { super(Statuscode.LOGINRESPONE); }

    public UnknownMessage(String response) {
        super(Statuscode.LOGINRESPONE);
        this.unknown = response;
    }

    public void setResponse(String unknown) {
        this.unknown = unknown;
    }

    public String getResponse() {
        return unknown;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(unknown);
    }

    @Override
    public void read(DataInput in) throws IOException {
        unknown = in.readUTF();
    }



}