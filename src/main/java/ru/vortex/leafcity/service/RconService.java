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
        String duration = "";
        if (product!= null){
            Rcon rcon = new Rcon(
                    remoteConsole.getIp(),
                    Integer.parseInt(remoteConsole.getLobbyPort()),
                    remoteConsole.getLobbyPass().getBytes()
            );
            if(product.getDuration().getDuration()>0){
                duration = " " + product.getDuration().getDuration() + "d";
            }
            if(!(product.getCommand() == null) && !(product.getCommand().trim().isEmpty())) {
                String command = product.getCommand().replace("{username}", username) + duration;
                rcon.command(command);
            }
            //sendNotify(username, product.getNotify().replace("{amount}", String.valueOf(product.getRealPrice())));
        }
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
