package main.java.de.studichat.chatserver.network.packetcommands;


import main.java.de.studichat.chatserver.dao.UserDAO;
import main.java.de.studichat.chatserver.dao.impl.UserSqlDAO;
import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.PacketCommand;
import main.java.de.studichat.chatserver.network.clientpackets.RequestForgotPasswordMessage;
import main.java.de.studichat.chatserver.network.serverpackets.ResponseForgotPasswordMessage;
import main.java.de.studichat.chatserver.util.Mail;
import main.java.de.studichat.chatserver.util.Password;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ForgotPassworPacketCommand implements PacketCommand<RequestForgotPasswordMessage> {

    Log log  = LogFactory.getLog(ForgotPassworPacketCommand.class);

    @Override
    public Message execute(RequestForgotPasswordMessage message) throws Exception {

        String username = message.getUsername();
        String email = message.getEmail();

        UserDAO user = new UserSqlDAO();

        boolean userexist = user.findUsernameAndEmail(username, email);

        if(userexist){

            String generatePassword = Password.generatePassword(6);
            String hashPassword = Password.hashPassword(generatePassword);
            user.updatePassword(username, hashPassword);
            Mail.sendMail(email, "Dein neues Passwort bei Studi Chat", "Hallo Dein Username ist: " + username + " und dein Passwort: " + generatePassword);
            log.info("Passwort Vergessen Email wurde an "+username+" mit der Email "+email+" versendet!");

            return new ResponseForgotPasswordMessage("PASSWORDRESET_OKAY");

        }

        return new ResponseForgotPasswordMessage("PASSWORDRESET_FAIL");
    }


}