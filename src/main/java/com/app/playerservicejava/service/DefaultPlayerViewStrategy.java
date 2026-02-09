package com.app.playerservicejava.service;

import com.app.playerservicejava.model.DefaultPlayerView;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.PlayerView;
import org.springframework.stereotype.Component;

@Component
public class DefaultPlayerViewStrategy implements PlayerViewStrategy {
    @Override
    public PlayerView toView(Player player) {
        DefaultPlayerView defaultPlayerView = new DefaultPlayerView();
        defaultPlayerView.setName(player.getFirstName());
        defaultPlayerView.setPlayerId(player.getPlayerId());
        return defaultPlayerView;
    }
}
