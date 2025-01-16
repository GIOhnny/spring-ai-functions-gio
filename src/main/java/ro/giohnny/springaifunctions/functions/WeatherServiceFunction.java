package ro.giohnny.springaifunctions.functions;

import ro.giohnny.springaifunctions.model.WeatherRequest;
import ro.giohnny.springaifunctions.model.WeatherResponse;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

/**
 * Created by jt, Spring Framework Guru.
 */
public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse> {

    //public static final String WEATHER_URL = "https://api.api-ninjas.com/v1/weather";
    public static final String WEATHER_URL = "https://api.weatherapi.com/v1/current.json";

    private final String weatherApiKey;

    public WeatherServiceFunction(String weatherApiKey) {
        this.weatherApiKey = weatherApiKey;
        System.out.println("WeatherServiceFunction created with API key: " + weatherApiKey);
    }

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(WEATHER_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("Accept", "application/json");
//                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        return restClient.get().uri(uriBuilder -> {
            System.out.println("Building URI for weather request: " + weatherRequest);

            uriBuilder.queryParam("q", weatherRequest.country());
            uriBuilder.queryParam("key", weatherApiKey);

            return uriBuilder.build();
        }).retrieve().body(WeatherResponse.class);
    }
}























