package ru.vortex.physics.service;

import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vortex.physics.model.shop.Product;
import ru.vortex.physics.utils.RemoteConsole;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
@Service
public class RconService {

    @Autowired
    RemoteConsole remoteConsole;

    public void sendCommand (String username, Product product) throws IOException, AuthenticationException {
        String result;

        Rcon rcon = new Rcon(
                remoteConsole.getIp(),
                Integer.parseInt(remoteConsole.getLobbyPort()),
                remoteConsole.getLobbyPass().getBytes()
        );
        String command = product.getCommand().replace("{username}", username) + " " + product.getDuration().getDuration() + "d";
        result = rcon.command("command");
        sendNotify(username, product.getNotify());

    }

    public void sendNotify (String username,String notifyMessage) throws IOException, AuthenticationException {

        String notify = ("lctools:shop " + username + " " + notifyMessage);

        List<Rcon> rconList = new ArrayList<>();
        rconList.add(new Rcon(
                remoteConsole.getIp(),
                Integer.parseInt(remoteConsole.getLobbyPort()),
                remoteConsole.getLobbyPass().getBytes()
        ));
        rconList.add(new Rcon(
                remoteConsole.getIp(),
                Integer.parseInt(remoteConsole.getResourcesPort()),
                remoteConsole.getResourcesPass().getBytes()
        ));
        rconList.add(new Rcon(
                remoteConsole.getIp(),
                Integer.parseInt(remoteConsole.getSurvivalPort()),
                remoteConsole.getSurvivalPass().getBytes()
        ));
        rconList.add(new Rcon(
                remoteConsole.getIp(),
                Integer.parseInt(remoteConsole.getFarmsPort()),
                remoteConsole.getFarmsPass().getBytes()
        ));

        for(var rcon:rconList) {
            rcon.command(notify);
        }
    }
}
