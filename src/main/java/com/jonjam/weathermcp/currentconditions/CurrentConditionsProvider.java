package com.jonjam.weathermcp.currentconditions;

import com.jonjam.weathermcp.LocaleUtils;
import com.jonjam.weathermcp.Prompts;
import java.util.Locale;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpMeta;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

// TODO Review and improve this when built out tool
@Component
public class CurrentConditionsProvider {

  // TODO tests
  @McpPrompt(
      name = Prompts.CURRENT_CONDITIONS_PROMPT,
      description = "Ask for the current weather conditions in a location.")
  public String currentConditionsPrompt(
      @McpArg(name = "location", description = "City or point of interest", required = true)
          final String location,
      final McpMeta meta) {

    final Locale resolvedLanguage = LocaleUtils.resolveLocale(meta);

    final StringBuilder prompt = new StringBuilder("Provide the current weather conditions for ");

    prompt
        .append(location)
        .append(" using language code ")
        .append(resolvedLanguage.toLanguageTag())
        .append(".");

    return prompt.toString();
  }

  // TODO tests
  @McpTool(
      name = "current-conditions",
      description = "Get the current weather conditions for a location.",
      annotations =
          @McpTool.McpAnnotations(
              title = "Current Weather Conditions",
              readOnlyHint = true,
              destructiveHint = false,
              idempotentHint = true,
              openWorldHint = true))
  public String currentConditionsTool(
      @McpToolParam(description = "City or point of interest", required = true)
          final String location) {

    // TODO validate location parameter

    // TODO call gateway to lookup location key

    // TODO call gateway to get current conditions

    // TODO return current conditions

    return "Current conditions for " + location + " are not yet implemented.";
  }
}
