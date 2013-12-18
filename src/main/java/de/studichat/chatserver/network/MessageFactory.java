package main.java.de.studichat.chatserver.network;

public interface MessageFactory {

    Message createMessage() throws Exception;

}