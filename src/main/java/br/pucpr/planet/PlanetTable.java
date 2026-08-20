package br.pucpr.planet;

import br.pucpr.table.TableData;
import java.util.List;

public class PlanetTable implements TableData {
  private final Planet planet;

  public PlanetTable(Planet planet) {
    this.planet = planet;
  }

  @Override
  public List<String> getHeaders() {
    return List.of("Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", "Tipo");
  }

  @Override
  public List<String> getValues() {
    return List.of(
        formatName(planet.name()),
        String.format("%,.1f", planet.diameterKm()),
        String.format("%,d", planet.sunDistanceKm()),
        String.format("%.2f", Planet.kmToAu(planet.sunDistanceKm())),
        formatType(planet.type()));
  }

  private static String formatName(String name) {
    if (name == null || name.isEmpty()) {
      return "NÃO INFORMADO";
    }
    if (name.length() > 20) {
      name = name.substring(0, 17) + "...";
    }
    return name;
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
