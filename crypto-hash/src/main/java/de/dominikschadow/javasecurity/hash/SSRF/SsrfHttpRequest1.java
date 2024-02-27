package com.geekmasher.ssrf;

import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class SsrfHttpRequest1 {

    @GetMapping("/ssrf/httpreq1")
    @ResponseBody
	public String index(@RequestParam(required = false) String input) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(input))
                .GET()
                .build();

            HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );

            return response.body();
        }
        catch (Exception e) {
            return "Invalid URL :: " + input;
        }
	}
}
