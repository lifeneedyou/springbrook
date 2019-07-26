package com.brook.bean;

public class PageTag {
    private Integer startOffset;
    private Integer pageSize;

    public Integer getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(Integer startOffset) {
        this.startOffset = startOffset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static PageTag set(Integer pageNumber, Integer pageSize){
        PageTag pageTag = new PageTag();
        int startOffset = (pageNumber - 1) * pageSize;
        pageTag.setStartOffset(startOffset);
        pageTag.setPageSize(pageSize);
        return pageTag;
    }
}
