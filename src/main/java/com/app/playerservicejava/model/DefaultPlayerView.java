package com.app.playerservicejava.model;

public class DefaultPlayerView extends PlayerView {
    private String name;
    private String playerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Override
    Player toPlayer() {

        return null;
    }
}
