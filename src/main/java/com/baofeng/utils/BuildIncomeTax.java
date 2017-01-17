package com.baofeng.utils;

import java.math.BigDecimal;

/**
 * 功能：计算个人所得税
 * */
public class BuildIncomeTax {
	
	/**
	 * 功能：个人所得税计算
	 * @param $baseNumber  税点基数
	 * @param $makeNumber  计税工资
	 * */
	public static BigDecimal makeTax(BigDecimal baseNumber, BigDecimal makeNumber) {
		BigDecimal $max = makeNumber.subtract(baseNumber);
		BigDecimal $rate = new BigDecimal(0);
		BigDecimal $sukou = new BigDecimal(0);
		BigDecimal $result = new BigDecimal(0);
		if ($max.doubleValue() > Double.valueOf(0).doubleValue() && $max.doubleValue() <= Double.valueOf(1500).doubleValue()) {
			$rate = new BigDecimal(3);
			$sukou = new BigDecimal(0);
		} else if ($max.doubleValue() > Double.valueOf(1500).doubleValue() && $max.doubleValue() <= Double.valueOf(4500).doubleValue()) {
			$rate = new BigDecimal(10);
			$sukou = new BigDecimal(105);
		} else if ($max.doubleValue() > Double.valueOf(4500).doubleValue() && $max.doubleValue() <= Double.valueOf(9000).doubleValue()) {
			$rate = new BigDecimal(20);
			$sukou = new BigDecimal(555);
		} else if ($max.doubleValue() > Double.valueOf(9000).doubleValue() && $max.doubleValue() <= Double.valueOf(35000).doubleValue()) {
			$rate = new BigDecimal(25);
			$sukou = new BigDecimal(1005);
		} else if ($max.doubleValue() > Double.valueOf(35000).doubleValue() && $max.doubleValue() <= Double.valueOf(55000).doubleValue()) {
			$rate = new BigDecimal(30);
			$sukou = new BigDecimal(2755);
		} else if ($max.doubleValue() > Double.valueOf(55000).doubleValue() && $max.doubleValue() <= Double.valueOf(80000).doubleValue()) {
			$rate = new BigDecimal(35);
			$sukou = new BigDecimal(5505);
		} else if ($max.doubleValue() > Double.valueOf(80000).doubleValue()) {
			$rate = new BigDecimal(45);
			$sukou = new BigDecimal(13505);
		}
		if ($rate.doubleValue() > 0 && $sukou.doubleValue() > 0)
			$result = $max.multiply($rate).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP).subtract($sukou);
		return $result;
	}
	
	public static void main(String[] args) {
		System.out.println(BuildIncomeTax.makeTax(new BigDecimal(3500), new BigDecimal(15537.93)));
	}
}
