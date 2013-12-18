package main.java.de.studichat.chatserver.network;


import main.java.de.studichat.chatserver.network.clientpackets.RequestForgotPasswordMessage;
import main.java.de.studichat.chatserver.network.clientpackets.RequestLoginMessage;
import main.java.de.studichat.chatserver.network.clientpackets.RequestRegisterMessage;
import main.java.de.studichat.chatserver.network.serverpackets.ResponseForgotPasswordMessage;
import main.java.de.studichat.chatserver.network.serverpackets.ResponseLoginMessage;
import main.java.de.studichat.chatserver.network.serverpackets.ResponseRegisterMessage;
import main.java.de.studichat.chatserver.network.serverpackets.UnknownMessage;

public enum Statuscode implements MessageFactory {
      UNKNOWN(UnknownMessage.class),
      LOGIN(RequestLoginMessage.class),
      LOGINRESPONE(ResponseLoginMessage.class),
      REGISTER(RequestRegisterMessage.class),
      REGISTERRESPONE(ResponseRegisterMessage.class),
      FORGOTPASSWORD(RequestForgotPasswordMessage.class),
      FORGOTPASSWORDRESPONSE(ResponseForgotPasswordMessage.class)
//    LOGOUT(...class),
//    REGISTER(...class),
//    FORGOTDATA(...class),
//    WHISPER(...class),
//    JOINROOM(...class),
//    LEAVEROOM(...class),
//    ROOMCHAT(...class),
//    CREATEROOM(...class),
//    DELETEROOM(...class),
//    KICK(...class),
//    BAN(...class),
//    DEBAN(...class),
//    DISCCONNECT(...class),
//    RESTART(...class),
//    CHECKNEWUSER(...class),
//    GETNEWUSERLIST(...class),
//    GETNEWUSERLISTEND(...class),
//    GETBANLIST(...class),
//    GETBANLISTEND(...class),
            ;

    private final Class<? extends Message> type;

    private Statuscode(final Class<? extends Message> type){

        this.type = type;
    }

    @Override
    public Message createMessage() throws Exception {

        return type.newInstance();
    }

    public static Statuscode byOrdinal(int ordinal) {

        for (Statuscode statuscode : values()) {
            if (statuscode.ordinal() == ordinal) {
                return statuscode;
            }
        }
        throw new IllegalArgumentException("illegal statuscode");
    }
}