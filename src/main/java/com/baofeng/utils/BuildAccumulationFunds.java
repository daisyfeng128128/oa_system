package com.baofeng.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能：生成公积金数据
 * */
public class BuildAccumulationFunds {

	/**
	 * 功能：计算公积金
	 * 
	 * @param baseNumber 基数
	 * @return {@link Map}</br> 个人部分</br> pn_provident:个人公积金</br> 公司部分</br>
	 *         cp_provident:公司公积金</br>
	 * */
	public static Map<String, BigDecimal> make(BigDecimal baseNumber, JSONObject base,boolean towns) {
		Map<String, BigDecimal> hash = new HashMap<String, BigDecimal>();

		hash.put("pn_provident", new BigDecimal(0.00));
		hash.put("cp_provident", new BigDecimal(0.00));
		if (baseNumber != null && baseNumber.doubleValue() > 0) {
			// 公积金交金基数
			BigDecimal $minProvident = base.getBigDecimal("MinPFcardinalNumber");
			BigDecimal $maxProvident = base.getBigDecimal("MaxPFcardinalNumber");
			if (baseNumber.doubleValue() <= $minProvident.doubleValue()) {
				baseNumber = $minProvident;
			}
			if (baseNumber.doubleValue() >= $maxProvident.doubleValue()) {
				baseNumber = $maxProvident;
			}
			
			String split = String.valueOf("\\+");
			String $towns = String.valueOf("towns");
			if(!towns)
				$towns = String.valueOf("country");
			
			String $pn_provident = base.getJSONObject($towns).getString("pn_provident");
			String $cp_provident = base.getJSONObject($towns).getString("cp_provident");
			
			/** 公积金 */
			String[] $pnprovident = $pn_provident.split(split);
			BigDecimal pn_provident = baseNumber.multiply(new BigDecimal($pnprovident[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($pnprovident[1]));
			/** 公积金 */
			String[] $cpprovident = $cp_provident.split(split);
			BigDecimal cp_provident = baseNumber.multiply(new BigDecimal($cpprovident[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($cpprovident[1]));
			hash.put("pn_provident", pn_provident);
			hash.put("cp_provident", cp_provident);
		}
		return hash;
	}

	public static void main(String[] args) {
		
	}
}
