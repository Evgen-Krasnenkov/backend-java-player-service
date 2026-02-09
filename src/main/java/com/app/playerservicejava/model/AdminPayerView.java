package com.app.playerservicejava.model;


public class AdminPayerView extends PlayerView{
    private String playerId;
    private String name;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
    public Player toPlayer() {
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setLastName(lastName);
        player.setFirstName(name);
        return player;
    }
}
