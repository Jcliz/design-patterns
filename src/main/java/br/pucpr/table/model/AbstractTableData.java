package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTableData implements TableData {
  private final List<TableDataListener> listeners = new ArrayList<>();

  @Override
  public final void addListener(TableDataListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    listeners.add(listener);
  }

  @Override
  public final void removeListener(TableDataListener listener) {
    listeners.remove(listener);
  }

  protected final void fireDataChanged() {
    for (var listener : new ArrayList<>(listeners)) {
      listener.dataChanged(this);
    }
  }
}
