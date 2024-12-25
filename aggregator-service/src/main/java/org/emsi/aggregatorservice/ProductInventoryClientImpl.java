package org.emsi.aggregatorservice;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ProductInventoryClientImpl implements ProductInventoryClient {

    @Override
    public Integer getProductInventories() {
        var response = "";

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:51516/inventories"))
                .build();
        var client = HttpClient.newHttpClient();
        try {
            var httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            response = httpResponse.body();
        } catch (IOException ioe) {
            System.out.println("IOException Occurred" + ioe);
        } catch (InterruptedException ie) {
            System.out.println("InterruptedException Occurred" + ie);
            Thread.currentThread().interrupt();
        }
        if ("".equalsIgnoreCase(response)) {
            return null;
        } else {
            return Integer.parseInt(response);
        }
    }
}
