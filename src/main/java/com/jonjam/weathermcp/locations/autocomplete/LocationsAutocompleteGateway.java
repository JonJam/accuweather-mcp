package com.jonjam.weathermcp.locations.autocomplete;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationsAutocompleteGateway {

  private final AccuWeatherLocationsAutocompleteClient client;

  public List<String> autocompleteForCitiesAndPointsOfInterest(
      final String partialName, final Locale language) {

    // TODO add error handling
    return client
        .autocompleteForCitiesAndPointsOfInterest(partialName, language.toLanguageTag())
        .stream()
        .map(AccuWeatherLocationsAutocompleteDto::getLocalizedName)
        .collect(Collectors.toList());
  }
}
