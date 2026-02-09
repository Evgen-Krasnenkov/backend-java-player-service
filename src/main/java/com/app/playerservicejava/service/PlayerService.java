package com.app.playerservicejava.service;

import com.app.playerservicejava.dto.PlayerDto;
import com.app.playerservicejava.mapper.PlayerMapper;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    public PlayerService(PlayerMapper mapper, PlayerRepository playerRepository) {
        this.mapper = mapper;
        this.playerRepository = playerRepository;
    }

    private final PlayerMapper mapper;
    private final PlayerRepository playerRepository;

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

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> update(String id, PlayerDto playerDto) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(item -> {
            mapper.dtoToEntity(playerDto, item);
            return playerRepository.save(item);
        });
    }

    public void delete(String id) {
        playerRepository.deleteById(id);
    }
}
