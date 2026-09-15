package br.pucpr.table.model;

@FunctionalInterface
public interface TableDataListener {
  void dataChanged(TableData source);
}
