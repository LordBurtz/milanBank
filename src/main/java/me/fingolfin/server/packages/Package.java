package me.fingolfin.server.packages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Package {
    private String ip;

    public Package() {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            ip = in.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }
}
