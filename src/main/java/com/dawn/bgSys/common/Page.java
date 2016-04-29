package com.dawn.bgSys.common;

/**
 * Created with qiangxiaolong
 * User: P0031372
 * Date: 13-8-12
 * Time: 上午9:43.
 */
public class Page {

    /**
     * 总记录数
     */
    private int totalRow;

    /**
     * 每页记录数
     */
    private int pageSize = 10;

    /**
     * 当前页码
     */
    private int currentCount;

    /**
     * 总页数
     */
    private int total;

    /**
     * 起始记录下标
     */
    private int beginIndex;

    /**
     * 截止记录下标
     */
    private int endIndex;

    /**
     * 构造方法，使用总记录数，当前页码
     *
     * @param totalRow     总记录数
     * @param currentCount 当前页面，从1开始
     */
    public Page(int totalRow, int currentCount) {
        this.totalRow = totalRow;
        this.currentCount = currentCount;
        calculate();
    }

    /**
     * 构造方法 ，利用总记录数，当前页面
     *
     * @param totalRow     总记录数
     * @param currentCount 当前页面
     * @param pageSize     默认10条
     */
    public Page(int totalRow, int currentCount, int pageSize) {
        this.totalRow = totalRow;
        this.currentCount = currentCount;
        this.pageSize = pageSize;
        calculate();
    }

    private void calculate() {
        total = totalRow / pageSize + ((totalRow % pageSize) > 0 ? 1 : 0);

        if (currentCount > total) {
            currentCount = total;
        } else if (currentCount < 1) {
            currentCount = 1;
        }

        beginIndex = (currentCount - 1) * pageSize;
        endIndex = beginIndex + pageSize;
        if (endIndex > totalRow) {
            endIndex = totalRow;
        }
    }

    public int getTotalRow() {
        return totalRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getTotal() {
        return total;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

}
