package com.baofeng.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {

	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	public static String convertThenSave(String imageLocation, BufferedImage bufferedImage, double newWidth, double newHeight, String rname, String small)
			throws Exception {
		// 这样就不用每次转换都要读取文件生成BufferedImage, 创建过多的对象
		long startTime = System.currentTimeMillis();
		Image image = new Image(bufferedImage, imageLocation);

		int startX = 0;
		int startY = 0;
		int endX = 0;
		int endY = 0;
		int actualWidth = image.getWidth();
		int actualHeight = image.getHeight();
		int cropWidth = 0; // 要截取的高度
		int cropHeight = 0;
		double actualRatio = new Double(actualWidth) / new Double(actualHeight);
		double ratio = new Double(newWidth) / new Double(newHeight);

		boolean needCrop = true;
		if (actualRatio == ratio) {
			needCrop = false;
		}
		if (actualRatio < ratio) { // 截去高出的部分
			cropHeight = (int) Math.round(actualWidth / ratio);
			startY = (int) Math.round((actualHeight - cropHeight) / 2);
			// logger.debug("crop new height: " + startY);
			endY = startY + cropHeight;
			endX = actualWidth;
		} else if (actualRatio > ratio) { // 截去宽出的部分
			cropWidth = (int) Math.round(actualHeight * ratio);
			startX = (int) Math.round((actualWidth - cropWidth) / 2);
			// logger.debug("crop width: " + startX);
			endX = startX + cropWidth;
			endY = actualHeight;
		}
		if (needCrop) {
			image.crop(startX, startY, endX, endY);
		}
		image.resize((int) newWidth, (int) newHeight);

		String originalName = String.valueOf("");
		if (small != null && small.trim().length() > 0) {
			String $temps = "_" + small;
			String beginName = rname.substring(0, rname.lastIndexOf("."));
			String endName = rname.substring(rname.lastIndexOf("."));
			originalName = imageLocation + File.separator + beginName + $temps + endName;
		} else {
			String $temps = "_" + (int) newWidth + "_" + (int) newHeight;
			String beginName = rname.substring(0, rname.lastIndexOf("."));
			String endName = rname.substring(rname.lastIndexOf("."));
			originalName = imageLocation + File.separator + beginName + $temps + endName;
		}

		File $file = new File(originalName);
		if (!$file.getParentFile().exists()) {
			$file.getParentFile().mkdirs();
		}
		image.saveAs($file.getAbsolutePath());
		long entTime = System.currentTimeMillis();
		logger.info("converting: " + imageLocation + " [" + newWidth + "/" + newHeight + "] pre img cost time:" + (entTime - startTime) + "ms");
		return $file.getParent();
	}

	/**
	 * 功能：用于截取图片框出部分图片
	 * 
	 * @param srcImage 源图片路径
	 * @param destImage 新截取图片路径
	 * @param startX 源图片width到截图坐标长度
	 * @param startY 源图片height到截图坐标长度
	 * @param newWidth 目标图片宽
	 * @param newHeight 目标图片高
	 * 
	 * */
	public static void diagram(String srcImage, String destImage, int startX, int startY, int newWidth, int newHeight) {
		FileInputStream input = null;
		ImageInputStream imageInput = null;
		try {
			String ext = String.valueOf("");
			File $file = new File(srcImage);
			if ($file.exists()) {
				ext = $file.getName().substring($file.getName().lastIndexOf(".") + 1);
				input = new FileInputStream($file);
				Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);
				ImageReader reader = it.next();
				imageInput = ImageIO.createImageInputStream(input);
				reader.setInput(imageInput, true);
				ImageReadParam param = reader.getDefaultReadParam();
				Rectangle rect = new Rectangle(startX, startY, newWidth, newHeight);
				param.setSourceRegion(rect);
				BufferedImage buffer = reader.read(0, param);
				ImageIO.write(buffer, ext, new File(destImage));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
				if (imageInput != null)
					imageInput.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String srcImage = "D:\\Users\\renliangrong\\Pictures\\psbJUK6FDX5.jpg";
		String destImage = "D:\\Users\\renliangrong\\Pictures\\psbJUK6FDX5_cut.jpg";
		ImageUtils.diagram(srcImage, destImage, 113, 171, 140, 140);
	}
}