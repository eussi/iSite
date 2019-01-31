package com.eussi.blog.base.modules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 *
 */
public class Page<T> extends BaseDomain implements Serializable {

	private static int DEFAULT_PAGE_SIZE = 20;

    private long startIndex = 1; //当前记录开始数

	private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数

    private long totalCount; // 总记录数

    private long totalPage; // 总页数

	private List<T> data; // 当前页中存放的记录,类型一般为List

	/**
	 * 构造方法，只构造空页.
	 */
	public Page() {
		this(0, DEFAULT_PAGE_SIZE, 0, 0, new ArrayList());
	}

    public Page(int pageNo, int pageSize) {
        this((pageNo-1) * pageSize, pageSize, 0, 0, new ArrayList());
    }

    /**
     * 构造方法
     *
     * @param startIndex 当前记录起始数
     * @param pageSize  本页容量
     * @param totalCount 数据库中总记录条数
     * @param totalPage 数据库中总页数
     * @param data   本页包含的数据
     */
	public Page(long startIndex, int pageSize, long totalCount, long totalPage, List<T> data) {
		this.startIndex = startIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
		this.data = data;
	}

    /**
     * 取该页当前页码,页码从1开始.
     */
    public long getCurrentPage() {
        return startIndex / pageSize + 1;
    }

	/**
	 * 该页是否有下一页.
	 */
	public boolean isHasNextPage() {
		return this.getCurrentPage() < this.getTotalPage();
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean isHasPreviousPage() {
		return this.getCurrentPage() > 1;
	}

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}