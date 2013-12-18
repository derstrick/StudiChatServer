package main.java.de.studichat.chatserver.network.packetcommands;

import main.java.de.studichat.chatserver.dao.UserDAO;
import main.java.de.studichat.chatserver.dao.impl.UserSqlDAO;
import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.PacketCommand;
import main.java.de.studichat.chatserver.network.clientpackets.RequestLoginMessage;
import main.java.de.studichat.chatserver.network.serverpackets.ResponseLoginMessage;

public class LoginPacketCommand implements PacketCommand<RequestLoginMessage> {

    @Override
    public Message execute(RequestLoginMessage message) {

        String username = message.getUsername();
        String password = message.getPassword();

        UserDAO user = new UserSqlDAO();

        boolean authenticated = user.doUserAuthentication(username, password);
        boolean isOffline = user.isUserOffline(username);
        boolean noBan = user.hasNoBan(username);
        boolean isActive = user.isActive(username);

        if (authenticated) {

            if(isOffline){

                if(isActive){

                    if(noBan){
                           return new ResponseLoginMessage("LOGINOKAY");
                    }

                    return new ResponseLoginMessage("LOGINFAIL_REASON_ISBANNED");

                }

                return new ResponseLoginMessage("LOGINFAIL_REASON_ISNOTACTIVE");


            }

            return new ResponseLoginMessage("LOGINFAIL_MULTI_LOGIN");


        }
        return new ResponseLoginMessage("LOGINFAIL_NOT_EXIST");
    }


}