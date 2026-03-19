package com.jonjam.weathermcp.dailyforecast;

import com.jonjam.weathermcp.Prompts;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Component;

@Component
public class DailyForecastProvider {

  @McpPrompt(
      name = Prompts.DAILY_FORECAST_PROMPT,
      description = "Ask for the daily weather forecast in a location.")
  public String dailyForecastPrompt(
      @McpArg(name = "location", description = "City or point of interest", required = true)
          final String location) {

    final StringBuilder prompt = new StringBuilder("Provide the daily weather forecast for ");

    prompt.append(location).append(".");

    return prompt.toString();
  }
}
