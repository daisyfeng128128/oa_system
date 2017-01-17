package com.baofeng.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能：生成社保数据
 * */
public class BuildSocialSecurity {

	/**
	 * 功能：生成数据
	 * 
	 * @param baseNumber基数
	 * @param towns城镇
	 * 
	 * @return {@link Map}</br> 个人部分</br> 
	 * 	pn_pension:个人养老金</br>
	 *  pn_medicare:医疗保险</br> 
	 *  pn_unemployment:失业保险</br>
	 *  pn_totalSocial:个人合计</br> 公司部分</br> 
	 *  cp_pension:养老金</br>
	 *  cp_medicare:医疗保险</br> 
	 *  cp_unemployment:失业保险</br>
	 *  cp_occupationalInjury:工伤保险</br> 
	 *  cp_fertility:生育保险</br>
	 *  cp_totalSocial:公司合计</br>
	 * */
	public static Map<String, BigDecimal> make(BigDecimal baseNumber, JSONObject base, boolean towns) {
		Map<String, BigDecimal> hash = new HashMap<String, BigDecimal>();

		hash.put("pn_pension", new BigDecimal(0.00));
		hash.put("pn_medicare", new BigDecimal(0.00));
		hash.put("pn_fertility", new BigDecimal(0.00));
		hash.put("pn_occupationalInjury", new BigDecimal(0.00));
		hash.put("pn_unemployment", new BigDecimal(0.00));
		hash.put("pn_totalSocial", new BigDecimal(0.00));
		
		hash.put("cp_pension", new BigDecimal(0.00));
		hash.put("cp_medicare", new BigDecimal(0.00));
		hash.put("cp_unemployment", new BigDecimal(0.00));
		hash.put("cp_occupationalInjury", new BigDecimal(0.00));
		hash.put("cp_fertility", new BigDecimal(0.00));
		hash.put("cp_totalSocial", new BigDecimal(0.00));
		if (baseNumber != null && baseNumber.doubleValue() > 0) {
			String $towns = String.valueOf("towns");
			if(!towns)
				$towns = String.valueOf("country");
			BigDecimal minBaseNumber = base.getBigDecimal("MinSScardinalNumber");// 最低社保基数
			BigDecimal maxBaseNumber = base.getBigDecimal("MaxSScardinalNumber");// 最高社保基数
			
			// 社保交金基数
			if (baseNumber.doubleValue() <= minBaseNumber.doubleValue()) {
				baseNumber = minBaseNumber;
			}
			if (baseNumber.doubleValue() >= maxBaseNumber.doubleValue()) {
				baseNumber = maxBaseNumber;
			}

			// 个人部分////////
			String split = String.valueOf("\\+");
			String $pn_pension = base.getJSONObject($towns).getString("pn_pension");
			String $pn_medicare = base.getJSONObject($towns).getString("pn_medicare");
			String $pn_fertility = base.getJSONObject($towns).getString("pn_fertility");
			String $pn_unemployment = base.getJSONObject($towns).getString("pn_unemployment");
			String $pn_occupationalInjury = base.getJSONObject($towns).getString("pn_occupationalInjury");
			
			/** 养老金 */
			String[] $pension = $pn_pension.split(split);
			BigDecimal pn_pension = baseNumber.multiply(new BigDecimal($pension[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($pension[1]));
			/** 医疗保险 */
			String[] $medicare = $pn_medicare.split(split);
			BigDecimal pn_medicare = baseNumber.multiply(new BigDecimal($medicare[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($medicare[1]));
			/** 失业保险 */
			String[] $unemployment = $pn_unemployment.split(split);
			BigDecimal pn_unemployment = baseNumber.multiply(new BigDecimal($unemployment[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($unemployment[1]));
			/** 生育保险 */
			String[] $fertility = $pn_fertility.split(split);
			BigDecimal pn_fertility = baseNumber.multiply(new BigDecimal($fertility[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($fertility[1]));
			/** 工伤保险 */
			String[] $occupationalInjury = $pn_occupationalInjury.split(split);
			BigDecimal pn_occupationalInjury = baseNumber.multiply(new BigDecimal($occupationalInjury[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($occupationalInjury[1]));

			/** 个人合计 */
			BigDecimal pn_totalSocial = pn_pension.add(pn_medicare).add(pn_unemployment).add(pn_fertility).add(pn_occupationalInjury);

			hash.put("pn_pension", pn_pension);
			hash.put("pn_medicare", pn_medicare);
			hash.put("pn_fertility", pn_fertility);
			hash.put("pn_unemployment", pn_unemployment);
			hash.put("pn_occupationalInjury", pn_occupationalInjury);
			hash.put("pn_totalSocial", pn_totalSocial);
			
			// 公司部分////////////////////
			String $cp_pension = base.getJSONObject($towns).getString("cp_pension");
			String $cp_medicare = base.getJSONObject($towns).getString("cp_medicare");
			String $cp_fertility = base.getJSONObject($towns).getString("cp_fertility");
			String $cp_unemployment = base.getJSONObject($towns).getString("cp_unemployment");
			String $cp_occupationalInjury = base.getJSONObject($towns).getString("cp_occupationalInjury");
			/** 养老金 */
			String[] $cppension = $cp_pension.split(split);
			BigDecimal cp_pension = baseNumber.multiply(new BigDecimal($cppension[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($cppension[1]));
			/** 医疗保险 */
			String[] $cpmedicare = $cp_medicare.split(split);
			BigDecimal cp_medicare = baseNumber.multiply(new BigDecimal($cpmedicare[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($cpmedicare[1]));
			/** 生育保险 */
			String[] $cpfertility = $cp_fertility.split(split);
			BigDecimal cp_fertility = baseNumber.multiply(new BigDecimal($cpfertility[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($cpfertility[1]));
			/** 失业保险 */
			String[] $cpunemployment = $cp_unemployment.split(split);
			BigDecimal cp_unemployment = baseNumber.multiply(new BigDecimal($cpunemployment[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($cpunemployment[1]));
			/** 工伤保险 */
			String[] $cpoccupationalInjury = $cp_occupationalInjury.split(split);
			BigDecimal cp_occupationalInjury = baseNumber.multiply(new BigDecimal($cpoccupationalInjury[0].replace("%", ""))).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal($cpoccupationalInjury[1]));
			/** 社保合计 */
			BigDecimal cp_totalSocial = cp_pension.add(cp_medicare).add(cp_unemployment).add(cp_occupationalInjury).add(cp_fertility);
			hash.put("cp_pension", cp_pension);
			hash.put("cp_medicare", cp_medicare);
			hash.put("cp_unemployment", cp_unemployment);
			hash.put("cp_occupationalInjury", cp_occupationalInjury);
			hash.put("cp_fertility", cp_fertility);
			hash.put("cp_totalSocial", cp_totalSocial);
		}
		return hash;
	}
}
