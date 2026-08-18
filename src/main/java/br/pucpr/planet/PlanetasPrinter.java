package br.pucpr.planet;

import br.pucpr.user.Theme;
import java.util.ArrayList;
import java.util.Locale;

public class PlanetasPrinter {
  private static final Locale LOCALE = Locale.of("pt", "BR");
  private static final double EARTH_SUN_DISTANCE_KM = 149_600_000.0;
  private static final int BORDER_WIDTH = 85;
  private static final int CONSOLE_WIDTH = 120;

  public void print(ArrayList<Planet> planets, boolean alignRight, Theme theme) {
    if (planets == null || planets.isEmpty()) {
      System.out.println("ERRO: Lista de planetas vazia ou nula.");
      return;
    }

    String border = String.valueOf(theme.getBorderChar()).repeat(BORDER_WIDTH);
    StringBuilder sb = new StringBuilder();

    sb.append(border).append("\n");
    sb.append(String.format("| %-15s | %-12s | %-17s | %-15s | %-10s |%n",
        "Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", "Tipo"));
    sb.append(border).append("\n");

    for (Planet planet : planets) {
      if (planet == null) {
        continue;
      }
      sb.append(String.format("| %-15s | %-12s | %-17s | %-15s | %-10s |%n",
          planet.name(),
          formatDiameter(planet.diameterKm()),
          formatSunDistanceKm(planet.sunDistanceKm()),
          formatSunDistanceUa(planet.sunDistanceKm()),
          formatType(planet.type())));
    }
    sb.append(border).append("\n");

    for (String line : sb.toString().split("\n")) {
      System.out.println(alignRight ? alignRight(line) : line);
    }
  }

  private static String alignRight(String line) {
    int padding = CONSOLE_WIDTH - line.length();
    return padding > 0 ? " ".repeat(padding) + line : line;
  }

  private static String formatDiameter(double diameterKm) {
    return String.format(LOCALE, "%,.1f", diameterKm);
  }

  private static String formatSunDistanceKm(long sunDistanceKm) {
    return String.format(LOCALE, "%,d", sunDistanceKm);
  }

  private static String formatSunDistanceUa(long sunDistanceKm) {
    return String.format(LOCALE, "%,.2f", sunDistanceKm / EARTH_SUN_DISTANCE_KM);
  }

  private static String formatType(PlanetType type) {
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }
}
