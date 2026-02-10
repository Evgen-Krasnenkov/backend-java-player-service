package com.app.playerservicejava.controller;

import com.app.playerservicejava.dto.AgePlayerResponse;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.model.PlayersAges;
import com.app.playerservicejava.service.PlayerService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "v1/players", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PlayerController {
    @Resource
    private PlayerService playerService;

    @GetMapping()
    public ResponseEntity<PlayersAges> getPlayers() {
        var players = playerService.getAges();
        return ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgePlayerResponse> getPlayerById(@PathVariable("id") String id) {
        AgePlayerResponse ageByPlayerId = playerService.getAgeByPlayerId(id);
        return ResponseEntity.ok(ageByPlayerId);
    }
}
