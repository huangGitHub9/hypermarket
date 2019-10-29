package cn.haiwan.util.pager;


public class PageModel {


    /**
     * 总记录数
     */
    private int recordCount;
    /**
     * 当前页面
     */
    private int pageIndex = 1;
    /**
     * 每页分多少条数据
     */
    private int pageSize = 6;

    /**
     * 总页数
     */
    private int totalSize;

    public int getRecordCount() {

        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageIndex() {

        //如果当前页码大于总页码则让总页码作为当前页码
        //计算总页码
        int totalPageNum = this.getRecordCount() % this.getPageSize() == 0 ? this.getRecordCount() / this.getPageSize() : (this.getRecordCount() / this.getPageSize()) + 1;

        return pageIndex > totalPageNum ? totalPageNum : pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {

        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {

        return totalSize;
    }

    public int getFirstLimitParam() {
        return (this.getPageIndex() - 1) * this.getPageSize();
    }

}
