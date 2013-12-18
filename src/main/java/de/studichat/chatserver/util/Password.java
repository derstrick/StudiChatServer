package main.java.de.studichat.chatserver.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Password {

    public static String generatePassword(int passwordLength) throws Exception {

        StringBuffer buffer = new StringBuffer();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        int charactersLength = characters.length();

        for (int i = 0; i < passwordLength; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    }



    public static String hashPassword(String password){

        password = DigestUtils.md5Hex(password);
        return password;

    }

}
