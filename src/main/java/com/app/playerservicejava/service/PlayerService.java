package com.app.playerservicejava.service;

import com.app.playerservicejava.PayerServiceException;
import com.app.playerservicejava.dto.AgePlayerResponse;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.model.PlayersAges;
import com.app.playerservicejava.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    private PlayerRepository playerRepository;
    private AgeCalculator ageCalculator;

    public PlayerService(PlayerRepository playerRepository, AgeCalculator ageCalculator) {
        this.playerRepository = playerRepository;
        this.ageCalculator = ageCalculator;
    }


    public Players getPlayers() {
        Players players = new Players();
        playerRepository.findAll()
                .forEach(players.getPlayers()::add);
        return players;
    }

    public Optional<Player> getPlayerById(String playerId) {
        Optional<Player> player = null;

        /* simulated network delay */
        try {
            player = playerRepository.findById(playerId);
            Thread.sleep((long)(Math.random() * 2000));
        } catch (Exception e) {
            LOGGER.error("message=Exception in getPlayerById; exception={}", e.toString());
            return Optional.empty();
        }
        return player;
    }

    public AgePlayerResponse getAgeByPlayerId(String playerId) {
        Optional<Player> playerById = this.getPlayerById(playerId);
        Long age = playerById.map(ageCalculator::getAge).orElseThrow(() -> new PayerServiceException("Player not found"));
        AgePlayerResponse agePlayerResponse = new AgePlayerResponse();
        agePlayerResponse.setAgeInYears(age);
        return agePlayerResponse;
    }

    public PlayersAges getAges() {
        List<Player> playerList = playerRepository.findAll();
        var ages = playerList.stream()
                .map(item -> new AgePlayerResponse(ageCalculator.getAge(item)))
                .toList();
        PlayersAges playersAges = new PlayersAges();
        playersAges.getAges().addAll(ages);
        return playersAges;
    }

}
