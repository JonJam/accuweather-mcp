package com.jonjam.weathermcp.hourlyforecast;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.jspecify.annotations.Nullable;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HourlyForecastSummaryDto {

  @Nullable String detailLink;

  List<HourlyForecastHourSummaryDto> hours;
}
