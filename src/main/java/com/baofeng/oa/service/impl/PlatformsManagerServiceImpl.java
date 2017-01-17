package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.PlatformsManagerBean;
import com.baofeng.oa.dao.PlatformsManagerDAO;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("platformsManagerService")
public class PlatformsManagerServiceImpl implements IPlatformsManagerService {

	@Autowired
	private PlatformsManagerDAO platformsManagerDAO;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private WorkManagers workManagers;

	@Override
	public PlatformsManager readPlatformsManager(Integer id) {
		if (id != null && id > 0) {
			PlatformsManager manager = this.platformsManagerDAO.readManager(id);
			manager.getManager().setProbation(manager.getManager().getProbation() == null ? 0 : manager.getManager().getProbation());
			return manager;
		}
		return null;
	}

	@Override
	public PlatformsChannels readPlatformsChannels(Integer channelid) {
		if (channelid != null && channelid.intValue() > 0) {
			PlatformsChannels platformsChannels = this.platformsManagerDAO.readPlatformsChannels(channelid);
			if (platformsChannels != null) {
				return platformsChannels;
			}
		}
		return null;
	}

	@Override
	public boolean deletePlatformsManager(Integer id, HttpServletRequest request) {
		if (id != null && id.intValue() > 0) {
			PlatformsManager platformsManager = this.readPlatformsManager(id);
			PlatformsManager $platformsManager = new PlatformsManager();
			$platformsManager.setId(platformsManager.getId());
			$platformsManager.setManager(platformsManager.getManager());
			$platformsManager.setChannel(platformsManager.getChannel());
			$platformsManager.setPlat(platformsManager.getPlat());
			if (platformsManager != null) {
				platformsManager.setPlat(null);
				platformsManager.setChannel(null);
				platformsManager.setManager(null);
				this.platformsManagerDAO.savePlatformsManager(platformsManager);
				if (this.platformsManagerDAO.deletePlatforms(platformsManager)) {
					this.workManagers.onEvents(WorkNames.MANAGER_PDEL, platformsManager);
					if ($platformsManager.getChannel() != null) {
						this.monitorLogService.logsDelete(request, "平台" + $platformsManager.getPlat().getPlatName() + "频道：" + $platformsManager.getChannel().getChannels()
								+ "下的平台助理: " + $platformsManager.getManager().getRealname() + " ID: " + $platformsManager.getId());
					} else {
						this.monitorLogService.logsDelete(request, "平台" + $platformsManager.getPlat().getPlatName() + "下的平台助理: " + $platformsManager.getManager().getRealname()
								+ " ID: " + $platformsManager.getId());
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean addPlatManager(PlatformsManager post, HttpServletRequest request) {
		if (this.platformsManagerDAO.savePlatformsManager(post)) {
			if (post.getChannel() != null) {
				this.monitorLogService.logsAdd(request, "平台" + post.getPlat().getPlatName() + "频道：" + post.getChannel().getChannels() + "下的平台助理: "
						+ post.getManager().getRealname() + " ID: " + post.getId());
			} else {
				this.monitorLogService.logsAdd(request, "平台" + post.getPlat().getPlatName() + "下的平台助理: " + post.getManager().getRealname() + " ID: " + post.getId());
			}
			this.workManagers.onEvents(WorkNames.MANAGER, post);
			return true;
		}
		return false;

	}

	@Override
	public boolean addPlatManagers(PlatformsManager post, HttpServletRequest request) {
		List<PlatformsManager> list = new ArrayList<PlatformsManager>();
		if (post.getChannel() != null) {
			list = this.platformsManagerDAO.readAllPlatformsManager(post.getManager().getId(), post.getPlat().getId(), post.getChannel().getId());
		} else {
			list = this.platformsManagerDAO.readAllPlatformsManager(post.getManager().getId(), post.getPlat().getId(), null);
		}
		if (list != null && list.size() > 0) {
			return false;
		} else {
			if (this.platformsManagerDAO.savePlatformsManager(post)) {
				if (post.getChannel() != null) {
					this.monitorLogService.logsAdd(request, "平台" + post.getPlat().getPlatName() + "频道：" + post.getChannel().getChannels() + "下的平台助理: "
							+ post.getManager().getRealname() + " ID: " + post.getId());
				} else {
					this.monitorLogService.logsAdd(request, "平台" + post.getPlat().getPlatName() + "下的平台助理: " + post.getManager().getRealname() + " ID: " + post.getId());
				}
				this.workManagers.onEvents(WorkNames.MANAGER, post);
				return true;
			}
		}
		return false;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, Integer platid, String fastArg, SearchFilter filter) {
		NPageResult rows = this.platformsManagerDAO.readAllPages(pageSize, curPage, sortName, sortOrder, platid, fastArg, filter);
		if (rows != null && rows.getData().size() > 0) {
			List<PlatformsManagerBean> list = new ArrayList<PlatformsManagerBean>();
			BranchOffice branchOffice = null;
			for (Object o : rows.getData()) {
				PlatformsManager post = (PlatformsManager) o;
				PlatformsManagerBean bean = new PlatformsManagerBean();
				bean.setId(post.getId());
				bean.setEntryTime(post.getManager().getEntryTime());
				bean.setSex(post.getManager().getSex() == Sex.MAN ? "男" : "女");
				if (post.getManager().getProbation() != null) {
					bean.setProbation(post.getManager().getProbation().intValue() == 0 ? "-" : post.getManager().getProbation() + "个月");
				} else {
					bean.setProbation("-");
				}
				bean.setBankCard(post.getManager().getBankCard());
				bean.setBankCardAccount(post.getManager().getBankCardAccount());
				bean.setQq(post.getManager().getQq());
				bean.setPhone(post.getManager().getPhone());
				bean.setRegular(post.getManager().getGenrer() == Genres.ZHENGSHI ? "正式" : "试用");
				if (branchOffice == null) {
					bean.setNumber(post.getManager().getNumber() == null ? "N/A" : String.format("%04d", post.getManager().getNumber()));
					branchOffice = this.branchOfficeService.readBranchOffice(post.getBranchs());
				}
				if (branchOffice != null)
					bean.setNumber(post.getManager().getNumber() == null ? "N/A" : branchOffice.getNumberHead() + String.format("%04d", post.getManager().getNumber()));
				bean.setAliasname(post.getManager().getAliasname());
				bean.setRealname(post.getManager().getRealname());
				bean.setAddress(post.getManager().getAddress());
				bean.setIdcard(post.getManager().getIdcard());
				bean.setIdImage(post.getManager().getIdImage());
				bean.setPositions(post.getManager().getPositions());
				if (post.getPlat() != null) {
					bean.setPlat(post.getPlat().getPlatName());
				}
				bean.setChannel(post.getChannel() != null ? post.getChannel().getChannels() : "-");
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;
	}

	@Override
	public List<PlatformsManager> findAllPlatformsManager() {
		return this.platformsManagerDAO.findAllPlatformsManager();
	}

	/**
	 * 功能：验证选择用户是否添加过
	 * */
	@Override
	public boolean readPlatformsManager(Integer id, Integer platId, Integer channelId) {
		return this.platformsManagerDAO.readPlatformsManager(id, platId, channelId);
	}

	/***/
	@Override
	public List<PlatformsManager> readPlatformsManagerByEmpId(Integer empId) {
		return this.platformsManagerDAO.readPlatformsManagerByEmpId(empId);
	}

	@Override
	public boolean savePlatformsManager(PlatformsManager manager) {
		return this.platformsManagerDAO.savePlatformsManager(manager);
	}

	@Override
	public List<PlatformsManagerBean> readPlatformsManagerBeanList(String realname, SearchFilter filter, Integer branchId) {
		if (realname != null && realname.trim().length() > 0) {
			List<Managers> managerList = this.platformsManagerDAO.readPlatformsManagerBeanList(realname, filter);
			List<PlatformsManagerBean> list = new ArrayList<PlatformsManagerBean>();
			BranchOffice branch = null;
			if (managerList != null) {
				for (Managers manager : managerList) {
					PlatformsManagerBean bean = new PlatformsManagerBean();
					bean.setId(manager.getId());
					bean.setAliasname(manager.getAliasname());
					if (manager.getNumber() != null) {
						if (branch == null)
							branch = this.branchOfficeService.readBranchOffice(branchId);
						if (branch != null)
							bean.setNumber(branch.getNumberHead() + String.format("%04d", manager.getNumber()));
						else
							bean.setNumber(String.format("%04d", manager.getNumber()));
					} else {
						bean.setNumber("N/A");
					}
					bean.setRealname(manager.getRealname());
					list.add(bean);
				}
			}
			return list;
		}
		return null;
	}

	@Override
	public boolean verify(Integer id, Integer platId, Integer channelId) {
		if (id != null && id.intValue() > 0 && platId != null && platId.intValue() > 0) {
			List<PlatformsManager> list = this.platformsManagerDAO.readAllPlatformsManager(id, platId, channelId);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<?> findPlatformsManagerBySession(Integer manId) {
		return this.platformsManagerDAO.findPlatformsManagerBySession(manId);
	}

	@Override
	public boolean savePlatformsManagerBySession(PlatformsManager plam) {
		return this.platformsManagerDAO.savePlatformsManagerBySession(plam);
	}

	@Override
	public boolean deletePlatformsManagerBySession(Integer manId) {
		List<?> managerList = this.platformsManagerDAO.findPlatformsManagerBySession(manId);
		if (managerList != null && managerList.size() > 0) {
			PlatformsManager plam = (PlatformsManager) managerList.get(0);
			if (plam != null) {
				plam.setManager(null);
				plam.setPlat(null);
				plam.setChannel(null);
				this.platformsManagerDAO.savePlatformsManagerBySession(plam);
				if (this.platformsManagerDAO.deletePlatformsManagerBySession(plam)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public PlatformsManager readPlatformsManagerByMId(Integer mid) {
		if (mid != null) {
			PlatformsManager pm = this.platformsManagerDAO.readPlatformsManagerByMId(mid);
			if (pm != null)
				return pm;
		}
		return null;
	}

}
