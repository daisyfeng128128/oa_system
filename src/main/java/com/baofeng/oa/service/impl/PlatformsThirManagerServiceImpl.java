package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.PlatformsThirdManagerBean;
import com.baofeng.oa.dao.PlatformsThirManagerDAO;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsThirdManager;
import com.baofeng.oa.service.IPlatformsThirManagerService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("platformsThirManagerService")
public class PlatformsThirManagerServiceImpl implements IPlatformsThirManagerService {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private PlatformsThirManagerDAO thirManagerDAO;
	@Autowired
	private IMonitorLogService monitorLogService;
	
	@Override
	public boolean addThirdManager(PlatformsThirdManager post , HttpServletRequest request) {
		if (post != null && post.getId() != null && post.getId().intValue() > 0) {
			PlatformsThirdManager $post = this.readThirdManager(post.getId());
			if ($post != null) {
				$post.setChannel(post.getChannel());
				$post.setSex(post.getSex());
				$post.setHourlyWage(post.getHourlyWage());
				$post.setMajia(post.getMajia());
				$post.setPlat(post.getPlat());
				$post.setPositions(post.getPositions());
				$post.setRealname(post.getRealname());
				post = $post;
				if (this.thirManagerDAO.save(post)) {
					if(post.getChannel() != null){
						this.monitorLogService.logsUpdate(request,"平台："+post.getPlat().getPlatName()+"频道号："+post.getChannel().getChannels()+"下的平台场控: "+ post.getRealname()+" ID: "+post.getId());
					}else{
						this.monitorLogService.logsUpdate(request,"平台："+post.getPlat().getPlatName()+"下的平台场控: "+ post.getRealname()+" ID: "+post.getId());
					}
					this.workManagers.onEvents(WorkNames.EMPLOYEE_ADDACTORES, post);
					return true;
				}
			}
		}
		if (this.thirManagerDAO.save(post)) {
			if(post.getChannel() != null){
				this.monitorLogService.logsAdd(request,"平台："+post.getPlat().getPlatName()+"频道号："+post.getChannel().getChannels()+"下的平台场控: "+ post.getRealname()+" ID: "+post.getId());
			}else{
				this.monitorLogService.logsAdd(request,"平台："+post.getPlat().getPlatName()+"下的平台场控: "+ post.getRealname()+" ID: "+post.getId());
			}
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String majia, String realname, Integer channelid,
			Integer platid, SearchFilter filter) {
		NPageResult rows = this.thirManagerDAO.readAllPages(pageSize, curPage, sortName, sortOrder, majia, realname, channelid, platid, filter);
		if (rows != null && rows.getData().size() > 0) {
			List<PlatformsThirdManagerBean> list = new ArrayList<PlatformsThirdManagerBean>();
			for (Object o : rows.getData()) {
				PlatformsThirdManager post = (PlatformsThirdManager) o;
				PlatformsThirdManagerBean bean = new PlatformsThirdManagerBean();
				bean.setId(post.getId());
				bean.setMajia(post.getMajia());
				bean.setSex(post.getSex() == Sex.MAN ? "男" : "女");
				bean.setRealname(post.getRealname());
				bean.setPositions(post.getPositions());
				bean.setHourlyWage(post.getHourlyWage());
				if (post.getPlat() != null)
					bean.setPlat(post.getPlat().getPlatName());
				if (post.getChannel() != null)
					bean.setChannel(post.getChannel().getChannels());
				else {
					bean.setChannel("-");
				}
				bean.setCreateDT(post.getCreateDT());
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;

	}

	@Override
	public boolean deleteThirdManager(Integer id,HttpServletRequest request) {
		if (id != null && id.intValue() > 0) {
			PlatformsThirdManager thirdManager = this.readThirdManager(id);
			if (thirdManager != null) {
				thirdManager.setStatus(EntityStatus.DELETED);
				if (this.thirManagerDAO.save(thirdManager)) {
					if(thirdManager.getChannel() != null){
						this.monitorLogService.logsDelete(request,"平台："+thirdManager.getPlat().getPlatName()+"频道号："+thirdManager.getChannel().getChannels()+"下的平台场控: "+ thirdManager.getRealname()+" ID: "+thirdManager.getId());
					}else{
						this.monitorLogService.logsDelete(request,"平台："+thirdManager.getPlat().getPlatName()+"下的平台场控: "+ thirdManager.getRealname()+" ID: "+thirdManager.getId());
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public PlatformsThirdManager readThirdManager(Integer id) {
		if (id != null) {
			PlatformsThirdManager platformsThirdManager = this.thirManagerDAO.readThirdManager(id);
			if (platformsThirdManager != null) {
				return platformsThirdManager;
			}
		}
		return null;
	}

	@Override
	public PlatformsChannels readPlatformsChannels(Integer channelid) {
		if (channelid != null && channelid.intValue() > 0) {
			PlatformsChannels platformsChannels = this.thirManagerDAO.readPlatformsChannels(channelid);
			if (platformsChannels != null) {
				return platformsChannels;
			}
		}
		return null;
	}

	@Override
	public PageResult<?> readPagesChannel(int rows, int page, Integer platid, SearchFilter filter, String queryFilter) {
		return this.thirManagerDAO.readPagesChannel(rows, page, platid, filter, queryFilter);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findAllPlatformsThirdManager() {
		return this.thirManagerDAO.findAllPlatformsThirdManager();
	}

	@Override
	public List<?> findPlatformsThirdManagerBySession(Integer tmanId) { 
		return this.thirManagerDAO.readPlatformsManagerBySession(tmanId);
	}

}
