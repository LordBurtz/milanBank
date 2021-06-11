package me.fingolfin.server;

import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class BankServer {

    public static final int PORT = 8117;

    private Server server;

    protected String useless = "sldkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
            "sddddddddddddddddddddddddddddddddddddddddddd" +
            "sdddddddddddddddddddddddddggggggggggggggggggsssss" +
            "sddddddddddddddddddddddddddddddddddddddddddddddd" +
            "sdddddddddddddaaaaaaaaaaaaaaaaaaaaaaavbvvvvvvvvvvvvvvvv" +
            "saddddddddddddddddddddddddddddddddddddddddddddddddddd";

    public BankServer() {
        Server server = new Server();
        server.start();

        //TODO: go further with https://github.com/ww6015132/kryonet#running-a-server

        try {
            server.bind(PORT);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
