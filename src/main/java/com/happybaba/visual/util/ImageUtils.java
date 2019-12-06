/**
 * @Title: ImageUtils.java
 * @Description: 图片操作工具包
 * Copyright: Copyright (c) 2019
 * 
 * @author Pouee
 * @version 1.0
 */
package com.happybaba.visual.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName: ImageUtils
 * @Description: 图片操作工具包
 * @author Pouee
 *
 */
public abstract class ImageUtils {
	
	/**
	 * @ClassName: VerifyCodeUtils
	 * @Description: 验证工具类
	 * @author Pouee
	 * 该类用于创建验证码
	 */
	public static abstract class VerifyCodeUtils {
		/**
		 * 验证码字符
		 */
		private static final String LETTER="abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
		/**
		 * 产生随机数的工具类
		 */
		private static final Random random = new Random();
		
		/**
		 * 该方法用于构造验证码
		 * @Title: createVerifyCode
		 * @Description: 构造验证码
		 * @param w 验证码宽度
		 * @param h 验证码高度
		 * @param code 验证码内容
		 * @return 验证码对象 BufferedImage
		 * @throws IOException 如果发生错误 将抛出IOException
		 */
		public static BufferedImage createVerifyCode(int w, int h,String code) throws IOException {
			int verifySize = code.length();
			BufferedImage image = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);
			Random rand = new Random();
			Graphics2D g2 = image.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			Color[] colors = new Color[5];
			Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
					Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
					Color.PINK, Color.YELLOW };
			float[] fractions = new float[colors.length];
			for (int i = 0; i < colors.length; i++) {
				colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
				fractions[i] = rand.nextFloat();
			}
			Arrays.sort(fractions);
			g2.setColor(Color.GRAY);// 设置边框色
			g2.fillRect(0, 0, w, h);
			Color c = getRandColor(200, 250);
			g2.setColor(c);// 设置背景色
			g2.fillRect(0, 2, w, h - 4);
			// 绘制干扰线
			Random random = new Random();
			g2.setColor(getRandColor(160, 200));// 设置线条的颜色
			for (int i = 0; i < 20; i++) {
				int x = random.nextInt(w - 1);
				int y = random.nextInt(h - 1);
				int xl = random.nextInt(6) + 1;
				int yl = random.nextInt(12) + 1;
				g2.drawLine(x, y, x + xl + 40, y + yl + 20);
			}
			// 添加噪点
			float yawpRate = 0.05f;// 噪声率
			int area = (int) (yawpRate * w * h);
			for (int i = 0; i < area; i++) {
				int x = random.nextInt(w);
				int y = random.nextInt(h);
				int rgb = getRandomIntColor();
				image.setRGB(x, y, rgb);
			}
			shear(g2, w, h, c);// 使图片扭曲
			g2.setColor(getRandColor(100, 160));
			int fontSize = h - 4;
			Font font = new Font("Algerian", Font.ITALIC, fontSize);
			g2.setFont(font);
			char[] chars = code.toCharArray();
			for (int i = 0; i < verifySize; i++) {
				AffineTransform affine = new AffineTransform();
				affine.setToRotation(
						Math.PI / 4 * rand.nextDouble()
								* (rand.nextBoolean() ? 1 : -1),
						(w / verifySize) * i + fontSize / 2, h / 2);
				g2.setTransform(affine);
				g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h
						/ 2 + fontSize / 2 - 10);
			}
			g2.dispose();
			return image;
		}
		private static Color getRandColor(int fc, int bc) {
			if (fc > 255)
				fc = 255;
			if (bc > 255)
				bc = 255;
			int r = fc + random.nextInt(bc - fc);
			int g = fc + random.nextInt(bc - fc);
			int b = fc + random.nextInt(bc - fc);
			return new Color(r, g, b);
		}
		private static int getRandomIntColor() {
			int[] rgb = getRandomRgb();
			int color = 0;
			for (int c : rgb) {
				color = color << 8;
				color = color | c;
			}
			return color;
		}
		private static int[] getRandomRgb() {
			int[] rgb = new int[3];
			for (int i = 0; i < 3; i++) {
				rgb[i] = random.nextInt(255);
			}
			return rgb;
		}
		private static void shear(Graphics g, int w1, int h1, Color color) {
			shearX(g, w1, h1, color);
			shearY(g, w1, h1, color);
		}
		private static void shearX(Graphics g, int w1, int h1, Color color) {
			int period = random.nextInt(2);
			boolean borderGap = true;
			int frames = 1;
			int phase = random.nextInt(2);

			for (int i = 0; i < h1; i++) {
				double d = (double) (period >> 1)
						* Math.sin((double) i / (double) period
								+ (6.2831853071795862D * (double) phase)
								/ (double) frames);
				g.copyArea(0, i, w1, 1, (int) d, 0);
				if (borderGap) {
					g.setColor(color);
					g.drawLine((int) d, i, 0, i);
					g.drawLine((int) d + w1, i, w1, i);
				}
			}
		}
		private static void shearY(Graphics g, int w1, int h1, Color color) {
			int period = random.nextInt(40) + 10; // 50;
			boolean borderGap = true;
			int frames = 20;
			int phase = 7;
			for (int i = 0; i < w1; i++) {
				double d = (double) (period >> 1)
						* Math.sin((double) i / (double) period
								+ (6.2831853071795862D * (double) phase)
								/ (double) frames);
				g.copyArea(i, 0, 1, h1, 0, (int) d);
				if (borderGap) {
					g.setColor(color);
					g.drawLine(i, (int) d, i, 0);
					g.drawLine(i, (int) d + h1, i, h1);
				}

			}
		}
	
		/**
		 * 
		 * 该方法用于产生一个指定长度 的验证字符串
		 * @Title: getRandom
		 * @Description: 产生一个指定长度 的验证字符串
		 * @param len 长度
		 * @return 字符串
		 */
		public static String getRandom(int len){
			char[] ch=LETTER.toCharArray();
			Random rand =new Random();
			String result ="";
			while(len--!=0)result+=ch[rand.nextInt(ch.length)];
			return result;
		}
	}
	
	/**
	 * @ClassName: ThumbnailGenerator
	 * @Description: 图片压缩 生成缩略图工具类
	 * @author Pouee
	 */
	public static abstract class ThumbnailGenerator {
		
		/**
		 * 该方法用于生成缩略图
		 * @Title: transform
		 * @Description: 生成缩略图
		 * @param originalFile  源图片路径
		 * @param thumbWidth 缩略图宽
		 * @param thumbHeight 缩略图高
		 * @return 图片对象
		 * @throws Exception
		 */
		public static BufferedImage transform(String originalFile, int thumbWidth, int thumbHeight) throws Exception {
			Image image = javax.imageio.ImageIO.read(new File(originalFile));
		    BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		    Graphics2D graphics2D = thumbImage.createGraphics();
		    graphics2D.setBackground(Color.WHITE);
	    	graphics2D.setPaint(Color.WHITE); 
	    	graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
		    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		    return thumbImage;
		}
	}
}
