package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ColumnTableData<T> extends AbstractTableData {
  private final List<ColumnData<? super T>> columns;
  private final List<T> data;

  public ColumnTableData(
      Collection<? extends T> data, Collection<? extends ColumnData<? super T>> columns) {
    this.columns = new ArrayList<>(columns);
    this.data = new ArrayList<>(data);
  }

  @SafeVarargs
  public ColumnTableData(Collection<? extends T> data, ColumnData<? super T>... columns) {
    this(data, Arrays.asList(columns));
  }

  @Override
  public int rowCount() {
    return data.size();
  }

  @Override
  public int colCount() {
    return columns.size();
  }

  @Override
  public String header(int col) {
    return columns.get(col).header();
  }

  @Override
  public String get(int row, int col) {
    var line = data.get(row);
    return columns.get(col).get(line);
  }

  public void add(T line) {
    data.add(line);
    fireDataChanged();
  }

  public void addAll(Collection<? extends T> lines) {
    if (lines.isEmpty()) return;
    data.addAll(lines);
    fireDataChanged();
  }

  public void set(int row, T line) {
    data.set(row, line);
    fireDataChanged();
  }

  public void remove(int row) {
    data.remove(row);
    fireDataChanged();
  }

  public void clear() {
    if (data.isEmpty()) return;
    data.clear();
    fireDataChanged();
  }

  public void addColumn(ColumnData<? super T> column) {
    columns.add(column);
    fireDataChanged();
  }
}
