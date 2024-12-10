package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class Consulta {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/5f3feed4bcc18739616e67c9/latest/";

    public Moeda obterTaxas(String moeda) {
        String url = API_URL + moeda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moeda.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar taxas de câmbio para a moeda: " + moeda, e);
        }
    }
}
