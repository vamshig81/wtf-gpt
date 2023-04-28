package dev.vgade.wtfgpt;

public record CharGptResponse(
        String id,
        String object,
        String created,
        String model,
        ChatGptResponseChoice[] choices,
        ChatGptResponseUsage usage) {
}
