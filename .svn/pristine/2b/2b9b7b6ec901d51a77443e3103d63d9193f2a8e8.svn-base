package com.baofeng.utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLUtils {

	private static String httpHead = "http://10.1.11.137:8080/api-test/resources/%s";

	public static String Get(String parameters, String mediaType, String encoding, String url) {
		String result = "ERRORS";
		HttpURLConnection uc = null;
		try {
			URL u = new URL(url.contains("http") == true ? url : String.format(httpHead, url));
			uc = (HttpURLConnection) u.openConnection();
			uc.setDoOutput(true);
			uc.setRequestMethod("GET");
			uc.addRequestProperty("accept", mediaType);
			uc.addRequestProperty("PPS-authenticate", "9296011741541f37bb78762fd66242475ee0d43f");

			if (null != parameters && !parameters.trim().isEmpty()) {
				OutputStream out = uc.getOutputStream();
				out.write(parameters.getBytes(encoding));
				out.flush();
				out.close();
			}
			for (int i = 0;; i++) {
				String header = uc.getHeaderField(i);
				if (header == null)
					break;
				// System.out.println(uc.getHeaderFieldKey(i) + ": " + header);
			}
			InputStream buffer = new BufferedInputStream(uc.getInputStream());
			Reader reader = new InputStreamReader(buffer);
			StringBuffer context = new StringBuffer();
			int line;
			while ((line = reader.read()) != -1) {
				context.append((char) line);
			}
			reader.close();
			buffer.close();
			result = context.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	public static String Post(String params, String mediaType, String encoding, String url) {
		return doSend(params, mediaType, encoding, url, "POST");
	}

	public static String Put(String params, String mediaType, String encoding, String url) {
		return doSend(params, mediaType, encoding, url, "PUT");
	}

	public static String Delete(String mediaType, String encoding, String url) {
		String result = "ERRORS";
		HttpURLConnection uc = null;
		try {
			URL u = new URL(url.contains("http") == true ? url : String.format(httpHead, url));
			uc = (HttpURLConnection) u.openConnection();
			uc.setRequestMethod("DELETE");
			if (null != mediaType) {
				uc.addRequestProperty("Content-Type", mediaType);
			}
			uc.addRequestProperty("accept", "*");
			uc.addRequestProperty("PPS-authenticate", "9296011741541f37bb78762fd66242475ee0d43f");
			uc.connect();
			uc.disconnect();
			InputStream buffer = new BufferedInputStream(uc.getInputStream());
			Reader reader = new InputStreamReader(buffer);
			StringBuffer context = new StringBuffer();
			int line;
			while ((line = reader.read()) != -1) {
				context.append((char) line);
			}
			reader.close();
			buffer.close();
			result = context.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	private static String doSend(String params, String mediaType, String encoding, String url, String method) {
		String result = "ERRORS";
		HttpURLConnection uc = null;
		try {
			URL u = new URL(url.contains("http") == true ? url : String.format(httpHead, url));
			uc = (HttpURLConnection) u.openConnection();
			uc.setDoOutput(true);
			uc.setRequestMethod(method);
			if (null != mediaType) {
				uc.addRequestProperty("Content-Type", mediaType);
			}
			uc.addRequestProperty("accept", "*");
			uc.addRequestProperty("basic-token", "753800aad1a1d437d5d3ee442bb70cecd866cade");
			uc.connect();
			OutputStream out = uc.getOutputStream();
			out.write(params.getBytes(encoding));
			out.flush();

			InputStream buffer = new BufferedInputStream(uc.getInputStream());
			Reader reader = new InputStreamReader(buffer);
			StringBuffer context = new StringBuffer();
			int line;
			while ((line = reader.read()) != -1) {
				context.append((char) line);
			}
			reader.close();
			buffer.close();
			result = context.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * 功能：下载网络图片
	 * */
	public static boolean download(String uri, String folder, String filename) {
		try {
			URL $url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) $url.openConnection();
			DataInputStream in = new DataInputStream(connection.getInputStream());
			DataOutputStream out = new DataOutputStream(new FileOutputStream(folder + filename));
			byte[] buffer = new byte[1024];
			int line = 0;
			while ((line = in.read(buffer)) > 0) {
				out.write(buffer, 0, line);
			}
			out.close();
			in.close();
			connection.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		// 需要下载的URL
		//String url = "http://q.qlogo.cn/qqapp/101183515/C996B99932A801D0B093F75DA3991327/100";
		// 截取最后/后的字符串
		//String fileName = "a.jpg";
		// 图片保存路径
		//String folder = "D:\\home\\";
		// URLUtils.download(url, folder, fileName);
		System.out.println("|PW||RD".split("\\|").length);
	}
}
