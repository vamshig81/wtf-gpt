package dev.vgade.wtfgpt;

public record ChatGptResponseUsage(
        int prompt_tokens,
        int completion_tokens,
        int total_tokens
) {
}