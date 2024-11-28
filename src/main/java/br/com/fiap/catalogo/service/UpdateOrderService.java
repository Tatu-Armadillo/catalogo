package br.com.fiap.catalogo.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateOrderService {
    private final HttpClient httpClient;

    public UpdateOrderService (){
        this.httpClient = HttpClient.newHttpClient();
    }

    public void updateOrderStatus(String orderId, String newOrderStatus) {
        String pedidosUrl = System.getenv("PEDIDOS_URL");

        if (pedidosUrl == null || pedidosUrl.isEmpty()) {
            System.out.println("A variável de ambiente 'PEDIDOS_URL' não está configurada.");
            return;
        }

        log.info(pedidosUrl);

        String requestBody = String.format("""
                {
                    "orderId": "%s",
                    "paymentStatus": "%s"
                }
                """, orderId, newOrderStatus);
        
        log.info(requestBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://pedidos-api:9090/api/order/update_status"))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
