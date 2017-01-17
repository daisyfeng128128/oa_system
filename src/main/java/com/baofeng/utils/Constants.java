package com.baofeng.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSONArray;

public class Constants {

	public static final String format1 = "yyyy-MM-dd";
	public static final String format2 = "yyyy-MM-dd HH:mm:ss";
	public static final String format3 = "M月dd";
	public static final String format4 = "yyyy.MM.dd";
	public static final String format5 = "yyyyMMdd";
	public static final String format6 = "M月dd日 HH:mm";
	public static final String format7 = "yyyy-MM";
	public static final String format8 = "yyyy-MM-dd HH:mm";
	public static final String format9 = "yyyy年MM月";
	public static final String format10 = "yyyy年MM月dd日";
	public static final String format11 = "yyyy年MM月dd日 HH:mm:ss";
	public static final String format12 = "yyyy";

	/** 满勤天数 */
	public static final double DEFAULT_ATTENDANCE = 26;
	/** 原最大天数改为 26天 */
	public static final int DEFAULT_MAXNUM = 26;

	/** 社保最低基数 */
	public static double DEFAULT_MINSOCIAL = 3271;
	/** 社保最高基数 */
	public static double DEFAULT_MAXSOCIAL = 16353;
	/** 公种积金最低基数 */
	public static double DEFAULT_MINPROVIDENT = 254;
	/** 公种积金最高基数 */
	public static double DEFAULT_MAXPROVIDENT = 2290;

	/** 日独立ip */
	public static final String INDIEIP = "ANCHORS:FLOW:IP";
	/** 日访问IP列表 */
	public static final String IPLIST = "ANCHORS:FLOW:IPLIST";
	/** 日访问UV */
	public static final String UVLIST = "ANCHORS:FLOW:UVLIST";

	/** 日pv */
	public static final String PV = "ANCHORS:FLOW:PV";
	/** 日uv */
	public static final String UV = "ANCHORS:FLOW:UV";
	/** 当前session用户KEY */
	public static final String CURRENT_USER = "session_user";
	public static final String CURRENT_BRANCHS = "sessoin_branchs";

	/** 图片文件 */
	public static String DEFAULT_UPLOADIMAGEPATH;
	/** 员工考勤 excel 路径 */
	public static String DEFAULT_UPLOADEXCEL;

	/** 图片访问路径 */
	public static String DEFAULT_HTTPIMAGES;

	/** 生成系统 */
	public static String COREWEB_BUILDITEMS;

	/** 访问域名 */
	public static String DEFAULT_BASEHTTP;
	public static boolean DEFAULT_ISRELEASE;
	/** 数据通道 */
	public static ConcurrentLinkedQueue<Message> Queue = new ConcurrentLinkedQueue<Message>();

	/** 系统配置 */
	public static final String CONF_FILE_NAME = "variables.properties";

	static {
		try {
			InputStream fis = Constants.class.getClassLoader().getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(fis);
			DEFAULT_BASEHTTP = props.getString("DEFAULT_BASEHTTP");
			DEFAULT_UPLOADIMAGEPATH = props.getString("DEFAULT_UPLOADIMAGEPATH");
			DEFAULT_HTTPIMAGES = props.getString("DEFAULT_HTTPIMAGES");
			DEFAULT_ISRELEASE = Boolean.valueOf(props.getString("DEFAULT_ISRELEASE"));
			COREWEB_BUILDITEMS = props.getString("COREWEB_BUILDITEMS");
			DEFAULT_MINSOCIAL = Double.valueOf(props.getString("DEFAULT_MINSOCIAL"));
			DEFAULT_UPLOADEXCEL = props.getString("DEFAULT_UPLOADEXCEL");
			DEFAULT_MAXSOCIAL = Double.valueOf(props.getString("DEFAULT_MAXSOCIAL"));
			DEFAULT_MINPROVIDENT = Double.valueOf(props.getString("DEFAULT_MINPROVIDENT"));
			DEFAULT_MAXPROVIDENT = Double.valueOf(props.getString("DEFAULT_MAXPROVIDENT"));
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：动态读取配置文件
	 * */
	public static JSONArray readToJson(String key) {
		JSONArray json = new JSONArray();
		try {
			InputStream fis = Constants.class.getClassLoader().getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(fis);
			String values = props.getString(key);
			fis.close();
			json = JSONArray.parseArray(values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 功能：动态读取配置文件
	 * */
	public static String[] readProperties(String properties) {
		String values = String.valueOf("");
		try {
			InputStream fis = Constants.class.getClassLoader().getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(fis);
			values = props.getString(properties);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values.split(",");
	}

	@SuppressWarnings("deprecation")
	public static String sha1ToPath(String sha1, String separator) {
		String result = String.valueOf("");
		if (sha1 != null && sha1.trim().length() > 0) {
			String s1 = sha1.substring(0, 1);
			String s2 = DigestUtils.shaHex(sha1.getBytes()).substring(0, 2);
			result = s1 + separator + s2 + separator + sha1;
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public static String fileToSha1(String file) {
		String sha1 = String.valueOf("");
		if (file != null && file.trim().length() > 0) {
			InputStream input = null;
			try {
				input = new FileInputStream(new File(file));
				sha1 = DigestUtils.shaHex(input);
				input.close();
			} catch (Exception e) {
				try {
					input.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		return sha1;
	}

	/**
	 * 功能：创建文件路径
	 * */
	public static void mkdirs(String path) {
		if (path != null) {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
		}
	}

	/**
	 * 功能：获取请求IP地址
	 * */
	public static String InternetProtocol(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	/**
	 * 功能：日期转字符
	 * */
	public static String convert(Date date, String format) {
		String retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		retValue = sdf.format(date);
		return retValue;
	}

	/**
	 * 功能：字符转日期
	 * */
	public static Date convert(String date, String format) {
		SimpleDateFormat fmat = new SimpleDateFormat(format);
		Date value = null;
		try {
			value = fmat.parse(date);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 字符串转16进制字符串
	 */
	public static String stringToHex(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
		}
		return sb.toString().trim();
	}

	/**
	 * 16进制字符串转字符串
	 * */
	public static String hexToString(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		try {
			return new String(bytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getBranchsToInt(String key) {
		if (System.getProperty(key) != null) {
			return Integer.parseInt(System.getProperty(key));
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Constants.readProperties("MESSAGE_SOCIALSECURITY");
	}
}
