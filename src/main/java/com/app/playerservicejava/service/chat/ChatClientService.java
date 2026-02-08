package com.app.playerservicejava.service.chat;

import com.app.playerservicejava.dto.ChatDto;
import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.Model;
import io.github.ollama4j.models.OllamaResult;
import io.github.ollama4j.types.OllamaModelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.PromptBuilder;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ChatClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatClientService.class);

    private OllamaAPI ollamaAPI;

    public ChatClientService(OllamaAPI ollamaAPI) {
        this.ollamaAPI = ollamaAPI;
    }

    public List<Model> listModels() throws OllamaBaseException, IOException, URISyntaxException, InterruptedException {
        List<Model> models = ollamaAPI.listModels();
        return models;
    }

    public String chat(ChatDto chatDto) throws OllamaBaseException, IOException, InterruptedException {

        // https://ollama4j.github.io/ollama4j/intro
        PromptBuilder promptBuilder =
                new PromptBuilder()
                        .addLine(chatDto.prompt());
        OptionsBuilder chatOptions = new OptionsBuilder()
                .setMirostatEta(Float.parseFloat("10"))
                .setTemperature(Float.parseFloat("0.2"));

        boolean raw = false;
        OllamaResult response = ollamaAPI.generate(chatDto.model().getValue(), promptBuilder.build(), raw,chatOptions.build());
        return response.getResponse();
    }

}
