package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.bean.TalentBean;
import com.baofeng.oa.dao.TalentDAO;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Talent;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.ITalentService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("talentService")
public class TalentServiceImpl implements ITalentService {

	@Autowired
	private TalentDAO talentDAO;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter filter) {
		NPageResult page = this.talentDAO.readAllPages(pageSize, curPage, sortName, sortOrder, fastArg, filter);
		List<TalentBean> list = new ArrayList<TalentBean>();
		BranchOffice branchOffice = null;
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			for (Object o : page.getData()) {
				Talent talent = (Talent) o;
				TalentBean bean = new TalentBean();
				bean.setId(talent.getId().toString());
				bean.setAliasname(talent.getAliasname());
				bean.setBankAddress(talent.getBankAddress());
				bean.setBankCard(talent.getBankCard());
				bean.setName(talent.getName());
				bean.setPhone(talent.getPhone());
				bean.setQq(talent.getQq());
				bean.setSex(talent.getSex() == Sex.WOMAN ? "女" : "男");
				bean.setGenrer(String.valueOf(talent.getGenrer()));
				branchOffice=this.branchOfficeService.readBranchOffice(talent.getBranchs());
				if (talent.getNumber() != null){
					if(branchOffice!=null)
						bean.setNumber(branchOffice.getNumberHead() + String.format("%04d", Integer.valueOf(talent.getNumber())));
					else
						bean.setNumber(String.format("%04d", Integer.valueOf(talent.getNumber())));
				}else{
					bean.setNumber("N/A");
				}
				list.add(bean);
			}
		}
		page.setData(list);
		return page;
	}

	@Override
	public boolean addTalent(Talent talent) {
		if (talent != null && talent.getId() != null && talent.getId().intValue() > 0) {
			Talent $talent = this.talentDAO.readTalent(talent.getId());
			if ($talent != null) {
				$talent.setAliasname(talent.getAliasname());
				$talent.setName(talent.getName());
				$talent.setSex(talent.getSex());
				$talent.setPhone(talent.getPhone());
				$talent.setQq(talent.getQq());
				$talent.setBankAddress(talent.getBankAddress());
				$talent.setBankCard(talent.getBankCard());
				$talent.setGenrer(LineGenres.ONLINE);
				talent = $talent;
			}
		}
		if (this.talentDAO.addTalent(talent)) {
			return true;
		}
		return false;
	}

	@Override
	public TalentBean readTalent(Integer id) {
		if (id != null && id.intValue() > 0) {
			Talent talent = this.talentDAO.readTalent(id);
			if (talent != null) {
				TalentBean bean = new TalentBean();
				bean.setAliasname(talent.getAliasname());
				bean.setName(talent.getName());
				bean.setBankAddress(talent.getBankAddress());
				bean.setBankCard(talent.getBankCard());
				bean.setId(talent.getId().toString());
				bean.setPhone(talent.getPhone());
				bean.setQq(talent.getQq());
				bean.setSex(talent.getSex() == Sex.WOMAN ? "女" : "男");
				bean.setNumber(talent.getNumber());
				return bean;
			}
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		if (id != null && id.intValue() > 0) {
			Talent talent = this.talentDAO.readTalent(id);
			if (talent != null) {
				this.talentDAO.deleteTalent(talent);
					return true;
			}
		}
		return false;
	}

	@Override
	public List<Talent> findAllTalent() {
		return this.talentDAO.findAllTalent();
	}

	@Override
	public Talent findTalentSession(Integer empId) {
		Talent talent = this.talentDAO.readTalentBySession(empId);
		if (talent != null) {
			return talent;
		}
		return null;
	}
	
	@Override
	public boolean saveTalent(Talent talent) {
		return this.talentDAO.saveTalent(talent);
	}
}
