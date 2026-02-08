package com.app.playerservicejava.dto;

import com.app.playerservicejava.model.Model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record ChatDto(String prompt, Model model) {
}
