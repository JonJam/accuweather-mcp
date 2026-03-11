package com.jonjam.weathermcp.locations.autocomplete;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class AccuWeatherLocationsAutocompleteDto {

  @JsonProperty("Key")
  String key;

  @JsonProperty("LocalizedName")
  String localizedName;

  @JsonProperty("Type")
  String type;
}
