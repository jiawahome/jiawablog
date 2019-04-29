package com.jiawablog.dto;

public class PageDto {

    private int cur;

    private int pageSize;

    private long count;

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageDto{");
        sb.append("cur=").append(cur);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
