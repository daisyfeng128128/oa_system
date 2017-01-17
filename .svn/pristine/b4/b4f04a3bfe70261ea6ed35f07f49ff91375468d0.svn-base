package com.baofeng.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Random;

public class MD5 {
	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}

	/**
	 * 获取用户id 当前时间分钟的数字 做组合
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getRandomString(String str, int i) {
		if (i < str.length()) {
			int j = new Random().nextInt(str.length());
			StringBuilder bilBuilder = new StringBuilder(str);
			bilBuilder.insert(j, new Date().getMinutes());
			bilBuilder.insert(i, j);
			return bilBuilder.toString();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(MD5Encode("123.com"));
	}
}
