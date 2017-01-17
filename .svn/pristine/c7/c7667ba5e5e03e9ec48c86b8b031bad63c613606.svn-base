package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.PactBean;
import com.baofeng.oa.bean.PlatformsActoresBean;
import com.baofeng.oa.dao.PlatformsActoresDAO;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.ActoresSigned;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("actoresService")
public class PlatformsActoresServiceImpl implements IPlatformsActoresService {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private PlatformsActoresDAO actoresDAO;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private PlatformsActoresDAO platformsActoresDAO;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@Override
	public PageResult<?> readPagesChannel(int rows, int page, SearchFilter $filter, String filter) {
		return this.actoresDAO.readPagesChannel(rows, page, $filter, filter);
	}

	@Override
	public PageResult<?> readPagesEmp(int rows, int page, SearchFilter $filter, String filter) {
		return this.actoresDAO.readPagesEmp(rows, page, $filter, filter);
	}

	@Override
	public boolean addActores(Actores post) {
		if (post != null && post.getId() != null && post.getId().intValue() > 0) {
			Actores $post = this.actoresDAO.readActores(post.getId());
			if ($post != null) {
				$post.setRealname(post.getRealname());
				$post.setAliasname(post.getAliasname());
				$post.setPhone(post.getPhone());
				$post.setEmail(post.getEmail());
				$post.setSex(post.getSex());
				$post.setAddress(post.getAddress());
				$post.setIdcard(post.getIdcard());
				if (post.getIdImage() != null && post.getIdImage().trim().length() > 0)
					$post.setIdImage(post.getIdImage());
				$post.setGenre(post.getGenre());
				$post.setEntryTime(post.getEntryTime());
				$post.setMinimumGuarantee(post.getMinimumGuarantee());
				$post.setProbationSalary(post.getProbationSalary());
				$post.setSigned(post.getSigned());
				$post.setEmployee(post.getEmployee());
				$post.setLabour(post.getLabour());
				post = $post;
			}
		}
		return this.actoresDAO.saveActores(post);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, Integer platid, Integer type, Integer genrer, String name, SearchFilter filter) {
		LineGenres TYPE = null;
		if (type != null && type == 1) {
			TYPE = LineGenres.ONLINE;
		} else if (type != null && type == 2)
			TYPE = LineGenres.OFFLINE;
		Genres GEN = null;
		if (genrer != null && genrer == 3)
			GEN = Genres.LIZHI;
		NPageResult rows = this.actoresDAO.readAllPages(pageSize, curPage, sortName, sortOrder, platid, TYPE, GEN, name, filter);
		return rows;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPagesPlatformActores(int pageSize, int curPage, String sortName, String sortOrder, Integer platid, String fastArg, SearchFilter filter) {
		NPageResult rows = this.actoresDAO.readPagesPlatformActores(pageSize, curPage, sortName, sortOrder, platid, fastArg, filter);
		if (rows != null && rows.getData().size() > 0) {
			List<PlatformsActoresBean> listBean = new ArrayList<PlatformsActoresBean>();
			BranchOffice branchOffice = null;
			for (Object o : rows.getData()) {
				PlatformsActores post = (PlatformsActores) o;
				PlatformsActoresBean bean = new PlatformsActoresBean();
				if (post.getActores() != null) {
					bean.setId(post.getId());
					if (post.getActores().getNumber() != null && post.getActores().getNumber().intValue() > 0) {
						if (branchOffice == null) {
							bean.setNumber(String.format("%04d", post.getActores().getNumber()));
							branchOffice = this.branchOfficeService.readBranchOffice(post.getBranchs());
						}
						if (branchOffice != null)
							bean.setNumber(branchOffice.getNumberHead() + String.format("%04d", post.getActores().getNumber()));
					} else
						bean.setNumber("N/A");
					bean.setQq(post.getActores().getQq());
					bean.setAddress(post.getActores().getAddress());
					bean.setPhone(post.getActores().getPhone());
					bean.setRealname(post.getActores().getRealname());
					bean.setAliasname(post.getActores().getAliasname());
					bean.setEmail(post.getActores().getEmail());
					bean.setEntryTime(post.getActores().getEntryTime());
					bean.setBeLiveTime(post.getBeLiveTime());
					bean.setSex(post.getActores().getSex() == Sex.MAN ? "男" : "女");
					bean.setGenre(post.getActores().getGenre() == LineGenres.ONLINE ? "线上艺人" : "线下艺人");
					bean.setCostArtists(String.format("%.2f", post.getCostArtists() != null ? post.getCostArtists() : 0.00));
					if (post.getActores().getProbation() != null) {
						bean.setProbation(post.getActores().getProbation().intValue() == 0 ? "-" : post.getActores().getProbation() + "个月");
					} else {
						bean.setProbation("-");
					}

					bean.setProbationSalary(post.getActores().getProbationSalary() == null ? "0.00" : String.format("%.2f", post.getActores().getProbationSalary()));
					bean.setBasicSalary(post.getActores().getBasicSalary() == null ? "0.00" : String.format("%.2f", post.getActores().getBasicSalary()));
					bean.setMinimumGuarantee(post.getActores().getMinimumGuarantee() == null ? "0.00" : String.format("%.2f", post.getActores().getMinimumGuarantee()));
					bean.setPushMoney(post.getPushMoney() == null ? "-" : String.format("%d%% %n", post.getPushMoney()));

					if (post.getChannel() != null) {
						bean.setChannels(post.getChannel() != null ? post.getChannel().getChannels() : "-");
					} else {
						bean.setChannels(post.getNumber() != null ? String.valueOf(post.getNumber()) : "-");
					}
					bean.setSigned(post.getActores().getLabour() == ActoresLabour.SYSTEM ? "公司签约" : (post.getActores().getSigned() == ActoresSigned.YES ? "经济签约" : "还未签约"));
					bean.setCreateDT(post.getCreateDT());
					if (post.getPlat() != null) {
						bean.setPlatName(post.getPlat().getPlatName());
					}
				}
				listBean.add(bean);
			}
			rows.setData(listBean);
		}
		return rows;
	}

	@Override
	public Actores readActores(Integer id) {
		if (id != null && id.intValue() > 0) {
			Actores actores = this.actoresDAO.readActores(id);
			if (actores != null) {
				actores.setProbation(actores.getProbation() == null ? 0 : actores.getProbation());
			}
			return actores;
		}
		return null;
	}

	@Override
	public Platforms readPlatforms(Integer id) {
		if (id != null && id.intValue() > 0) {
			Platforms platforms = this.actoresDAO.readPlatforms(id);
			return platforms;
		}
		return null;
	}

	@Override
	public boolean addNewActores(PlatformsActores pact, Integer actId, Integer platId, String costArtists, Integer channelsId, String beLiveTime, Integer pushMoney,
			HttpServletRequest request) {
		if (pact != null && pact.getId() != null && pact.getId().intValue() > 0) {
			PlatformsActores $pact = this.actoresDAO.readPlatformsActores(pact.getId());
			if ($pact != null) {
				$pact.setBeLiveTime(Constants.convert(beLiveTime, Constants.format1));
				$pact.setPushMoney(pushMoney);
				if (pact.getMainPlatform() != null)
					$pact.setMainPlatform(Integer.valueOf(pact.getMainPlatform()));
				if (pact.getNumber() != null)
					$pact.setNumber(pact.getNumber());
				if (costArtists != null)
					$pact.setCostArtists(new BigDecimal(costArtists));
				else
					$pact.setCostArtists(new BigDecimal(0.00));
				pact = $pact;
			}
			if (this.actoresDAO.savePlatformsActores(pact)) {
				if (pact.getChannel() != null) {
					this.monitorLogService.logsUpdate(request, "平台: " + pact.getPlat().getPlatName() + "频道号：" + pact.getChannel().getChannels() + "下的 平台艺人"
							+ pact.getActores().getRealname() + " ID: " + pact.getId());
				} else {
					this.monitorLogService.logsUpdate(request, "平台: " + pact.getPlat().getPlatName() + "下的 平台艺人" + pact.getActores().getRealname() + " ID: " + pact.getId());
				}
				this.workManagers.onEvents(WorkNames.ACTORES, pact);
				return true;
			}
		} else {
			pact.setPushMoney(pushMoney);
			pact.setBeLiveTime(Constants.convert(beLiveTime, Constants.format1));
			if (pact.getMainPlatform() != null)
				pact.setMainPlatform(Integer.valueOf(pact.getMainPlatform()));
			if (pact.getNumber() != null)
				pact.setNumber(pact.getNumber());
			if (costArtists != null && costArtists.trim().length() > 0)
				pact.setCostArtists(new BigDecimal(costArtists));
			else
				pact.setCostArtists(new BigDecimal(0.00));
			Actores actores = this.actoresDAO.readActores(actId);
			Platforms plat = this.platformsService.readPlatforms(platId);
			pact.setPlat(plat);
			pact.setActores(actores);
			if (channelsId != null && channelsId.intValue() > 0) {
				PlatformsChannels channels = this.platformsService.readChannels(channelsId);
				pact.setChannel(channels);
			}
			if (this.actoresDAO.savePlatformsActores(pact)) {
				if (pact.getChannel() != null) {
					this.monitorLogService.logsAdd(request, "平台: " + pact.getPlat().getPlatName() + "频道号：" + pact.getChannel().getChannels() + "下的 平台艺人"
							+ pact.getActores().getRealname() + " ID: " + pact.getId());
				} else {
					this.monitorLogService.logsAdd(request, "平台: " + pact.getPlat().getPlatName() + "下的 平台艺人" + pact.getActores().getRealname() + " ID: " + pact.getId());
				}
				this.workManagers.onEvents(WorkNames.ACTORES, pact);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deletePlatformsActores(Integer id) {
		if (id != null && id.intValue() > 0) {
			PlatformsActores platformsActores = this.actoresDAO.readPlatformsActores(id);
			if (platformsActores != null) {
				platformsActores.setActores(null);
				platformsActores.setPlat(null);
				platformsActores.setChannel(null);
				this.actoresDAO.savePlatformsActores(platformsActores);
				if (this.actoresDAO.deletePlatformsActores(platformsActores)) {
					this.workManagers.onEvents(WorkNames.ACTORES_PDEL, platformsActores);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllPlatformsActores(LineGenres genrer) {
		return this.actoresDAO.findAllPlatformsActores(genrer);
	}

	@Override
	public boolean readPlatformsActores(Integer id, String platId, String channelsId) {
		Integer $platId = Integer.valueOf(platId);
		Integer $channelsId = Integer.valueOf(0);
		if (channelsId != null && channelsId.trim().length() > 0) {
			try {
				$channelsId = Integer.valueOf(channelsId);
			} catch (Exception e) {
			}
		}
		PlatformsActores pact = this.actoresDAO.readPlatformsActores($platId, $channelsId, id);
		if (pact != null) {
			return true;
		}
		return false;
	}

	@Override
	public Actores findValidationSession(Integer empId) {
		Actores actores = this.actoresDAO.readActoresBySession(empId);
		if (actores != null) {
			return actores;
		}
		return null;
	}

	@Override
	public boolean savePlatformsActoresBySession(PlatformsActores pact) {
		return this.actoresDAO.savePlatformsActoresBySession(pact);
	}

	@Override
	public List<PlatformsActores> findPlatformsActoresList(Integer actId) {
		return this.actoresDAO.findPlatformsActoresList(actId);
	}

	@Override
	public PlatformsActores readPlatformsActoresById(Integer id) {
		return this.actoresDAO.readPlatformsActores(id);
	}

	@Override
	public PlatformsActores findPlatformsActoresById(Integer pactId) {
		return this.actoresDAO.readPlatformsActores(pactId);
	}

	@Override
	public PlatformsOnlineIncome findPlatformsActoresByPactToOnline(Integer pactId) {
		return this.actoresDAO.readPlatformsActoresByPactToOnline(pactId);
	}

	@Override
	public PlatformsOfflineIncome findPlatformsActoresByPactToOffline(Integer pactId) {
		return this.actoresDAO.readPlatformsActoresByPactToOffline(pactId);
	}

	@Override
	public boolean savePlatformsOnlineIncome(PlatformsOnlineIncome online) {
		return this.actoresDAO.savePlatformsOnlineIncome(online);
	}

	@Override
	public boolean savePlatformsOfflineIncome(PlatformsOfflineIncome offline) {
		return this.actoresDAO.savePlatformsOfflineIncome(offline);
	}

	@Override
	public List<Actores> readActoresByEmpId(Integer empId) {
		return this.actoresDAO.readActoresByEmpId(empId);
	}

	@Override
	public boolean saveActores(Actores actores) {
		return this.actoresDAO.saveActores(actores);
	}

	@Override
	public boolean deletePlatformsActoresBySession(Integer actId) {
		PlatformsActores pact = this.platformsActoresDAO.readPlatformsActoresBySession(actId);
		if (pact != null) {
			pact.setActores(null);
			pact.setPlat(null);
			pact.setChannel(null);
			this.platformsActoresDAO.savePlatformsActoresBySession(pact);
			if (this.platformsActoresDAO.deletePlatformsActoresBySession(pact)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PactBean readPlatformsActoresBean(Integer id) {
		PlatformsActores pact = this.actoresDAO.readPlatformsActores(id);
		PactBean bean = new PactBean();
		if (pact != null) {
			Platforms plat = this.actoresDAO.readPlatforms(Integer.valueOf(pact.getPlat().getId()));
			Actores actores = this.actoresDAO.readActores(pact.getActores().getId());
			if (plat != null)
				bean.setFormGenre(String.valueOf(plat.getFormGenre()));
			bean.setAliasname(pact.getActores().getAliasname());
			if (pact.getBeLiveTime() != null)
				bean.setBeLiveTime(Constants.convert(pact.getBeLiveTime(), Constants.format1));
			bean.setCostArtists(String.format("%.2f", pact.getCostArtists()));
			bean.setGenre(pact.getActores().getGenre());
			bean.setId(pact.getId());
			bean.setPhone(pact.getActores().getPhone());
			bean.setPushMoney(pact.getActores().getPushMoney());
			bean.setQq(pact.getActores().getQq());
			bean.setRealname(pact.getActores().getRealname());
			bean.setSex(pact.getActores().getSex());
			bean.setMainPlatform(String.valueOf(pact.getMainPlatform()));
			bean.setNumber(pact.getNumber() == null ? "" : String.valueOf(pact.getNumber()));
			if (pact.getChannel() != null)
				bean.setChannelId(pact.getChannel().getId());
			if (actores.getGenrer() == Genres.SHIYONG) {
				bean.setGenrer("试用");
			} else if (actores.getGenrer() == Genres.ZHENGSHI) {
				bean.setGenrer("正式");
			}
			bean.setProbationSalary(actores.getProbationSalary() == null ? "0.00" : String.format("%.2f", actores.getProbationSalary()));
			bean.setBasicSalary(actores.getBasicSalary() == null ? "0.00" : String.format("%.2f", actores.getBasicSalary()));
			bean.setMinimumGuarantee(actores.getMinimumGuarantee() == null ? "0.00" : String.format("%.2f", actores.getMinimumGuarantee()));
		}
		return bean;
	}

	@Override
	public List<PlatformsActores> findAllPlatformsActores() {
		return this.platformsActoresDAO.findAllPlatformsActores();
	}

	@Override
	public List<PlatformsActores> findAllPlatformsActoresByActId(Actores actores) {
		return this.platformsActoresDAO.findPlatformsActoresList(actores.getId());
	}

	@Override
	public String readAllPlatNames(Integer id) {
		if (id != null && id.intValue() > 0) {
			return this.platformsActoresDAO.readAllPlatNames(id);
		}
		return "";
	}

	@Override
	public List<PlatformsActores> readPlatformsActoresByActoresId(Integer id) {
		return this.platformsActoresDAO.readPlatformsActoresByActoresId(id);
	}

	@Override
	public PlatformsActores readPlatformsActoresByNUmber(Integer numbers) {
		return this.platformsActoresDAO.readPlatformsActoresByNUmber(numbers);
	}

}
