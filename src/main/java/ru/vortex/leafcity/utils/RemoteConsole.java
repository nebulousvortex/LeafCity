package ru.vortex.leafcity.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.vortex.leafcity.utils.ex.AuthenticationException;

import java.io.IOException;

@Component
public class RemoteConsole {
    private String ip;
    private String lobbyPort;
    private String survivalPort;
    private String farmsPort;
    private String resourcesPort;
    private String lobbyPass;
    private String survivalPass;
    private String farmsPass;
    private String resourcesPass;

    public RemoteConsole( @Value("${rcon.ip}") String ip,
                        @Value("${rcon.lobby.port}") String lobbyPort,
                        @Value("${rcon.survival.port}") String survivalPort,
                        @Value("${rcon.farms.port}") String farmsPort,
                        @Value("${rcon.resources.port}") String resourcesPort,
                        @Value("${rcon.lobby.pass}") String lobbyPass,
                        @Value("${rcon.survival.pass}") String survivalPass,
                        @Value("${rcon.farms.pass}") String farmsPass,
                        @Value("${rcon.resources.pass}") String resourcesPass) throws AuthenticationException, IOException {        this.ip = ip;
        this.lobbyPort = lobbyPort;
        this.survivalPort = survivalPort;
        this.farmsPort = farmsPort;
        this.resourcesPort = resourcesPort;
        this.lobbyPass = lobbyPass;
        this.survivalPass = survivalPass;
        this.farmsPass = farmsPass;
        this.resourcesPass = resourcesPass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLobbyPort() {
        return lobbyPort;
    }

    public void setLobbyPort(String lobbyPort) {
        this.lobbyPort = lobbyPort;
    }

    public String getSurvivalPort() {
        return survivalPort;
    }

    public void setSurvivalPort(String survivalPort) {
        this.survivalPort = survivalPort;
    }

    public String getFarmsPort() {
        return farmsPort;
    }

    public void setFarmsPort(String farmsPort) {
        this.farmsPort = farmsPort;
    }

    public String getResourcesPort() {
        return resourcesPort;
    }

    public void setResourcesPort(String resourcesPort) {
        this.resourcesPort = resourcesPort;
    }

    public String getLobbyPass() {
        return lobbyPass;
    }

    public void setLobbyPass(String lobbyPass) {
        this.lobbyPass = lobbyPass;
    }

    public String getSurvivalPass() {
        return survivalPass;
    }

    public void setSurvivalPass(String survivalPass) {
        this.survivalPass = survivalPass;
    }

    public String getFarmsPass() {
        return farmsPass;
    }

    public void setFarmsPass(String farmsPass) {
        this.farmsPass = farmsPass;
    }

    public String getResourcesPass() {
        return resourcesPass;
    }

    public void setResourcesPass(String resourcesPass) {
        this.resourcesPass = resourcesPass;
    }
}
