package com.app.playerservicejava.controller;

import com.app.playerservicejava.dto.PlayerDto;
import com.app.playerservicejava.model.AdminPayerView;
import com.app.playerservicejava.model.DefaultPlayerView;
import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.PlayerView;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.service.PayerViewFactory;
import com.app.playerservicejava.service.PlayerService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/v1/players", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PlayerController {
    @Resource
    private PlayerService playerService;

    private final PayerViewFactory payerViewFactory;

    public PlayerController(PayerViewFactory payerViewFactory) {
        this.payerViewFactory = payerViewFactory;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Players> getPlayers() {
        Players players = playerService.getPlayers();
        return ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerView> getPlayerById(@PathVariable("id") String id, @RequestParam(required = false) boolean isAdmin) {
        Optional<Player> player = playerService.getPlayerById(id);
        PlayerView playerView = player.map(item ->
                        payerViewFactory.getPlayerViewStrategy(isAdmin).toView(item))
                .orElse(new DefaultPlayerView());
        return ResponseEntity.ok(playerView);
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody AdminPayerView playerView) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playerService.savePlayer(playerView.toPlayer()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Optional<Player>> updatePlayer(@PathVariable String id, @RequestBody PlayerDto playerDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body(playerService.update(id, playerDto));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePlayer(@PathVariable String id) {
        playerService.delete(id);
    }
}
