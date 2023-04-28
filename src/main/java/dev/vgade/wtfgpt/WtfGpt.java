package dev.vgade.wtfgpt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class WtfGpt {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string to search for: ");
        String search = sc.nextLine();

 /*       String input = """
                {
                  "model": "text-davinci-001",
                  "prompt": "%s",
                  "max_tokens": 100,
                  "temperature": 1
                }
                 """.formatted(search);*/
        ObjectMapper objectMapper = new ObjectMapper();

        ChatGptRequest chatGptRequest = new ChatGptRequest("text-davinci-001", search, 100, 1);
        String input = objectMapper.writeValueAsString(chatGptRequest);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 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")
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {

            CharGptResponse charGptResponse =  objectMapper.readValue(response.body(), CharGptResponse.class);
            String answer = charGptResponse.choices()[charGptResponse.choices().length - 1].text();
            if(!answer.isEmpty()) {
                System.out.println(answer.replace("\n", "").trim());
            } else {
                System.out.println(answer);
            }
        } else {

            System.out.println(response.statusCode());
            System.out.println(response.body());
        }
    }
}
