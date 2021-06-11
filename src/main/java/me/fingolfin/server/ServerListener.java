package me.fingolfin.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerListener extends Listener {
    @Override
    public void connected(Connection connection) {
        System.out.println(" [Server] connected to " + connection.getRemoteAddressTCP());
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println(" [Server] disconnected from " + connection.getRemoteAddressTCP());
    }

    @Override
    public void received(Connection connection, Object object) {

    }
}
