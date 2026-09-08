package br.pucpr.table.model;

public final class PagedTableData implements TableData {
  private final TableData source;
  private final int pageSize;
  private int page;

  public PagedTableData(TableData source, int pageSize, int page) {
    if (source == null) {
      throw new IllegalArgumentException("Source cannot be null");
    }
    if (pageSize <= 0) {
      throw new IllegalArgumentException("Page size must be greater than zero");
    }
    this.source = source;
    this.pageSize = pageSize;
    setPage(page);
  }

  public PagedTableData(TableData source, int pageSize) {
    this(source, pageSize, 0);
  }

  public TableData getSource() {
    return source;
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    if (page < 0 || page >= pageCount()) {
      throw new IllegalArgumentException("Invalid page: " + page);
    }
    this.page = page;
  }

  public int pageCount() {
    final var rows = source.rowCount();
    return rows == 0 ? 1 : (rows + pageSize - 1) / pageSize;
  }

  public boolean hasNext() {
    return page < pageCount() - 1;
  }

  public boolean hasPrevious() {
    return page > 0;
  }

  public boolean next() {
    if (!hasNext()) {
      return false;
    }
    page++;
    return true;
  }

  public boolean previous() {
    if (!hasPrevious()) {
      return false;
    }
    page--;
    return true;
  }

  public void first() {
    page = 0;
  }

  public void last() {
    page = pageCount() - 1;
  }

  private int offset() {
    return page * pageSize;
  }

  @Override
  public int rowCount() {
    return Math.max(0, Math.min(pageSize, source.rowCount() - offset()));
  }

  @Override
  public int colCount() {
    return source.colCount();
  }

  @Override
  public String header(int col) {
    return source.header(col);
  }

  @Override
  public String get(int row, int col) {
    if (row < 0 || row >= rowCount()) {
      throw new IndexOutOfBoundsException("Invalid row: " + row);
    }
    return source.get(offset() + row, col);
  }
}
