package ru.vortex.leafcity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vortex.leafcity.model.shop.Product;
import ru.vortex.leafcity.utils.Rcon;
import ru.vortex.leafcity.utils.RemoteConsole;
import ru.vortex.leafcity.utils.ex.AuthenticationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class RconService {

    @Autowired
    RemoteConsole remoteConsole;

    public void sendCommand (String username, Product product) throws IOException, AuthenticationException {
        String result;
        String duration = "";
        Rcon rcon = new Rcon(
                remoteConsole.getIp(),
                Integer.parseInt(remoteConsole.getLobbyPort()),
                remoteConsole.getLobbyPass().getBytes()
        );
        if(product.getDuration().getDuration()>0){
            duration = " " + product.getDuration().getDuration() + "d";
        }
        String command = product.getCommand().replace("{username}", username) + duration;
        result = rcon.command(command);
        sendNotify(username, product.getNotify());

    }

    private void sendNotify (String username,String notifyMessage) throws IOException, AuthenticationException {

        String notify = ("lctools:shop " + username + " " + notifyMessage);

        List<Rcon> rconList = new ArrayList<>();
        rconList.add(new Rcon(remoteConsole.getIp(),Integer.parseInt(remoteConsole.getLobbyPort()),remoteConsole.getLobbyPass().getBytes()
        ));
        rconList.add(new Rcon(remoteConsole.getIp(),Integer.parseInt(remoteConsole.getResourcesPort()),remoteConsole.getResourcesPass().getBytes()
        ));
        rconList.add(new Rcon(remoteConsole.getIp(),Integer.parseInt(remoteConsole.getSurvivalPort()),remoteConsole.getSurvivalPass().getBytes()
        ));
        rconList.add(new Rcon(remoteConsole.getIp(),Integer.parseInt(remoteConsole.getFarmsPort()),remoteConsole.getFarmsPass().getBytes()
        ));

        for(var rcon:rconList) {
            rcon.command(notify);
        }
    }
}
