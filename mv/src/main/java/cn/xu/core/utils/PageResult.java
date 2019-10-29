package cn.xu.core.utils;

public class PageResult {
    private Integer pCur;
    private Integer pTotalCounts;
    public static Integer PAGERESULT_PSIZE =18;
    private Integer pStart;

    public Integer getpStart() {
        return pStart;
    }

    public void setpStart(Integer pCur) {
         pCur = (pCur==null)? 1: pCur;
        int _pStart = (pCur-1)* PageResult.PAGERESULT_PSIZE;
        this.pStart = _pStart;
    }

    public Integer getpCur() {
        return pCur;
    }
    public void setpCur(Integer pCur) {
        int _pCur = (pCur==null)? 1: pCur;
        _pCur = (_pCur-1)* PageResult.PAGERESULT_PSIZE;
        this.pCur = _pCur;
    }

    public Integer getpTotalCounts() {
        return pTotalCounts;
    }

    public void setpTotalCounts(Integer pTotalCounts) {
        this.pTotalCounts = pTotalCounts;
    }
}
