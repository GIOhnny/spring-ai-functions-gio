package ro.giohnny.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

public record StockResponse(
        @JsonPropertyDescription("Stock ticker symbol") String ticker,
        @JsonPropertyDescription("Company name") String name,
        @JsonPropertyDescription("Stock price") BigDecimal price,
        @JsonPropertyDescription("Stock exchange") String exchange,
        @JsonPropertyDescription("Last updated timestamp") long updated,
        @JsonPropertyDescription("Currency of the stock price") String currency
) {}
