package org.emsi.aggregatorservice;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ProductInformationClientImpl implements ProductInformationClient {

    @Override
    public String getProductTitle() {
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:51515/information"))
                .build();
        var client = HttpClient.newHttpClient();
        try {
            var httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
        } catch (IOException ioe) {
            System.out.println("IOException Occurred" + ioe);
        } catch (InterruptedException ie) {
            System.out.println("InterruptedException Occurred" + ie);
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
