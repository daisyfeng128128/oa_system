package com.baofeng.commons.service;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.utils.IBaseService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IMonitorLogService extends IBaseService {


	/** 记录删除日志 */
	public boolean logsDelete(HttpServletRequest request, String logs);

	/** 记录新增日志 */
	public boolean logsAdd(HttpServletRequest request, String logs);

	/** 记录修改日志 */
	public boolean logsUpdate(HttpServletRequest request, String logs);
	
	/** 记录登陆日志 */
	public boolean logsLogin(HttpServletRequest request);

	@SuppressWarnings("rawtypes")
	NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, Integer type, String fastArg, SearchFilter filter);
}
