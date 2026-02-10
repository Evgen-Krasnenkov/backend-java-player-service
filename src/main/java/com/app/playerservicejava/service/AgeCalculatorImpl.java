package com.app.playerservicejava.service;

import com.app.playerservicejava.model.Player;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class AgeCalculatorImpl implements AgeCalculator {
    @Override
    public long getAge(Player player) {
        long age = 0;
        if (isAlive(player)) {
            LocalDate birthDate = LocalDate.of(Integer.parseInt(player.getBirthYear()), Integer.parseInt(player.getBirthMonth()), Integer.parseInt(player.getBirthDay()));
            LocalDate now = LocalDate.now();
            age = ChronoUnit.YEARS.between(birthDate, now);
        } else {
            LocalDate deathLocalDate = LocalDate.of(Integer.parseInt(player.getDeathYear()), Integer.parseInt(player.getDeathMonth()), Integer.parseInt(player.getDeathDay()));
            LocalDate birthDate = LocalDate.of(Integer.parseInt(player.getBirthYear()), Integer.parseInt(player.getBirthMonth()), Integer.parseInt(player.getBirthDay()));
            age = ChronoUnit.YEARS.between(birthDate, deathLocalDate);
        }
        return age;
    }

    private boolean isAlive(Player player) {
        return player.getDeathYear() == null;
    }
}
