package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.PlatformsBean;
import com.baofeng.oa.dao.PlatformsActoresDAO;
import com.baofeng.oa.dao.PlatformsDAO;
import com.baofeng.oa.entity.BaseEnums.PlatIncomeGenre;
import com.baofeng.oa.entity.BaseEnums.PlatformGenre;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("platformsService")
public class PlatformsServiceImpl implements IPlatformsService {
	@Autowired
	private PlatformsDAO platformsDAO;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private PlatformsActoresDAO platformsActoresDAO;
	@Autowired
	private WorkManagers workManagers;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, List<RoleDetailsAtts> platList, List<RoleDetailsAtts> attsList,
			SearchFilter filter) {
		NPageResult rows = this.platformsDAO.readAllPages(pageSize, curPage, sortName, sortOrder, name, filter);
		if (rows != null && rows.getData().size() > 0) {
			List<PlatformsBean> list = new ArrayList<PlatformsBean>();
			boolean isViews = false;
			for (RoleDetailsAtts atts : attsList) {
				if ("views".equals(atts.getOpkey())) {
					isViews = true;
					break;
				}
			}
			List<Integer> platIds = new ArrayList<Integer>();
			for (RoleDetailsAtts atts : platList) {
				platIds.add(Integer.valueOf(atts.getOpkey()));
			}
			for (Object o : rows.getData()) {
				Platforms post = (Platforms) o;
				if (isViews || platIds.contains(post.getId().intValue())) {
					PlatformsBean bean = new PlatformsBean();
					List<String> channelList = new ArrayList<String>();
					if (post != null && post.getChannels() != null && post.getChannels().size() > 0) {
						for (PlatformsChannels platformsChannels : post.getChannels()) {
							if (platformsChannels != null) {
								channelList.add(platformsChannels.getChannels());
							}
						}
					} else
						channelList.add("N/A");
					bean.setTax(post.getTax() == null ? "" : String.format("%.2f%%", post.getTax()));
					bean.setChars(channelList);
					bean.setId(post.getId());
					bean.setDate(post.getCreateDT());
					bean.setName(post.getPlatName());
					bean.setRemarks(post.getRemarks());
					if (post.getIncomeGenre() != null) {
						bean.setIncomeGenre(post.getIncomeGenre() == PlatIncomeGenre.KAIPIAOSHUI ? "开票税" : post.getIncomeGenre() == PlatIncomeGenre.SHUIHOU ? "税后" : "税前");
					}
					if (post.getFormGenre() != null)
						bean.setFormGenre(post.getFormGenre() == PlatformGenre.PAIMAI ? "排麦" : "个人直播间");
					if (post.getPlatManager() != null)
						bean.setPlatManager(post.getPlatManager().getName());
					list.add(bean);
				}
			}
			rows.setData(new ArrayList(list));
		}
		return rows;
	}

	public Platforms readPlatforms(Integer id) {
		if (id != null && id.intValue() > 0) {
			Platforms platforms = this.platformsDAO.findPlatforms(id);
			return platforms;
		}
		return null;
	}

	@Override
	public boolean deletePlatforms(Integer id, HttpServletRequest request) {
		if (id != null && id.intValue() > 0) {
			Platforms plat = this.readPlatforms(id);
			if (plat != null) {
				boolean ftnIndex = false;
				if (plat.getActores() != null && plat.getActores().size() > 0) {
					ftnIndex = true;
				}
				if (plat.getManager() != null && plat.getManager().size() > 0) {
					ftnIndex = true;
				}
				if (!ftnIndex) {
					plat.setStatus(EntityStatus.DELETED);
					if (this.platformsDAO.deletePlatforms(plat)) {
						this.monitorLogService.logsDelete(request, "平台: " + plat.getPlatName() + " ID: " + plat.getId());
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings("serial")
	public boolean addPlatforms(Platforms poster, final String[] channels, HttpServletRequest request) {
		Set<String> $chanList = new TreeSet<String>() {
			{
				if (channels != null && channels.length > 0) {
					for (String $chan : channels) {
						add($chan);
					}
				}
			}
		};
		if (poster != null && poster.getId() != null && poster.getId().intValue() > 0) {
			Platforms $post = this.readPlatforms(poster.getId());
			if ($post != null) {
				$post.setPlatName(poster.getPlatName());
				$post.setIncomeGenre(poster.getIncomeGenre());
				$post.setFormGenre(poster.getFormGenre());
				$post.setPlatManager(poster.getPlatManager());
				$post.setRemarks(poster.getRemarks());
				$post.setTax(poster.getTax());
				List<PlatformsChannels> chanList = new ArrayList<PlatformsChannels>($post.getChannels());
				if (chanList != null && chanList.size() > 0) {
					for (PlatformsChannels $chan : chanList) {
						if (!$chanList.contains($chan.getChannels())) {
							$post.getChannels().remove($chan);
						}
						$chanList.remove($chan.getChannels());
					}
				}
				for (String $chan : $chanList) {
					PlatformsChannels $channels = new PlatformsChannels();
					$channels.setChannels($chan);
					$channels.setPlat($post);
					this.platformsDAO.getBaseDAO().save($channels);
				}
				poster = $post;
			}
			if (this.platformsDAO.savePlatforms(poster)) {
				this.monitorLogService.logsUpdate(request, "平台: " + poster.getPlatName() + " ID: " + poster.getId());
				this.workManagers.onEvents(WorkNames.PLATFORMS, poster);
				return true;
			}
		} else {
			if (channels != null && channels.length > 0) {
				for (String channel : $chanList) {
					PlatformsChannels platformsChannels = new PlatformsChannels();
					platformsChannels.setChannels(channel);
					platformsChannels.setBranchs(poster.getBranchs());
					poster.getChannels().add(platformsChannels);
				}
			}
			if (this.platformsDAO.savePlatforms(poster)) {
				this.monitorLogService.logsAdd(request, "平台: " + poster.getPlatName() + " ID: " + poster.getId());
				this.workManagers.onEvents(WorkNames.PLATFORMS, poster);
				return true;
			}
		}

		return false;
	}

	@Override
	public PageResult<?> readPagesPlat(int rows, int page, SearchFilter filter, String queryFilter) {
		return this.platformsDAO.readPagesPlat(rows, page, filter, queryFilter);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public NPageResult<?> PagesPlat(int pageSize, int curPage, String sortName, String sortOrder, Integer id, SearchFilter filter, String queryFilter) {
		NPageResult $page = this.platformsDAO.readAllPagesPlat(pageSize, curPage, sortName, sortOrder, queryFilter, filter, queryFilter);
		return $page;
	}

	@Override
	public PlatformsChannels readChannels(Integer channelsId) {
		if (channelsId != null) {
			return this.platformsDAO.readChannels(channelsId);
		}
		return null;
	}

	@Override
	public boolean savePlatformsActores(PlatformsActores platformsActores) {
		return this.platformsDAO.savePlatformsActores(platformsActores);
	}

	/**
	 * 功能：删除频道验证
	 * */
	@Override
	public boolean readPlatformsByChan(String platId, String channels) {
		PlatformsChannels chan = this.platformsDAO.readPlatformsChannels(platId, channels);
		if (chan != null) {
			boolean ftnIndex = false;
			if (chan.getActores() != null && chan.getActores().size() > 0) {
				ftnIndex = true;
			}
			if (chan.getManager() != null && chan.getManager().size() > 0) {
				ftnIndex = true;
			}
			return ftnIndex;
		}
		return false;
	}

	@Override
	public List<Platforms> findAllPlatforms(SearchFilter filter) {
		return this.platformsDAO.findAllPlatforms(filter);
	}

	@Override
	public Platforms findPlatforms(Integer platId) {
		return this.readPlatforms(platId);
	}

	@Override
	public PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter fil, String filter, List<RoleDetailsAtts> platList) {
		return this.platformsDAO.readAllPagesSkip(rows, page, fil, filter, platList);
	}

	@Override
	public List<Platforms> findAllPlatforms() {
		return this.platformsDAO.findAllPlatforms();
	}
	
	@Override
	public PlatformsActores readPlatformsActores(Integer pactId) {
		return this.platformsActoresDAO.readPlatformsActores(pactId);
	}

	@Override
	public PageResult<?> readPagesAllPlat(int rows, int page,SearchFilter $filter, String filter) {
		return this.platformsDAO.readPagesAllPlat(rows, page, $filter, filter);
	}
}
