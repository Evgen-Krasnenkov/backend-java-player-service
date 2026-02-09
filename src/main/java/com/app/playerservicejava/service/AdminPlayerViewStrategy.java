package com.app.playerservicejava.service;

import com.app.playerservicejava.model.AdminPayerView;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.PlayerView;
import org.springframework.stereotype.Component;

@Component
public class AdminPlayerViewStrategy implements PlayerViewStrategy {
    @Override
    public PlayerView toView(Player player) {
        AdminPayerView adminPayerView = new AdminPayerView();
        adminPayerView.setName(player.getFirstName());
        adminPayerView.setLastName(player.getLastName());
        adminPayerView.setPlayerId(player.getPlayerId());
        return adminPayerView;
    }
}
