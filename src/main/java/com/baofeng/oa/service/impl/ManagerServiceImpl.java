package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.ManagerOnlineBean;
import com.baofeng.oa.bean.ManagersBean;
import com.baofeng.oa.dao.ManagerDAO;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("managerService")
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private ManagerDAO managerDAO;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IPlatformsManagerService platformsManagerService;

	@Override
	public Managers readManagers(Integer id) {
		if (id != null && id > 0) {
			Managers manager = this.managerDAO.readManager(id);
			manager.setProbation(manager.getProbation() == null ? 0 : manager.getProbation());
			return manager;
		}
		return null;
	}

	@Override
	public PlatformsChannels readPlatformsChannels(Integer channelid) {
		if (channelid != null && channelid.intValue() > 0) {
			PlatformsChannels platformsChannels = this.managerDAO.readPlatformsChannels(channelid);
			if (platformsChannels != null) {
				return platformsChannels;
			}
		}
		return null;
	}

	@Override
	public boolean deleteManagers(Integer id, HttpServletRequest request) {
		if (id != null && id.intValue() > 0) {
			Managers managers = this.readManagers(id);
			if (managers != null) {
				managers.setStatus(EntityStatus.DELETED);
				if (this.managerDAO.deletePlatforms(managers)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean addManager(Managers post, HttpServletRequest request) {
		boolean ftnIndex = false;
		if (post != null && post.getId() != null && post.getId() > 0) {
			Managers $post = this.readManagers(post.getId());
			if ($post != null) {
				ftnIndex = true;
				$post.setAliasname(post.getAliasname());
				$post.setRealname(post.getRealname());
				$post.setGenre(post.getGenre());
				$post.setQq(post.getQq());
				$post.setPhone(post.getPhone());
				$post.setSex(post.getSex());
				$post.setPositions(post.getPositions());
				$post.setProbation(post.getProbation());
				$post.setGenrer(post.getGenrer());
				$post.setProbationSalary(post.getProbationSalary());
				$post.setFoodSubsidy(post.getFoodSubsidy());
				$post.setEntryTime(post.getEntryTime());
				$post.setBankCard(post.getBankCard());
				$post.setBankCardAccount(post.getBankCardAccount());
				if (post.getGenre() == LineGenres.ONLINE) {
					$post.setBasicSalary(post.getBasicSalary());
				}
				post = $post;
				if (this.managerDAO.saveManager(post)) {
					this.monitorLogService.logsUpdate(request, "助理: " + post.getRealname() + " ID: " + post.getId());
					if (ftnIndex) {
						this.workManagers.onEvents(WorkNames.MANAGER_MID, post);
					}
					return true;
				}
			}
		}
		if (this.managerDAO.saveManager(post)) {
			this.monitorLogService.logsAdd(request, "助理: " + post.getRealname() + " ID: " + post.getId());
			if (ftnIndex) {
				this.workManagers.onEvents(WorkNames.MANAGER_MID, post);
			}
			return true;
		}
		return false;

	}

	@Override
	public boolean addManager(Managers post, Integer platId, Integer channe, HttpServletRequest request) {
		boolean ftnIndex = false;
		PlatformsManager manager = new PlatformsManager();
		manager.setBranchs(post.getBranchs());
		if (platId != null && platId.intValue() > 0) {
			Platforms plat = this.platformsService.readPlatforms(platId);
			manager.setPlat(plat);
		}
		if (channe != null && channe.intValue() > 0) {
			PlatformsChannels channel = this.platformsManagerService.readPlatformsChannels(Integer.valueOf(channe));
			manager.setChannel(channel);
		}else {
			manager.setChannel(null);
		}
		manager.setManager(post);
		if (post != null && post.getId() != null && post.getId() > 0) {
			Managers $post = this.readManagers(post.getId());
			if ($post != null) {
				ftnIndex = true;
				$post.setAliasname(post.getAliasname());
				$post.setRealname(post.getRealname());
				$post.setGenre(post.getGenre());
				$post.setQq(post.getQq());
				$post.setPhone(post.getPhone());
				$post.setSex(post.getSex());
				$post.setPositions(post.getPositions());
				$post.setProbation(post.getProbation());
				$post.setGenrer(post.getGenrer());
				$post.setProbationSalary(post.getProbationSalary());
				$post.setFoodSubsidy(post.getFoodSubsidy());
				$post.setEntryTime(post.getEntryTime());
				$post.setBankCard(post.getBankCard());
				$post.setBankCardAccount(post.getBankCardAccount());
				if (post.getGenre() == LineGenres.ONLINE) {
					$post.setBasicSalary(post.getBasicSalary());
				}
				post = $post;
				if (this.managerDAO.saveManager(post)) {
					this.monitorLogService.logsUpdate(request, "助理: " + post.getRealname() + " ID: " + post.getId());
					if (ftnIndex) {
						this.workManagers.onEvents(WorkNames.MANAGER_MID, post);
					}
					return true;
				}
			}
		} else {
			if (this.managerDAO.saveManager(post)) {
				this.monitorLogService.logsAdd(request, "助理: " + post.getRealname() + " ID: " + post.getId());
				if (this.platformsManagerService.addPlatManagers(manager, request)) {
					return true;
				}
				if (ftnIndex) {
					this.workManagers.onEvents(WorkNames.MANAGER_MID, post);
				}
				return true;
			}
		}
		return false;

	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String genrer, SearchFilter filter, RoleUsers users, List<RoleDetailsAtts> platList,
			List<RoleDetailsAtts> attsList, String genre, String fastArg) {
		NPageResult rows = null;
		Operator user = users.getUser();
		boolean isViews = false;
		for (RoleDetailsAtts atts : attsList) {
			if ("views".equals(atts.getOpkey())) {
				isViews = true;
				break;
			}
		}
		if (user.getGenrer() == Genres.MANAGER || user.getGenrer() == Genres.SUPERS || Boolean.valueOf(isViews)) {
			rows = this.managerDAO.readAllPages(pageSize, curPage, sortName, sortOrder, genrer, filter, genre, fastArg);
		} else {
			rows = this.managerDAO.readAllPages(pageSize, curPage, sortName, sortOrder, genrer, filter, platList, user, genre, fastArg);
		}
		if (rows != null && rows.getData().size() > 0) {
			List<ManagersBean> list = new ArrayList<ManagersBean>();
			BranchOffice branchOffice = null;
			for (Object o : rows.getData()) {
				Managers post = null;
				if (o instanceof Managers) {
					post = (Managers) o;
				} else if (o instanceof PlatformsManager) {
					PlatformsManager pm = (PlatformsManager) o;
					post = pm.getManager();
				}
				ManagersBean bean = new ManagersBean();
				if (branchOffice == null) {
					bean.setNumber(post.getNumber() == null ? "N/A" : String.format("%04d", post.getNumber()));
					branchOffice = this.branchOfficeService.readBranchOffice(post.getBranchs());
				}
				if (branchOffice != null)
					bean.setNumber(post.getNumber() == null ? "N/A" : branchOffice.getNumberHead() + String.format("%04d", post.getNumber()));
				bean.setId(post.getId());
				bean.setEntryTime(post.getEntryTime());
				bean.setSex(post.getSex() == Sex.MAN ? "男" : "女");
				if (post.getProbation() != null) {
					bean.setProbation(post.getProbation().intValue() == 0 ? "-" : post.getProbation() + "个月");
				} else {
					bean.setProbation("-");
				}
				bean.setPhone(post.getPhone());
				bean.setQq(post.getQq());
				bean.setLeaveReasons(post.getLeaveReasons());
				bean.setGenrer(post.getGenrer() == Genres.ZHENGSHI ? "正式" : "试用");
				bean.setProbationSalary(post.getProbationSalary() == null ? "0.00" : String.format("%.2f", post.getProbationSalary()));
				bean.setFoodSubsidy(post.getFoodSubsidy() == null ? "0.00" : String.format("%.2f", post.getFoodSubsidy()));

				bean.setGenre(post.getGenre() == LineGenres.OFFLINE ? "线下管理" : "线上管理");
				bean.setAliasname(post.getAliasname());
				bean.setBankCard(post.getBankCard());
				bean.setBankCardAccount(post.getBankCardAccount());
				bean.setRealname(post.getRealname());
				bean.setAddress(post.getAddress());
				bean.setIdcard(post.getIdcard());
				bean.setIdImage(post.getIdImage());
				bean.setPositions(post.getPositions());
				bean.setBasicSalary(post.getBasicSalary() == null ? "0.00" : String.format("%.2f", post.getBasicSalary()));
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;
	}

	@Override
	public boolean saveManagerBySession(Managers manager) {
		return this.managerDAO.saveManagerBySession(manager);
	}

	@Override
	public Managers findManagerByEmpIdSession(Integer empId) {
		return this.managerDAO.findManagerByEmpIdSession(empId);
	}

	@Override
	public List<Managers> readAllManagers() {
		return this.managerDAO.readAllManagers();
	}

	/**
	 * 功能：验证选择用户是否添加过
	 * */
	@Override
	public boolean readManager(Integer id) {
		return this.managerDAO.readManagers(id);
	}

	/***/
	@Override
	public List<Managers> readManagersByEmpId(Integer empId) {
		return this.managerDAO.readManagersByEmpId(empId);
	}

	@Override
	public boolean saveManager(Managers manager) {
		return this.managerDAO.saveManager(manager);
	}

	@Override
	public boolean addLeaveManager(Managers manager) {
		if (manager != null) {
			if (this.managerDAO.saveManager(manager)) {
				this.workManagers.onEvents(WorkNames.EMPLOYEE_ADDACTORES, manager);
				return true;
			}
		}
		return false;
	}

	@Override
	public ManagersBean readManagersBean(Integer id) {
		if (id != null && id > 0) {
			Managers manager = this.managerDAO.readManager(id);
			manager.setProbation(manager.getProbation() == null ? 0 : manager.getProbation());
			ManagersBean bean = new ManagersBean();
			if (manager != null) {
				bean.setAddress(manager.getAddress());
				bean.setAliasname(manager.getAliasname());
				bean.setBasicSalary(manager.getBasicSalary() != null ? String.format("%.2f", manager.getBasicSalary()) : "0.00");
				bean.setEntryTime(manager.getEntryTime());
				bean.setFoodSubsidy(manager.getFoodSubsidy() != null ? String.format("%.2f", manager.getFoodSubsidy()) : "0.00");
				bean.setGenre(manager.getGenre().toString());
				bean.setId(manager.getId());
				bean.setIdcard(manager.getIdcard());
				bean.setNumber(manager.getNumber() != null ? String.format("%04d", manager.getNumber()) : "N/A");
				bean.setPhone(manager.getPhone());
				bean.setPositions(manager.getPositions());
				bean.setQq(manager.getQq());
				bean.setRealname(manager.getRealname());
				bean.setSex(manager.getSex().toString());
				bean.setBankCard(manager.getBankCard());
				bean.setBankCardAccount(manager.getBankCardAccount());
				if (manager.getEmployee() != null)
					bean.setEmpid(manager.getEmployee().getId().toString());
			}
			return bean;
		}
		return null;
	}

	@Override
	public List<Managers> findAllManagers() {
		return this.managerDAO.readAllManagers();
	}

	@Override
	public Managers findLoadManagers(Integer id) {
		return this.managerDAO.readManager(id);
	}

	@Override
	public List<Managers> findAllOnlineManagers() {
		return this.managerDAO.findAllOnlineManagers();
	}

	@Override
	public List<Managers> readAllManagersByPhone(String id, String phone) {
		if (phone != null && phone.trim().length() > 0) {
			return this.managerDAO.readAllManagersByPhone(id, phone);
		}
		return null;
	}

	@Override
	public boolean checkManager(Managers post) {
		if (post != null && post.getId() != null && post.getId().intValue() > 0) {
			return false;
		} else {
			if (this.managerDAO.checkManager(post)) {
				return true;
			}
			return false;
		}
	}

	public ManagersBean editManagerById(Integer id) {
		if (id != null && id > 0) {
			Managers post = this.managerDAO.readManager(id);
			ManagersBean bean = new ManagersBean();
			bean.setId(post.getId());
			bean.setIdImage(Constants.DEFAULT_HTTPIMAGES + "/" + post.getIdImage());
			if (post.getNumber() != null && post.getNumber().intValue() > 0)
				bean.setNumber(String.format("%04d", post.getNumber()));
			else
				bean.setNumber("N/A");
			bean.setAddress(post.getAddress());
			bean.setPhone(post.getPhone());
			bean.setQq(post.getQq());
			bean.setIdcard(post.getIdcard());
			bean.setRealname(post.getRealname());
			bean.setAliasname(post.getAliasname());
			bean.setEntryTime(post.getEntryTime());
			bean.setPositions(post.getPositions());
			bean.setBankCardAccount(post.getBankCardAccount());
			bean.setBankCard(post.getBankCard());
			bean.setSex(post.getSex() == Sex.MAN ? "男" : "女");
			bean.setGenre(post.getGenre() == LineGenres.ONLINE ? "线上管理" : "线下管理");

			if (post.getGenrer() == Genres.SHIYONG) {
				bean.setGenrer("试用");
			} else if (post.getGenrer() == Genres.LIZHI) {
				bean.setGenrer("离职");
			} else if (post.getGenrer() == Genres.ZHENGSHI) {
				bean.setGenrer("正式");
			}

			if (post.getProbation() != null) {
				bean.setProbation(post.getProbation().intValue() == 0 ? "-" : post.getProbation().toString());
			} else {
				bean.setProbation("-");
			}
			bean.setCreateDT(post.getCreateDT());

			bean.setProbationSalary(post.getProbationSalary() == null ? "0.00" : String.format("%.2f", post.getProbationSalary()));
			bean.setBasicSalary(post.getBasicSalary() == null ? "0.00" : String.format("%.2f", post.getBasicSalary()));

			return bean;
		}
		return null;

	}

	@Override
	public ManagerOnlineBean editManagerOnlineById(Integer id) {
		if (id != null && id > 0) {
			Managers post = this.managerDAO.readManager(id);
			ManagerOnlineBean bean = new ManagerOnlineBean();
			bean.setId(post.getId().toString());
			bean.setIdImage(Constants.DEFAULT_HTTPIMAGES + "/" + post.getIdImage());
			if (post.getNumber() != null && post.getNumber().intValue() > 0)
				bean.setNumber(String.format("%04d", post.getNumber()));
			else
				bean.setNumber("N/A");
			if (post.getAddress() == null) {
				bean.setAddress("");
			}else{
				bean.setAddress(post.getAddress());
			}
			bean.setPhone(post.getPhone());
			bean.setQq(post.getQq());
			if(post.getIdcard() == null){
				bean.setIdcard("");
			}else{
				bean.setIdcard(post.getIdcard());
			}
			bean.setRealname(post.getRealname());
			bean.setAliasname(post.getAliasname());
			bean.setEntryTime(post.getEntryTime().toString());
			bean.setPositions(post.getPositions());
			bean.setBankCardAccount(post.getBankCardAccount());
			bean.setBankCard(post.getBankCard());
			bean.setSex(post.getSex() == Sex.MAN ? "男" : "女");
			bean.setGenre(post.getGenre() == LineGenres.ONLINE ? "线上管理" : "线下管理");

			if (post.getGenrer() == Genres.SHIYONG) {
				bean.setGenrer("试用");
			} else if (post.getGenrer() == Genres.LIZHI) {
				bean.setGenrer("离职");
			} else if (post.getGenrer() == Genres.ZHENGSHI) {
				bean.setGenrer("正式");
			}

			if (post.getProbation() != null) {
				bean.setProbation(post.getProbation().intValue() == 0 ? "-" : post.getProbation().toString());
			} else {
				bean.setProbation("-");
			}

			bean.setProbationSalary(post.getProbationSalary() == null ? "0.00" : String.format("%.2f", post.getProbationSalary()));
			bean.setBasicSalary(post.getBasicSalary() == null ? "0.00" : String.format("%.2f", post.getBasicSalary()));

			return bean;
		}
		return null;
	}
}
