package com.baofeng.work;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.CostCheck;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.ICostCheckService;
import com.baofeng.oa.service.IPlatformsActoresService;

/**
 * 功能：每天校验艺人成本
 * */
public class CostCheckOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(CostCheckOnWorkListener.class);

	public CostCheckOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		ICostCheckService costCheckService = (ICostCheckService) commService.getService().get(ICostCheckService.class.getName());
		try {
			// 线下艺人
			List<Actores> listActOff = actoresService.findAllActoresOffline();
			// 线上艺人
			List<Actores> listActOn = actoresService.findAllActoresOnline();
			// 要保存的数据
			List<CostCheck> list = new ArrayList<CostCheck>();
			if (listActOff != null && listActOff.size() > 0) {
				for (Actores act : listActOff) {
					BigDecimal costArtists = new BigDecimal(0.00);
					BigDecimal basicSalary = new BigDecimal(0.00);
					String costArt = "";
					List<PlatformsActores> listPact = platformsActoresService.findAllPlatformsActoresByActId(act);
					if (listPact != null && listPact.size() > 0) {
						for (PlatformsActores pact : listPact) {
							String cost = pact.getCostArtists() != null ? pact.getCostArtists().toString() : "0";
							costArt = costArt + pact.getPlat().getPlatName() + ":" + cost + "</br>";
							costArtists = costArtists.add(pact.getCostArtists() != null ? pact.getCostArtists() : new BigDecimal(0.00));
						}
						basicSalary = act.getGenrer() == Genres.SHIYONG ? new BigDecimal(act.getProbationSalary()) : new BigDecimal(act.getBasicSalary());
						if (basicSalary.compareTo(costArtists) != 0) {
							if (costArt.length() > 5)
								costArt = costArt.substring(0, costArt.length() - 5);
							CostCheck cost = new CostCheck();
							cost.setBranchs(act.getBranchs());
							cost.setActores(act);
							cost.setCostArtists(costArt);
							list.add(cost);
						}
					}
				}
			}
			if (listActOn != null && listActOn.size() > 0) {
				for (Actores act : listActOn) {
					BigDecimal costArtists = new BigDecimal(0.00);
					BigDecimal basicSalary = new BigDecimal(act.getMinimumGuarantee() != null ? act.getMinimumGuarantee() : 0.00);
					String costArt = "";
					List<PlatformsActores> listPact = platformsActoresService.findAllPlatformsActoresByActId(act);
					if (listPact != null && listPact.size() > 0) {
						for (PlatformsActores pact : listPact) {
							String cost = pact.getCostArtists() != null ? pact.getCostArtists().toString() : "0";
							costArt = costArt + pact.getPlat().getPlatName() + ":" + cost + "</br>";
							costArtists = costArtists.add(pact.getCostArtists() != null ? pact.getCostArtists() : new BigDecimal(0.00));
						}
						if (basicSalary.compareTo(costArtists) != 0) {
							if (costArt.length() > 5)
								costArt = costArt.substring(0, costArt.length() - 5);
							CostCheck cost = new CostCheck();
							cost.setBranchs(act.getBranchs());
							cost.setActores(act);
							cost.setCostArtists(costArt);
							list.add(cost);
						}
					}
				}
			}
			costCheckService.deleteCostCheck();
			if (list != null && list.size() > 0) {
				costCheckService.addCostCheckList(list);
			}
		} catch (Exception e) {
			logger.error("同步艺人成本校验有误", e);
		}

	}

}
