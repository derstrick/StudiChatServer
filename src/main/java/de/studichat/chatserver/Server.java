package main.java.de.studichat.chatserver;

import main.java.de.studichat.chatserver.network.Message;
import main.java.de.studichat.chatserver.network.PacketCommand;
import main.java.de.studichat.chatserver.network.PacketHandler;
import main.java.de.studichat.chatserver.network.Statuscode;
import main.java.de.studichat.chatserver.network.packetcommands.ForgotPassworPacketCommand;
import main.java.de.studichat.chatserver.network.packetcommands.LoginPacketCommand;
import main.java.de.studichat.chatserver.network.packetcommands.RegisterPacketCommand;
import main.java.de.studichat.chatserver.network.serverpackets.UnknownMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    // Better faster EnumMap ;)
    private final Map<Statuscode, PacketCommand<? extends Message>> handlers = new EnumMap(Statuscode.class);

    // Wie viel Clients der Server gleichzeigt abarbeiten kann per "Auth" Phase
    private final ExecutorService workerPool = Executors.newFixedThreadPool(5);
    private final ServerSocket serverSocket;
    private final Thread acceptThread;
    private volatile boolean shutdown = false;

    public Server(int port) throws Exception {
        // Server kann in dieser Phase nur mit Login, Regestrieren und Passwordvergessen Requests was anfangen.
        handlers.put(Statuscode.LOGIN, new LoginPacketCommand());
        handlers.put(Statuscode.REGISTER, new RegisterPacketCommand());
        handlers.put(Statuscode.FORGOTPASSWORD, new ForgotPassworPacketCommand());

        serverSocket = new ServerSocket(port);
        acceptThread = new Thread(new AcceptTask());
    }

    public void shutdown() {
        shutdown = true;
        acceptThread.interrupt();
    }

    private void handleClientRequest(Socket socket) {
        workerPool.execute(new ClientRequest(socket));
    }

    private class AcceptTask implements Runnable {
        public void run() {
            while (!shutdown) {
                try {
                    Socket socket = serverSocket.accept();
                    handleClientRequest(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            workerPool.shutdown();
        }
    }

    private class ClientRequest<T extends Message> implements Runnable {
        private final Socket socket;

        private ClientRequest(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                Message message;
                try (DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()))) {
                    message = PacketHandler.readMessage(in);
                }
                try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {
                    PacketCommand command = handlers.get(message.getStatuscode());
                    Message response = new UnknownMessage("Unbekannte Nachricht");
                    if (command != null) {
                        response = command.execute(message);
                    }

                    PacketHandler.sendMessage(message, out);
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}