package com.app.playerservicejava.model;

import com.app.playerservicejava.dto.AgePlayerResponse;

import java.util.ArrayList;
import java.util.List;

public class PlayersAges {
    private List<AgePlayerResponse> ages = new ArrayList<>();

    public List<AgePlayerResponse> getAges() {
        return ages;
    }

    public void setAges(List<AgePlayerResponse> ages) {
        this.ages = ages;
    }

}
