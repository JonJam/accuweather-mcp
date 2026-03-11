package com.jonjam.weathermcp.locations.autocomplete;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import org.wiremock.spring.InjectWireMock;

// TODO Refactor to demo happy path and error handling
@SpringBootTest
@EnableWireMock(
    @ConfigureWireMock(baseUrlProperties = "spring.http.serviceclient.accuweather.base-url"))
class LocationsAutocompleteGatewayIntegrationTest {

  @InjectWireMock private WireMockServer wireMock;

  @Autowired private LocationsAutocompleteGateway gateway;

  @BeforeEach
  void stubAccuWeatherAutocomplete() {
    wireMock.stubFor(
        get(urlPathEqualTo("/locations/v1/autocomplete"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBodyFile("locations-autocomplete-san.json")));
  }

  @Nested
  @DisplayName("autocompleteForCitiesAndPointsOfInterest")
  class AutocompleteForCitiesAndPointsOfInterest {

    @Test
    @DisplayName("calls AccuWeather over HttpServiceClient")
    void callsAccuWeatherOverHttpServiceClient() {
      // Arrange
      final Locale locale = Locale.forLanguageTag("en-us");

      // Act
      final List<String> result = gateway.autocompleteForCitiesAndPointsOfInterest("san", locale);

      // Assert
      assertThat(result, contains("San Francisco"));
    }
  }
}
