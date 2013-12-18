package main.java.de.studichat.chatserver.network.serverpackets;

import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.Statuscode;
import main.java.de.studichat.chatserver.network.Streamable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ResponseLoginMessage extends Message implements Streamable {

    /*
    * @param response Kann folgende Nachrichten enthalten:
    *
    * LOGINOKAY  - > Alles Okay mit den Login Daten
    * LOGINFAIL_REASON_ISNOTACTIVE -> User ist noch nicht freigeschalten im Chat-System
    * LOGINFAIL_REASON_ISBANNED -> User ist in dem Chat-System gebannt
    * LOGINFAIL_MULTI_LOGIN -> User ist bereits eingeglogt
    * LOGINFAIL_NOT_EXIST -> User exestiert nicht
    *
    */

    private String response;

    public ResponseLoginMessage() { super(Statuscode.LOGINRESPONE); }

    public ResponseLoginMessage(String response) {
        super(Statuscode.LOGINRESPONE);
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

