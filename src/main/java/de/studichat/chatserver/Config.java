package main.java.de.studichat.chatserver;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public final class Config {

    private static final Log _log = LogFactory.getLog(Config.class);



    /** Enable/disable Development Version */
    public static boolean DEVELOPER;

    // Chat Server Informations
    /** Chat Server Port */
    public static int CHATSERVER_PORT;
    /** Max Chat Rooms **/
    public static int MAX_CHATROOMS;
    /** Max User online **/
    public static int MAX_USER;

    /** Word Filter */
    public static boolean WORD_FILTER;

    // Access to database
    /** Driver to access to database */
    public static String DATABASE_DRIVER;
    /** Path to access to database */
    public static String DATABASE_URL;
    /** Database login */
    public static String DATABASE_LOGIN;
    /** Database password */
    public static String DATABASE_PASSWORD;


    // Email
    public static String EMAIL_USERNAME;
    public static String EMAIL_PASSWORD;
    public static boolean EMAIL_TLS;
    public static boolean EMAIL_AUTH;
    public static String EMAIL_HOSTNAME;
    public static int EMAIL_PORT;


    /** Configuration files */
    /** Properties file for chat server configurations */
    public static final String  SERVER_CONFIGURATION_FILE    = "./config/server.properties";


    public static void load(){

        _log.info("Lade Config Datei:");

        try {

            Properties serverSettings    = new Properties();
            InputStream is               = new FileInputStream(new File(SERVER_CONFIGURATION_FILE));
            serverSettings.load(is);
            is.close();

            DEVELOPER    			   = Boolean.parseBoolean(serverSettings.getProperty("Developer", "false"));

            // Chat Server
            CHATSERVER_PORT            = Integer.parseInt(serverSettings.getProperty("ChatserverPort", "2106"));
            MAX_CHATROOMS            = Integer.parseInt(serverSettings.getProperty("MaxChatRooms", "1337"));
            MAX_USER            = Integer.parseInt(serverSettings.getProperty("MaxUser", "1337"));
            WORD_FILTER    			   = Boolean.parseBoolean(serverSettings.getProperty("Wordfilter", "true"));


            // Database
            DATABASE_DRIVER          = serverSettings.getProperty("Driver", "com.mysql.jdbc.Driver");
            DATABASE_URL             = serverSettings.getProperty("URL", "jdbc:mysql://localhost/db_chat");
            DATABASE_LOGIN           = serverSettings.getProperty("Login", "root");
            DATABASE_PASSWORD        = serverSettings.getProperty("Password", "");

            // Email
            EMAIL_HOSTNAME			 = serverSettings.getProperty("EmailHostname","127.0.0.1");
            EMAIL_USERNAME			 = serverSettings.getProperty("EmailUsername","");
            EMAIL_PASSWORD			 = serverSettings.getProperty("EmailPassword","");
            EMAIL_AUTH				 = Boolean.parseBoolean(serverSettings.getProperty("EmailAuth", "true"));
            EMAIL_TLS    			 = Boolean.parseBoolean(serverSettings.getProperty("EmailTLS", "true"));
            EMAIL_PORT               = Integer.parseInt(serverSettings.getProperty("EmailPort", "587"));


        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Error("Fehler beim laden der "+SERVER_CONFIGURATION_FILE+" Datei.");
        }

    }

    public static void check(){

        if(DEVELOPER){
            _log.warn("Chat Server läuft unter Developer Mode!");
            _log.info("Config entählt keine Fehler!");
        }

        if(CHATSERVER_PORT <= 0){
            _log.warn("Chat Server Port darf nicht 0 sein!");
        }

        if(MAX_CHATROOMS <= 0){
            _log.warn("Max Chatraeume darf nicht 0 sein!");
        }

        if(MAX_USER <= 0){
            _log.warn("Max User darf nicht 0 sein!");
        }

        else {

            _log.info("Config entählt keine Fehler!");

        }

        done();
    }

    public static void done(){

        _log.info("Chat Server Port: " + CHATSERVER_PORT );
        // TODO etc

    }


    // it has no instancies
    protected Config() {}




}