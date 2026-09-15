package br.pucpr.table;

import br.pucpr.table.model.TableData;
import br.pucpr.table.model.TableDataListener;

public class StatusBar implements TableDataListener {
  private final String title;

  public StatusBar(String title) {
    this.title = title;
  }

  @Override
  public void dataChanged(TableData source) {
    System.out.printf("[%s] %d registro(s) exibido(s)%n%n", title, source.rowCount());
  }
}
