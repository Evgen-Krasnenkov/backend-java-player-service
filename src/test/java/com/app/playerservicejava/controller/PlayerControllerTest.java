package com.app.playerservicejava.controller;


import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.service.PayerViewFactory;
import com.app.playerservicejava.service.PlayerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private PayerViewFactory payerViewFactory;

    @InjectMocks
    private PlayerController playerController;

    @Test
    void whenGetPlayers_thenReturnPlayers_Success() throws Exception {
        Players playersList = new Players();
        Player player = new Player();
        player.setFirstName("John");
        player.setPlayerId("test");
        playersList.setPlayers(List.of(player));


        Mockito.when(playerService.getPlayers()).thenReturn(playersList);
        RequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .get("/v1/players")
                .accept("application/json");

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.players").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.players.length()", Matchers.greaterThan(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.players", Matchers.any(List.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.players[0].playerId", Matchers.any(String.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.players[0].playerId").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.players[0].firstName").value("John"));
    }

}