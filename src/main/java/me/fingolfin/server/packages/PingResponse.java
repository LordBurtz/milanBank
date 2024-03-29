package me.fingolfin.server.packages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class PingResponse extends Package {
    long time = System.currentTimeMillis() / 1000;
    String ipv4;
    String ipv6;
    String ip;

    public PingResponse() {
        super();
        try {
            ipv4 = Inet4Address.getLocalHost().getHostAddress();
            ipv6 = Inet6Address.getLocalHost().getHostAddress();

            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            ip = in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //silly, but I want to  keep the try and catch statement for further changes
    @Override
    public String getIp() {
        return ip;
    }
}
