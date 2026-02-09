package com.app.playerservicejava.service;

import com.app.playerservicejava.model.DefaultPlayerView;
import org.springframework.stereotype.Component;

@Component
public class PayerViewFactory {
    private final DefaultPlayerViewStrategy defaultPlayerViewStrategy;

    private final AdminPlayerViewStrategy adminPlayerViewStrategy;

    public PayerViewFactory(DefaultPlayerViewStrategy defaultPlayerViewStrategy, AdminPlayerViewStrategy adminPlayerViewStrategy) {
        this.defaultPlayerViewStrategy = defaultPlayerViewStrategy;
        this.adminPlayerViewStrategy = adminPlayerViewStrategy;
    }

    public PlayerViewStrategy getPlayerViewStrategy(boolean isAdmin) {
        return isAdmin ? adminPlayerViewStrategy : defaultPlayerViewStrategy;
    }
}
