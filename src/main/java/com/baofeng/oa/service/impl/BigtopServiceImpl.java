package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.dao.BigtopDAO;
import com.baofeng.oa.entity.BigTopDetails;
import com.baofeng.oa.service.IBigtopService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("bigtopService")
public class BigtopServiceImpl implements IBigtopService {

	@Autowired
	private BigtopDAO bigtopDAO;

	@Override
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter, String platId, String date) {
		return this.bigtopDAO.readPages(pageSize, curPage, sortName, sortOrder, filter, platId, date);
	}

	@Override
	public boolean saveBigtop(String id, String askfees, String createDT, String date, String platId,Integer branchs) {
		BigTopDetails details = new BigTopDetails();
		details.setBranchs(branchs);
		details.setPlatId(Integer.valueOf(platId));
		details.setAskfees(new BigDecimal(askfees));
		details.setCreateDT(Constants.convert(createDT + " 00:00:00", Constants.format2));
		if (id != null && id.trim().length() > 0) {
			BigTopDetails $details = this.readBigtop(id);
			if ($details != null) {
				$details.setAskfees(new BigDecimal(askfees));
				$details.setCreateDT(Constants.convert(createDT + " 00:00:00", Constants.format2));
				details = $details;
			}
		}
		return this.bigtopDAO.saveBigTop(details);
	}

	@Override
	public BigTopDetails readBigtop(String id) {
		return id == null ? null : this.bigtopDAO.readBigtop(id);
	}

	@Override
	public List<String> readBigtopByDate() {
		Set<String> hash = new TreeSet<String>();
		List<BigTopDetails> detailsList = this.bigtopDAO.readBigTopDetails();
		if (detailsList != null && detailsList.size() > 0) {
			for (BigTopDetails details : detailsList) {
				if (details != null && details.getCreateDT() != null)
					hash.add(Constants.convert(details.getCreateDT(), Constants.format12));
			}
		}
		return Arrays.asList(hash.toArray(new String[hash.size()]));
	}

	@Override
	public Map<String, String> readLoader(String date, String platId,SearchFilter filter) {
		Map<String, String> map = new HashMap<String, String>();
		List<?> detailsList = this.bigtopDAO.readBigTopDetailsList(date, platId,filter);
		if (detailsList != null && detailsList.size() > 0) {
			BigDecimal askfees = new BigDecimal(0.00);
			BigDecimal payfees = new BigDecimal(0.00);
			for (Object o : detailsList) {
				if (o instanceof BigTopDetails) {
					BigTopDetails details = (BigTopDetails) o;
					if (details.getAskfees() != null)
						askfees = askfees.add(details.getAskfees());
					if (details.getPayfees() != null)
						payfees = payfees.add(details.getPayfees());
				}
			}
			map.put("askfees", askfees.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			map.put("payfees", payfees.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			map.put("cashfees", askfees.subtract(payfees).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		return map;
	}

	@Override
	public BigTopDetails findBigtopByOutlay(String platId, Integer outlayId) {
		return this.bigtopDAO.readBigtopByOutlay(platId, outlayId);
	}

	@Override
	public boolean saveBigtop(BigTopDetails details) {
		return this.bigtopDAO.saveBigTop(details);
	}

	@Override
	public boolean deleteBigtopByOutlay(BigTopDetails details) {
		if (details != null) {
			details.setOutlay(null);
			if (this.bigtopDAO.saveBigTop(details)) {
				return this.bigtopDAO.deleteBigTopDetails(details);
			}
		}
		return false;
	}

	@Override
	public BigTopDetails findBigtopByTopup(Integer platId, Date date1, Date date2) {
		return this.bigtopDAO.findBigtopByTopup(platId, date1, date2);
	}
}
