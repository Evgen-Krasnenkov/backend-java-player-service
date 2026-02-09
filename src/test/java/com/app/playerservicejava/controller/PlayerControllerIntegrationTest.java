package com.app.playerservicejava.controller;

import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlayerControllerIntegrationTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @Sql(value = {"classpath:schema-test.sql"})
    void whenGetPlayers_thenReturnPlayers_Success() throws Exception {
        List<Player> playerList = playerRepository.findAll();
        var builder = MockMvcRequestBuilders
                .get("/v1/players");
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());

        ResponseEntity<Players> forEntity = restTemplate.getForEntity("/v1/players", Players.class);
        Assertions.assertNotNull(forEntity.getBody());
        List<Player> players = forEntity.getBody()
                .getPlayers();
        Assertions.assertEquals(playerList.size(), players.size());
    }
}
