package main.java.de.studichat.chatserver.network;

public interface PacketCommand<T extends Message> {

    Message execute(T message) throws Exception;


}