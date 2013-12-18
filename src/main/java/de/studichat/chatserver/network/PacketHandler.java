package main.java.de.studichat.chatserver.network;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PacketHandler {

    public static void sendMessage(Message message, DataOutput out) throws IOException {
        Statuscode statuscode = message.getStatuscode();
        out.writeInt(statuscode.ordinal());
        message.write(out);
    }

    public static Message readMessage(DataInput in) throws Exception {
        int ordinal = in.readInt();
        Statuscode statuscode = Statuscode.byOrdinal(ordinal);
        Message message = statuscode.createMessage();
        message.read(in);
        return message;
    }


}
