package com.baofeng.oa.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.dao.PositionsDAO;
import com.baofeng.oa.entity.Positions;
import com.baofeng.oa.service.IPositionsService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;
@Service("postionsService")
public class PositionsServiceImpl implements IPositionsService { 

	@Autowired
	private PositionsDAO positionsDAO;

	@Autowired
	private IMonitorLogService monitorLogService;

	public boolean addPositions(Positions poster,HttpServletRequest request) {
		if (poster != null && poster.getId() != null && poster.getId() > 0) {
			Positions $post = this.readPositions(poster.getId());
			if ($post != null) {
				$post.setId(poster.getId());
				$post.setName(poster.getName());
				$post.setDescribed(poster.getDescribed());;
				poster = $post;
			}
			 
			if(this.positionsDAO.savePositions(poster)){
				this.monitorLogService.logsUpdate(request,"职位: "+ poster.getName()+" ID: "+poster.getId()); 
				return true;
			}
			return false;
		}
		if(this.positionsDAO.savePositions(poster)){
			this.monitorLogService.logsAdd(request,"职位: "+ poster.getName()+" ID: "+poster.getId()); 
			return true;
		}
		 return false;
	}

	public Positions readPositions(Integer id) {
		if(id != null && id>0 ){
			Positions positions = this.positionsDAO.readPositions(id);
			return positions;
		}
		return null;
	}
	@Override
	public boolean deletePositions(Integer id,HttpServletRequest request) {
		if (id != null && id > 0) {
			Positions positions = this.readPositions(id);
			if (positions != null) {
				positions.setStatus(EntityStatus.DELETED); 
			}
			if(this.positionsDAO.deletePositions(positions)){ 
				this.monitorLogService.logsDelete(request,"职位: "+ positions.getName()+" ID: "+positions.getId()); 
				return true;
			} 
			return false;
		}
		return false;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, SearchFilter filter) {
		NPageResult rows = this.positionsDAO.readAllPages(pageSize, curPage, sortName, sortOrder, name, filter);
		return rows;
	}


}
