package com.ning.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageTableRequest implements Serializable {
    private  Integer page;
    private  Integer limit;
    private  Integer offset;//起始点
    //起始点的计算
    public void countOffset(){
        if(null== this.page||null== limit){
            this.offset=0;
            return;
        }
        this.offset = (this.page - 1) * this.limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
