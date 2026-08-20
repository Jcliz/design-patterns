package br.pucpr.table;

import br.pucpr.user.Theme;
import java.util.List;

public class Table {
  public void print(List<TableData> rows, boolean alignRight, Theme theme) {
    if (rows == null || rows.isEmpty()) {
      System.out.println("ERRO: Lista de dados vazia ou nula.");
      return;
    }
    final var borderChar = theme.getBorderChar();
    final var headers = rows.get(0).getHeaders();
    final var widths = new int[headers.size()];
    for (var i = 0; i < headers.size(); i++) {
      widths[i] = headers.get(i).length();
    }
    final var rowFormat = buildRowFormat(widths);
    final var headerLine = String.format(rowFormat, headers.toArray());
    final var borderWidth = headerLine.length();

    var sb = new StringBuilder();
    sb.repeat(borderChar, borderWidth).append("\n");
    sb.append(headerLine).append("\n");
    sb.repeat(borderChar, borderWidth).append("\n");
    for (var row : rows) {
      if (row == null) {
        continue;
      }
      sb.append(String.format(rowFormat, row.getValues().toArray())).append("\n");
    }
    sb.repeat(borderChar, borderWidth).append("\n");

    if (alignRight) {
      var lines = sb.toString().split("\n");
      for (var line : lines) {
        System.out.println("                    " + line);
      }
    } else {
      System.out.print(sb);
    }
  }

  private static String buildRowFormat(int[] widths) {
    var sb = new StringBuilder("|");
    for (var width : widths) {
      sb.append(" %-").append(width).append("s |");
    }
    return sb.toString();
  }
}
