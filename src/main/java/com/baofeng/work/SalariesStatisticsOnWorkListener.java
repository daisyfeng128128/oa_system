package com.baofeng.work;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.bean.FinStatisticcsBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.SalariesStatistics;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.ISalariesStatisticsService;
import com.baofeng.utils.CustomDateUtils;
/**
 * 功能：所有部门工资统计
 * */
public class SalariesStatisticsOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(SalariesStatisticsOnWorkListener.class);

	public SalariesStatisticsOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {

		try {
			SalariesStatistics salariesStatistics = (SalariesStatistics)analysis(arg);
			Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(salariesStatistics.getCreateDT());
			Date date2 = CustomDateUtils.Handler.currentMonthEndDay(salariesStatistics.getCreateDT());
			ISalariesStatisticsService salariesStatisticsService = (ISalariesStatisticsService) commService.getService().get(ISalariesStatisticsService.class.getName());
			
			List<SalariesStatistics> salList = salariesStatisticsService.findSalariesStatistics( date1, date2);
			// 计算线下所有部门
			List<FinStatisticcsBean> list = salariesStatisticsService.findAllFinStatisticcsBean(date1, date2);
			//计算线上所有部门
			List<FinStatisticcsBean> listOn = salariesStatisticsService.findAllFinStatisticcsBeanOnline(date1, date2);
			//计算所有星探
			List<FinStatisticcsBean> listTa = salariesStatisticsService.findAllFinStatisticcsBeanTalent(date1, date2);
			Map<String ,SalariesStatistics> offMap = new HashMap<String, SalariesStatistics>();
			Set<String> newSet = new HashSet<String>();
			if(salList != null && salList.size()>0){
				for(SalariesStatistics post:salList){
					offMap.put(post.getTwoName(), post);
				}
			}
			if(listTa != null && listTa.size()>0){
				for( FinStatisticcsBean bean :listTa){
					SalariesStatistics post = new SalariesStatistics();
					post.setCreateDT(date1);  
					post.setPeopleNum(bean.getAmount());
					post.setSalariesTotal(bean.getRealTotal());
					post.setTwoName("talent");
					if(offMap.get(post.getTwoName()) != null){
						post.setId(offMap.get(post.getTwoName()).getId());
					}
					newSet.add(post.getTwoName());
					offMap.put(post.getTwoName(), post);
				}
			}
			if(listOn != null && listOn.size()>0){
				for (FinStatisticcsBean bean : listOn) {
					if(Managers.class.getName().equals(bean.getDeptName())){
						SalariesStatistics post = new SalariesStatistics();
						post.setCreateDT(date1);  
						post.setPeopleNum(bean.getAmount());
						post.setSalariesTotal(bean.getRealTotal());
						post.setTwoName("manOn");
						if(offMap.get(post.getTwoName()) != null){
							post.setId(offMap.get(post.getTwoName()).getId());
						}
						newSet.add(post.getTwoName());
						offMap.put(post.getTwoName(), post);
					}
					
					if(Actores.class.getName().equals(bean.getDeptName())){
						SalariesStatistics post = new SalariesStatistics();
						post.setCreateDT(date1);
						post.setPeopleNum(bean.getAmount());
						post.setSalariesTotal(bean.getRealTotal());
						post.setTwoName("acOn");
						if(offMap.get(post.getTwoName()) != null){
							post.setId(offMap.get(post.getTwoName()).getId());
						}
						newSet.add(post.getTwoName());
						offMap.put(post.getTwoName(), post);
					}
				}
			}
			if (list != null && list.size() > 0) {
				for (FinStatisticcsBean bean : list) {
					if ("活动部".equals(bean.getDeptName())) {
						SalariesStatistics post = new SalariesStatistics();
						post.setCreateDT(date1);
						post.setPeopleNum(bean.getAmount());
						post.setSalariesTotal(bean.getRealTotal());
						post.setTwoName("activity");
						if(offMap.get(post.getTwoName()) != null){
							post.setId(offMap.get(post.getTwoName()).getId());
						}
						newSet.add(post.getTwoName());
						offMap.put(post.getTwoName(), post);
					}
					if ("研发部".equals(bean.getDeptName())) {
						SalariesStatistics post = new SalariesStatistics();
						post.setCreateDT(date1);
						post.setPeopleNum(bean.getAmount());
						post.setSalariesTotal(bean.getRealTotal());
						post.setTwoName("development");
						if(offMap.get(post.getTwoName()) != null){
							post.setId(offMap.get(post.getTwoName()).getId());
						}
						newSet.add(post.getTwoName());
						offMap.put(post.getTwoName(), post);
					}
					if ("财务部".equals(bean.getDeptName())) {
						SalariesStatistics post = new SalariesStatistics();
						post.setCreateDT(date1);
						post.setPeopleNum(bean.getAmount());
						post.setSalariesTotal(bean.getRealTotal());
						post.setTwoName("finance");
						if(offMap.get(post.getTwoName()) != null){
							post.setId(offMap.get(post.getTwoName()).getId());
						}
						newSet.add(post.getTwoName());
						offMap.put(post.getTwoName(), post);

					}
					if ("人事部".equals(bean.getDeptName())) {
						SalariesStatistics post = new SalariesStatistics();
						post.setCreateDT(date1);
						post.setPeopleNum(bean.getAmount());
						post.setSalariesTotal(bean.getRealTotal());
						post.setTwoName("person");
						if(offMap.get(post.getTwoName()) != null){
							post.setId(offMap.get(post.getTwoName()).getId());
						}
						newSet.add(post.getTwoName());
						offMap.put(post.getTwoName(), post);

					}
					if ("艺人部".equals(bean.getDeptName())) {
						if ("频道助管".equals(bean.getPositionsName())) {
							SalariesStatistics post = new SalariesStatistics();
							post.setCreateDT(date1);
							post.setPeopleNum(bean.getAmount());
							post.setSalariesTotal(bean.getRealTotal());
							post.setTwoName("manOff");
							if(offMap.get(post.getTwoName()) != null){
								post.setId(offMap.get(post.getTwoName()).getId());
							}
							newSet.add(post.getTwoName());
							offMap.put(post.getTwoName(), post);
						}

						if ("频道管理".equals(bean.getPositionsName())) {
							SalariesStatistics post = new SalariesStatistics();
							post.setCreateDT(date1);
							post.setPeopleNum(bean.getAmount());
							post.setSalariesTotal(bean.getRealTotal());
							post.setTwoName("manOff");
							if(offMap.get(post.getTwoName()) != null){
								post.setId(offMap.get(post.getTwoName()).getId());
							}
							newSet.add(post.getTwoName());
							offMap.put(post.getTwoName(), post);
						}

						if ("艺人主播".equals(bean.getPositionsName())) {
							SalariesStatistics post = new SalariesStatistics();
							post.setCreateDT(date1);
							post.setPeopleNum(bean.getAmount());
							post.setSalariesTotal(bean.getRealTotal());
							post.setTwoName("acOff");
							if(offMap.get(post.getTwoName()) != null){
								post.setId(offMap.get(post.getTwoName()).getId());
							}
							newSet.add(post.getTwoName());
							offMap.put(post.getTwoName(), post);
						}
					}
				}
			}
			if(newSet != null && newSet.size()>0){
				for(String tName : newSet){
					SalariesStatistics post = offMap.get(tName);
					salariesStatisticsService.addSalariesStatistics(post);
				}
			}
			Set<String> oldSet= offMap.keySet();
			oldSet.removeAll(newSet);
			if(oldSet != null && oldSet.size()>0){
				for(String tName : oldSet){
					SalariesStatistics post = offMap.get(tName);
					salariesStatisticsService.deleteSalariesStatistics(post);
				}
			}

		} catch (Exception e) {
			logger.error("计算工资统计有误", e);
		}
	}

}
