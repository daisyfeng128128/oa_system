package com.baofeng.utils;

import java.util.List;

public class NPageResult<T> {

	private boolean success;
	private long totalRows;
	private long curPage;
	private List<T> data;

	@SuppressWarnings("unused")
	private NPageResult() {
	}

	public NPageResult(boolean success, long totalRows, long curPage, List<T> data) {
		this.success = success;
		this.totalRows = totalRows;
		this.curPage = curPage;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
