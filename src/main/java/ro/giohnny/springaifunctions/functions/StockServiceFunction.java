package ro.giohnny.springaifunctions.functions;

import org.springframework.web.client.RestClient;
import ro.giohnny.springaifunctions.model.StockRequest;
import ro.giohnny.springaifunctions.model.StockResponse;
import ro.giohnny.springaifunctions.model.WeatherResponse;

import java.util.function.Function;

public class StockServiceFunction implements Function<StockRequest, StockResponse> {
    public static final String STOCK_URL = "https://api.api-ninjas.com/v1/stockprice";

    private final String stockApiKey;

    public StockServiceFunction(String stockApiKey) {
        this.stockApiKey = stockApiKey;
        System.out.println("StockServiceFunction created with API key: " + stockApiKey);
    }

    @Override
    public StockResponse apply(StockRequest stockRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(STOCK_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                    httpHeaders.set("X-Api-Key", stockApiKey);
                }).build();

        try {
            return restClient.get().uri(uriBuilder -> {
                uriBuilder.queryParam("ticker", stockRequest.aapl());
                return uriBuilder.build();
            }).retrieve().body(StockResponse.class);
        } catch (Exception e) {
            System.out.println("Error occurred while fetching stock data: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
