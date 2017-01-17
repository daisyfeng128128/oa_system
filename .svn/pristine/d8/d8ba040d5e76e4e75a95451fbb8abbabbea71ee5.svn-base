package com.baofeng.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PrimaryGenerater {

	private static final String SERIAL_NUMBER = "XXXXXXXXXX"; // 流水号格式
	private static PrimaryGenerater primaryGenerater = null;

	private PrimaryGenerater() {
	}

	public static PrimaryGenerater getInstance() {
		if (primaryGenerater == null) {
			synchronized (PrimaryGenerater.class) {
				if (primaryGenerater == null) {
					primaryGenerater = new PrimaryGenerater();
				}
			}
		}
		return primaryGenerater;
	}

	/**
	 * 生成下一个编号
	 */
	public synchronized String generaterNextNumber(String sno) {
		String id = null;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		if (sno == null) {
			id = formatter.format(date) + "0000000001";
		} else {
			int count = SERIAL_NUMBER.length();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < count; i++) {
				sb.append("0");
			}
			DecimalFormat df = new DecimalFormat("0000000000");
			id = formatter.format(date) + df.format(1 + Integer.parseInt(sno.substring(8, 18)));
		}
		return id;
	}

	public static void main1(String[] args) {
		for (long i = 0; i < 99999999999l; i++) {
			String s = System.getProperty("s");
			if (s == null) {
				s = PrimaryGenerater.getInstance().generaterNextNumber(null);
				System.setProperty("s", s);
				System.out.println(s);
			} else {
				String s1 = PrimaryGenerater.getInstance().generaterNextNumber(s);
				System.setProperty("s", s1);
				System.out.println(s1);
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10000;i++){
			System.out.println(PrimaryGenerater.serialNumber());
		}
	}

	/**
	 * 功能:订单号
	 * */
	public static String serialNumber() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		Calendar nows = Calendar.getInstance();
		int year = nows.get(Calendar.YEAR);
		int hour = nows.get(Calendar.HOUR_OF_DAY);
		int minute = nows.get(Calendar.MINUTE);
		int second = nows.get(Calendar.SECOND);
		long seconds = second + (minute * 60) + (hour * 3600);

		List<String> beginSn = Arrays.asList("C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q");
		String begin = formatter.format(nows.getTime());
		String serial = beginSn.get(year - 2014).toString() + Integer.toHexString(hour).toUpperCase() + begin + seconds + (new Random().nextInt(89999)+10000);
		return serial;
	}
}
