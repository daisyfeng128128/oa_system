package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.BigTopDetails;
import com.baofeng.oa.entity.PlatformsMaintainOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IBigtopService;
/**
 * 功能：同步报表大型支出到财务报表
 * */
public class BigOutOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(BigOutOnWorkListener.class);

	public BigOutOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable o, Object arg) {
		try {

			PlatformsMaintainOutlay outlay = (PlatformsMaintainOutlay) arg;
			IBigtopService bigtopService = (IBigtopService) commService.getService() .get(IBigtopService.class.getName());
			BigTopDetails details = bigtopService.findBigtopByOutlay(outlay.getPlatformsId(), outlay.getId());
			if (details == null) {
				details = new BigTopDetails();
			}
			details.setOutlay(outlay);
			details.setBranchs(outlay.getBranchs());
			details.setPayfees(new BigDecimal(outlay.getTopup()));
			details.setCreateDT(outlay.getCreateDT());
			details.setPlatId(Integer.valueOf(outlay.getPlatformsId()));
			if (bigtopService.saveBigtop(details)) {
				if (logger.isDebugEnabled())
					logger.debug("大型充值数据同步到慨况表添加-修改成功");
			}
		
		} catch (Exception e) {
			logger.error("大型充值数据同步到慨况表添加-修改错误");
		}
		
	}

}
