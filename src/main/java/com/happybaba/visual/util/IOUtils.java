package com.happybaba.visual.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * IO读写操作类
 * @author Pouee
 * @version 1.0
 * 注意：该类所有操作都不关闭流
 */
public class IOUtils {
	
	public static final int BUFFER_SIZE = 4096;
	
	/**
	 * 从指定的输入流读取数据 并写入到输出流中
	 * @param in 输入流
	 * @param out 输出流
	 * @return 返回所复制的字节数量
	 * @throws IOException IO错误
	 */
	public static long copy(InputStream in, OutputStream out) throws IOException {
		Assert.notNull(in, "No InputStream specified");
		Assert.notNull(out, "No OutputStream specified");
		long byteCount = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
			byteCount += bytesRead;
		}
		out.flush();
		return byteCount;
	}
	
	/**
	 * 从指定的输入流中读取字节数组
	 * @param in 输入流
	 * @return 字节数组
	 * @throws IOException IO错误
	 */
	public static byte[] read(InputStream in) throws IOException{
		Assert.notNull(in, "No InputStream specified");
		ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
		copy(in, out);
		return out.toByteArray();
	}
	/**
	 * 从指定的输入流中读取文本文件
	 * @param in 输入流
	 * @param charset 编码
	 * @return 字符串
	 * @throws IOException IO错误
	 * @throws UnsupportedEncodingException 不支持指定编码
	 */
	public static String readAsText(InputStream in,String charset) throws UnsupportedEncodingException, IOException{
		Assert.notNull(in,"No InputStream specified");
		Assert.notNull(charset,"No Charset specified");
		return new String(read(in),charset);
	}
	/**
	 * 从指定的输入流中用UTF-8格式读取文本文件
	 * @param in 输入流
	 * @return 字符串
	 * @throws IOException IO错误
	 * @throws UnsupportedEncodingException 不支持指定编码
	 */
	public static String readAsText(InputStream in) throws UnsupportedEncodingException, IOException{
		return new String(read(in),"utf-8");
	}
	
	/**
	 * 将指定的字节数组写入输出流中
	 * @param in 字节数组
	 * @param out 输出流
	 * @throws IOException IO错误
	 */
	public static void copy(byte[] in, OutputStream out) throws IOException {
		Assert.notNull(in, "No input byte array specified");
		Assert.notNull(out, "No OutputStream specified");
		out.write(in);
	}
	
}
