package main.java.de.studichat.chatserver.network;



public abstract class Message implements Streamable {

    private Statuscode statuscode;

    public Message() {}

    public Message(Statuscode statuscode){

        this.statuscode = statuscode;
    }

    public void setStatuscode(Statuscode statuscode){

        this.statuscode = statuscode;
    }
    public Statuscode getStatuscode(){

        return statuscode;
    }

}