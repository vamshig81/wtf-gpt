package dev.vgade.wtfgpt;

public record ChatGptRequest(String model, String prompt, int max_tokens, int temperature) {
}
