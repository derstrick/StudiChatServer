package main.java.de.studichat.chatserver.network.packetcommands;

import main.java.de.studichat.chatserver.dao.UserDAO;
import main.java.de.studichat.chatserver.dao.impl.UserSqlDAO;
import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.PacketCommand;
import main.java.de.studichat.chatserver.network.clientpackets.RequestRegisterMessage;
import main.java.de.studichat.chatserver.network.serverpackets.ResponseRegisterMessage;
import main.java.de.studichat.chatserver.util.Mail;
import main.java.de.studichat.chatserver.util.Password;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RegisterPacketCommand  implements PacketCommand<RequestRegisterMessage> {

    Log log  = LogFactory.getLog(RegisterPacketCommand.class);

    @Override
    public Message execute(RequestRegisterMessage message) throws Exception {

         int position       = message.getPosition();
         int anrede         = message.getAnrede();
         String titel       = message.getTitel();
         String vorname     = message.getVorname();
         String nachname    = message.getNachname();
         String email       = message.getEmail();
         int matrikelnummer = message.getMatrikelnummer();

         UserDAO user = new UserSqlDAO();

         boolean checkEmail = user.findEmailAddress(email);
         boolean checkMN    = user.findMatrikelnummer(matrikelnummer);

         //Make Username
         String newUsername = email;
         newUsername = newUsername.substring(0, newUsername.length() - 14);

         // Generate new Password
         String newPassword  = Password.generatePassword(6);
         String hashedPassword = Password.hashPassword(newPassword);

         if(checkEmail){

             //Ist Student
             if(position == 2){

                 if(checkMN){

                     user.add(position,anrede,titel,vorname,nachname,email,matrikelnummer,hashedPassword,newUsername);
                     sendEmail(newUsername,newPassword,email);
                     return new ResponseRegisterMessage("REGISTER_OKAY");

                 }

                 return new ResponseRegisterMessage("REGISTER_FAIL_MATRIKELNUMMER");

             }

             user.add(position,anrede,titel,vorname,nachname,email,matrikelnummer,hashedPassword,newUsername);
             sendEmail(newUsername,newPassword,email);
             return new ResponseRegisterMessage("REGISTER_OKAY");

         }

        return new ResponseRegisterMessage("REGISTER_FAIL_EMAIL");

    }

    private void sendEmail(String username,String password, String emailAddress){

        Mail.sendMail(emailAddress, "Registrierung bei Studi Chat", "Hallo Dein Username ist: " + username + " und dein Passwort: " + password);
        log.info("Registrierungs Email wurde versendet!");

    }


}
