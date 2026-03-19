package com.jonjam.weathermcp.hourlyforecast;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import org.wiremock.spring.InjectWireMock;

@ActiveProfiles("test")
@SpringBootTest
@EnableWireMock(
    @ConfigureWireMock(baseUrlProperties = "spring.http.serviceclient.accuweather.base-url"))
class AccuWeatherHourlyForecastsClientIntegrationTest {

  @InjectWireMock private WireMockServer wireMock;

  @Autowired private AccuWeatherHourlyForecastsClient client;

  @Nested
  @DisplayName("getTwelveHoursByLocationKey")
  class GetTwelveHoursByLocationKey {

    @Test
    @DisplayName("returns parsed hourly forecasts when AccuWeather returns JSON array")
    void returnsParsedHourlyForecastsWhenAccuWeatherReturnsJsonArray() {
      // Arrange
      wireMock.stubFor(
          get(urlPathEqualTo("/forecasts/v1/hourly/12hour/352579"))
              .willReturn(
                  aResponse()
                      .withStatus(200)
                      .withHeader("Content-Type", "application/json")
                      .withBodyFile("hourly-forecast-12hour-valencia-spain.json")));

      // Act
      final List<AccuWeatherHourlyForecastDto> result =
          client.getTwelveHoursByLocationKey("352579", "en-us", true);

      // Assert
      assertThat(result, hasSize(2));

      final AccuWeatherHourlyForecastDto first = result.get(0);
      assertThat(first.getDateTime(), is("2026-03-19T14:00:00+00:00"));
      assertThat(first.getIconPhrase(), is("Intermittent clouds"));
      assertThat(first.getTemperature().getValue(), is(18.5f));
      assertThat(first.getTemperature().getUnit(), is("C"));
      assertThat(
          first.getLink(),
          is(
              "https://www.accuweather.com/en/es/valencia/352579/hourly-weather-forecast/352579?lang=en-us"));
    }
  }
}
