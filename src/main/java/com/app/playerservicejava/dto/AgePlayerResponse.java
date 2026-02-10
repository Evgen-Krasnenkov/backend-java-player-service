package com.app.playerservicejava.dto;

import com.app.playerservicejava.model.Player;

public class AgePlayerResponse {
    public long ageInYears;

    public long getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(long ageInYears) {
        this.ageInYears = ageInYears;
    }

    public AgePlayerResponse(long age){
        this.ageInYears = age;
    }

    public AgePlayerResponse(){}
}
