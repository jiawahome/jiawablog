package com.jiawablog.dto;

public class PageDto {

    private int current;

    private int size;

    private long total;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageDto{");
        sb.append("current=").append(current);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
