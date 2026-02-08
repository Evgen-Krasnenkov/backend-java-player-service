package com.app.playerservicejava.model;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.ollama4j.types.OllamaModelType;

import java.util.Arrays;

public enum Model {
    OPENAI("openai"),
    TINYLLAMA(OllamaModelType.TINYLLAMA);
    private final String value;

    Model(String name) {
        this.value = name;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }

    public static Model fromString(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        Model[] values = Model.values();
      return Arrays.stream(values)
                .filter(v -> v.value.equalsIgnoreCase(name) || v.getValue().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant " + Model.class.getCanonicalName() + "." + name));
    }
}
