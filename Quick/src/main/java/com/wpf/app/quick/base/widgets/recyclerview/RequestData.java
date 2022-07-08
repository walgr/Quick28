package com.wpf.app.quick.base.widgets.recyclerview;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public class RequestData {
    private int page = 0;
    private int pageSize = 10;

    public RequestData(int page) {
        this.page = page;
        this.pageSize = 10;
    }

    public RequestData(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public void refresh() {
        page = 0;
    }

    public void loadMore() {
        page++;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
