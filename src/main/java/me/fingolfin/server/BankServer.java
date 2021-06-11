package me.fingolfin.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import me.fingolfin.server.packages.PingRequest;
import me.fingolfin.server.packages.PingResponse;

import java.io.IOException;

public class BankServer {

    public static final int PORT = 8117;

    private Server server;
    private Kryo kryo;

    public BankServer() {
        server = new Server();
        server.start();

        //TODO: go further with https://github.com/ww6015132/kryonet#running-a-server

        try {
            server.bind(PORT);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        server.addListener(new ServerListener());

        kryo = server.getKryo();
        kryo.register(PingRequest.class);
        kryo.register(PingResponse.class);
    }

    public static void main(String[] args) {
        new BankServer();
    }
}
