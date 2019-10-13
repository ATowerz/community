package com.atz.springboot.util;

public class Page {

    private int start; //开始页数
    private int count; //每页显示个数
    private int total; //总个数
    private String param; //参数


    private static final int defaultCount = 8; //默认每页显示8条

    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public Page (){
        count = defaultCount;
    }
    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }

    public boolean isHasPreviouse(){
        if(start==0)
            return false;
        return true;
    }
    public boolean isHasNext(){
        if(start==getLast())
            return false;
        return true;
    }

    public int getTotalPage(){
        int totalPage;
        // 假设总数是80，是能够被8整除的，那么就有10页
        if (0 == total % count)
            totalPage = total /count;
            // 假设总数是81，不能够被8整除的，那么就有11页
        else
            totalPage = total / count + 1;

        if(0==totalPage)
            totalPage = 1;
        return totalPage;
    }

    public int getLast(){
        int last;
        // 假设总数是80，是能够被8整除的，那么最后一页的开始就是72
        if (0 == total % count)
            last = total - count;
            // 假设总数是81，不能够被8整除的，那么最后一页的开始就是80
        else
            last = total - total % count;
        last = last<0?0:last;
        return last;
    }

    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                + ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
                + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }

}
