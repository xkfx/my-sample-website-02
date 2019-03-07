package org.sample.website.util;

/**
 * 用来手动分页..
 * 因为mybatis的RowBounds
 * 会把所有记录先读到ResultSet里
 * 再 弃掉。
 */
public class RowBounds {

    private int offset;
    private int limit;

    public RowBounds() {
    }

    public RowBounds(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
