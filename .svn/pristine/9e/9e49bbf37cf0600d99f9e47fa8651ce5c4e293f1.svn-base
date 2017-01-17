package com.baofeng.commons.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.bean.MonitorLogBean;
import com.baofeng.commons.dao.MonitorLogDAO;
import com.baofeng.commons.entity.MonitorLog;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Service("monitorLogService")
public class MonitorLogServiceImpl implements IMonitorLogService {

	@Autowired
	private MonitorLogDAO monitorLogDAO;

	public MonitorLogDAO getMonitorLogDAO() {
		return monitorLogDAO;
	}

	public void setMonitorLogDAO(MonitorLogDAO monitorLogDAO) {
		this.monitorLogDAO = monitorLogDAO;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public PageResult readAllPages(Integer pageSize, Integer currentPage, SearchFilter filter) {
		return null;
	}

	@Override
	public boolean logsDelete(HttpServletRequest request, String logs) {
		if (StringUtils.isNotBlank(logs)) {
			RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
			if (user != null && user.getUser() != null) {
				if (user.getUser().getGenrer() == Genres.SUPERS) {
					return true;
				}
				MonitorLog mlog = new MonitorLog();
				logs = user.getUser().getAccounts() + "删除了" + logs;
				mlog.setLog(logs);
				mlog.setType(2);
				mlog.setBranchs(user.getBranchs());
				mlog.setOperator(user.getUser());
				mlog.setIp(Constants.InternetProtocol(request));
				return this.monitorLogDAO.saveMonitorLog(mlog);
			}
		}
		return false;
	}

	@Override
	public boolean logsAdd(HttpServletRequest request, String logs) {
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null && user.getUser() != null) {
			if (user.getUser().getGenrer() == Genres.SUPERS) {
				return true;
			}
			if (StringUtils.isNotBlank(logs)) {
				MonitorLog mlog = new MonitorLog();
				logs = user.getUser().getAccounts() + "新增了" + logs;
				mlog.setLog(logs);
				mlog.setType(2);
				mlog.setBranchs(user.getBranchs());
				mlog.setOperator(user.getUser());
				mlog.setIp(Constants.InternetProtocol(request));
				return this.monitorLogDAO.saveMonitorLog(mlog);
			}
		}
		return false;
	}

	@Override
	public boolean logsLogin(HttpServletRequest request) {
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null && user.getUser() != null) {
			if (user.getUser().getGenrer() == Genres.SUPERS && user.getUser().getAccounts().startsWith("admin")) {
				return true;
			}
			MonitorLog mlog = new MonitorLog();
			String logs = user.getUser().getAccounts() + "登陆了";
			mlog.setType(1);
			mlog.setLog(logs);
			mlog.setBranchs(user.getBranchs());
			mlog.setOperator(user.getUser());
			mlog.setIp(Constants.InternetProtocol(request));
			return this.monitorLogDAO.saveMonitorLog(mlog);
		}
		return false;
	}

	@Override
	public boolean logsUpdate(HttpServletRequest request, String logs) {
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null && user.getUser() != null) {
			if (user.getUser().getGenrer() == Genres.SUPERS) {
				return true;
			}
			if (StringUtils.isNotBlank(logs)) {
				MonitorLog mlog = new MonitorLog();
				logs = user.getUser().getAccounts() + "修改了" + logs;
				mlog.setLog(logs);
				mlog.setType(3);
				mlog.setBranchs(user.getBranchs());
				mlog.setOperator(user.getUser());
				mlog.setIp(Constants.InternetProtocol(request));
				return this.monitorLogDAO.saveMonitorLog(mlog);
			}
		}
		return false;

	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, Integer type, String fastArg, SearchFilter filter) {
		NPageResult rows = this.monitorLogDAO.readPagesLog(pageSize, curPage, sortName, sortOrder, type, fastArg, filter);
		if (rows != null && rows.getData() != null) {
			List<MonitorLogBean> listBean = new ArrayList<MonitorLogBean>();
			if (rows.getData().size() > 0) {
				for (Object o : rows.getData()) {
					MonitorLog log = (MonitorLog) o;
					MonitorLogBean bean = new MonitorLogBean();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bean.setCreateDT(sdf.format(log.getCreateDT()));
					bean.setIp(log.getIp());
					bean.setAccounts(log.getOperator().getAccounts());
					bean.setLog(log.getLog());
					bean.setId(log.getId());
					listBean.add(bean);
				}
			}
			rows.setData(listBean);
		}
		return rows;
	}

}
