package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.bean.FinStatisticcsBean;
import com.baofeng.oa.bean.SalariesStatisticsBean;
import com.baofeng.oa.dao.SalariesStatisticsDAO;
import com.baofeng.oa.entity.SalariesStatistics;
import com.baofeng.oa.service.ISalariesStatisticsService;
import com.baofeng.utils.SearchFilter;

@Service("salariesStatisticsService")
public class SalariesStatisticsServiceImpl implements ISalariesStatisticsService {

	@Autowired
	private SalariesStatisticsDAO salariesStatisticsDAO;

	@Override
	public List<SalariesStatistics> findSalariesStatistics( Date date1, Date date2) {
		return this.salariesStatisticsDAO.findSalariesStatistics( date1, date2);
	}

	@Override
	public SalariesStatisticsBean readPages(Date date1, Date date2,SearchFilter filter) {
		List<SalariesStatistics> list = this.salariesStatisticsDAO.readPage(date1, date2,filter);
		if (list != null && list.size() > 0) {
			SalariesStatisticsBean bean = new SalariesStatisticsBean();
			for (SalariesStatistics post : list) {
				if("talent".equals(post.getTwoName())){
					bean.setTalent(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setTalentTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
				if ("acOff".equals(post.getTwoName())) {
					bean.setAcOff(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setAcOffTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
				if ("activity".equals(post.getTwoName())) {
					bean.setActivity(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setActivityTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
				if ("acOn".equals(post.getTwoName())) {
					bean.setAcOn(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setAcOnTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
				if ("manOff".equals(post.getTwoName())) {
					bean.setManOff(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setManOffTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
				if ("manOn".equals(post.getTwoName())) {
					bean.setManOn(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setManOnTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
				
				if ("development".equals(post.getTwoName())) {
					bean.setDevelopment(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setDevelopmentTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
				if ("finance".equals(post.getTwoName())) {
					bean.setFinance(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setFinanceTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
				if ("person".equals(post.getTwoName())) {
					bean.setPerson(post.getPeopleNum() != null ? post.getPeopleNum().toString():"0");
					bean.setPersonTotal(post.getSalariesTotal() != null ? String.format("%.2f", post.getSalariesTotal()) : "0.00");
					continue;
				}
			}
			BigDecimal operate  =  new BigDecimal(0.00);
			BigDecimal operateTotal = new BigDecimal(0.00);
			BigDecimal total = new BigDecimal(0.00);
			BigDecimal totalCount = new BigDecimal(0.00);
			
			operate = new BigDecimal(bean.getAcOff()==null ?  "0" : bean.getAcOff()).add(new BigDecimal(bean.getAcOn() == null ? "0" : bean.getAcOn()))
					.add( new BigDecimal(bean.getActivity()==null ? "0" : bean.getActivity())) .add(new BigDecimal(bean.getManOff() == null ? "0" :bean.getManOff()))
					.add(new BigDecimal(bean.getManOn()==null ? "0" : bean.getManOn())).add(new BigDecimal(bean.getTalent()== null?"0":bean.getTalent()));
			operateTotal = new BigDecimal(bean.getAcOffTotal()==null ?  "0.00" : bean.getAcOffTotal()).add(new BigDecimal(bean.getAcOnTotal() == null ? "0.00" : bean.getAcOnTotal()))
					.add( new BigDecimal(bean.getActivityTotal()==null ? "0.00" : bean.getActivityTotal())) .add(new BigDecimal(bean.getManOffTotal() == null ? "0.00" :bean.getManOffTotal()))
					.add(new BigDecimal(bean.getManOnTotal()==null ? "0.00" : bean.getManOnTotal())).add(new BigDecimal( bean.getTalentTotal()==null ? "0.00":bean.getTalentTotal()));
			total = operate.add(new BigDecimal(bean.getDevelopment() == null ? "0" : bean.getDevelopment())).add(new BigDecimal(bean.getFinance()==null ? "0" : bean.getFinance()))
					.add(new BigDecimal(bean.getPerson() == null ? "0" : bean.getPerson()));
			totalCount=operateTotal.add(new BigDecimal(bean.getDevelopmentTotal() == null ? "0" : bean.getDevelopmentTotal())).add(new BigDecimal(bean.getFinanceTotal()==null ? "0" : bean.getFinanceTotal()))
					.add(new BigDecimal(bean.getPersonTotal() == null ? "0" : bean.getPersonTotal()));
					
			bean.setOperate(operate.toString());
			bean.setOperateTotal(operateTotal.toString());
			bean.setTotal(total.toString());
			bean.setTotalCount(totalCount.toString());
			return bean;
		}
		return null;
	}

	@Override
	public List<FinStatisticcsBean> findAllFinStatisticcsBean(Date date1, Date date2) {
		return this.salariesStatisticsDAO.findAllFinStatisticcsBean(date1, date2) ;
	}

	@Override
	public boolean addSalariesStatistics(SalariesStatistics post) {
		return this.salariesStatisticsDAO.addSalariesStatistics(post);
	}

	@Override
	public boolean deleteSalariesStatistics(SalariesStatistics post) {
		if(post != null && post.getId()!= null && post.getId().intValue()>0){
			return this.salariesStatisticsDAO.deleteSalariesStatistics(post);
		}
		return false;
	}

	@Override
	public List<FinStatisticcsBean> findAllFinStatisticcsBeanOnline(Date date1, Date date2) {
		return this.salariesStatisticsDAO.findAllFinStatisticcsBeanOnline(date1,date2);
	}

	@Override
	public List<FinStatisticcsBean> findAllFinStatisticcsBeanTalent(Date date1, Date date2) {
		return this.salariesStatisticsDAO.findAllFinStatisticcsBeanTalent(date1,date2);
	}

}
