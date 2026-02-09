package com.app.playerservicejava.service;

import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.PlayerView;

public interface PlayerViewStrategy {
    PlayerView toView(Player player);
}
