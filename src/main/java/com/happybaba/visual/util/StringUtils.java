/**
 * @Title: StringUtils.java
 * @Description: 该类指定一些常用的操作字符串的方法
 * Copyright: Copyright (c) 2019
 * 
 * @author Pouee
 * @version 1.2
 */
package com.happybaba.visual.util;

/**
 * @ClassName: StringUtils
 * @Description: 该类指定一些常用的操作字符串的方法
 * @author Pouee
 */
public class StringUtils {
	/**
	 * 路径分割符
	 */
	private static final String FOLDER_SEPARATOR = "/";
	/**
	 * 扩展名开始标记
	 */
	private static final char EXTENSION_SEPARATOR = '.';
	/**
	 * 表示Yes的中文字符
	 */
	private static final String YES = "是";
	/**
	 * 表示No的中文字符
	 */
	private static final String NO = "否";
	
	
	//------------------------------------------
	//--------- Core Methods
	//------------------------------------------
	
	/**
	 * @Title: isEmpty
	 * @Description: 检查指定字符串是否为空
	 * @param str 字符串
	 * @return 仅当指定字符串的length为0时返回true
	 * 
	 */
	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str));
	}
	
	/**
	 * 该方法用于判断指定字符串是否长度大于0
	 * 比如：
	 * <pre class="code">
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @Title: hasLength
	 * @Description: 判断指定字符串是否长度大于0
	 * @param str 指定字符串
	 * @return 仅当指定字符串长度大于0时返回true
	 *
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * @Title: hasLength
	 * @Description: 判断指定字符串是否长度大于0
	 * @param str 指定字符串
	 * @return 仅当指定字符串长度大于0时返回true
	 * @see #hasLength(String)
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}
	
	/**
	 * 该方法用于检查指定字符串是否含有非空白字符
	 * 比如:
	 * <p><pre class="code">
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @Title: hasText 
	 * @Description: 检查指定字符串是否含有非空白字符
	 * @param str 指定字符串
	 * @return 如果含有非空白字符返回 true 否则返回false
	 * 
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @Title: hasText
	 * @Description: 检查指定字符串是否含有非空白字符
	 * @param str 指定字符串
	 * @return 如果含有非空白字符返回 true 否则返回false
	 * @see #hasText(CharSequence)
	 */
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}
	
	/**
	 * 该方法用于检查指定字符串是否包含空白字符
	 * @Title: containsWhitespace
	 * @Description: 检查指定字符串是否包含空白字符
	 * @param str 指定字符串
	 * @return 包含空白字符返回true 不包含则返回false
	 */
	public static boolean containsWhitespace(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 该方法用于检查指定字符串是否包含空白字符
	 * @Title: containsWhitespace
	 * @Description: 检查指定字符串是否包含空白字符
	 * @param str 指定字符串
	 * @return 包含空白字符返回true 不包含则返回false
	 */
	public static boolean containsWhitespace(String str) {
		return containsWhitespace((CharSequence) str);
	}
	
	/**
	 * 该方法用于去除指定字符串的前面及后面的空格
	 * @Title: trimWhitespace
	 * @Description: 去除指定字符串的前面及后面的空格
	 * @param str 指定字符串
	 * @return 去空格后的字符串
	 */
	public static String trimWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * 该方法用于去除指定字符串全部的空格字符
	 * @Title: trimAllWhitespace
	 * @Description: 去除指定字符串全部的空格字符
	 * @param str 指定字符串
	 * @return 去空格后的字符串
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			}
			else {
				index++;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 该方法用于去除指定字符串前面的空格
	 * @Title: trimLeadingWhitespace
	 * @Description: 去除指定字符串前面的空格
	 * @param str 指定字符串
	 * @return 去空格后的字符串
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	/**
	 * 该方法用于去除指定字符串后面的空格
	 * @Title: trimTrailingWhitespace
	 * @Description: 去除指定字符串后面的空格
	 * @param str 字符串
	 * @return 去空格后的字符串
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * 该方法用于替换指定字符串内的所有指定字符 
	 * @Title: replace
	 * @Description: 替换指定字符串内的所有指定字符 
	 * @param inString 指定字符串
	 * @param oldPattern 被替换的字符串
	 * @param newPattern 新字符串
	 * @return 替换完成后的字符串
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; 
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		return sb.toString();
	}
	
	/**
	 * 该方法用于删除指定字符串内的所有指定的字符串
	 * @Title: delete
	 * @Description: 删除指定字符串内的所有指定的字符串
	 * @param inString 指定的字符串
	 * @param pattern 指定要删除的字符串
	 * @return 删除完成的字符串
	 * @see #deleteAny(String, String)
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}
	
	/**
	 * 该方法用于去除指定字符串所有包含指定字符串的字符
	 * 比如:
	 * <p><pre class="code">
	 * StringUtils.deleteAny("abcab","b") = "aca"
	 * </pre>
	 * @Title: deleteAny
	 * @Description: 去除指定字符串所有包含指定字符串的字符
	 * @param inString 指定的字符串
	 * @param charsToDelete 将要的去除的所有字符
	 * @return 去除后的字符串
	 * @see #delete(String, String)
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (!hasLength(inString) || !hasLength(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 该方法用于给指定的字符串加上单引号
	 * 比如:
	 * <p><pre class="code">
	 * StringUtils.quote("abcab",) = "'abcab'"
	 * StringUtils.quote(null) = null
	 * </pre>
	 * @Title: quote
	 * @Description: 给指定的字符串加上单引号
	 * @param str 字符串
	 * @return 加上单引号的字符
	 */
	public static String quote(String str) {
		return (str != null ? "'" + str + "'" : null);
	}
	
	/**
	 * 该方法用于获取文件名
	 * @Title: getFileName
	 * @Description: 获取文件名
	 * @param path 文件路径
	 * @return 文件名 
	 */
	public static String getFileName(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}
	
	/**
	 * 该方法用于获得指定文件的扩展名
	 * @Title: getFilenameExtension
	 * @Description: 获得指定文件的扩展名
	 * @param path 指定文件路径
	 * @return 文件扩展名
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return path.substring(extIndex + 1);
	}
	
	/**
	 * 该方法用于HTML字符转义
	 * @Title: htmlEscape
	 * @Description: HTML字符转义
	 * @param input HTML字符
	 * @return 过滤后的字符
	 */
	public static String htmlEscape(String input) {
		if (isEmpty(input)) {
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;"); // IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
		input = input.replaceAll("\"", "&quot;"); // 双引号也需要转义，所以加一个斜线对其进行转义
		input = input.replaceAll("\n", "<br/>"); // 不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
		return input;
	}
	/**
	 * 该方法用于HTML字符简单转义
	 * @Title: htmlSimpleEscape
	 * @Description: HTML字符简单转义
	 * @param input HTML字符
	 * @return 过滤后的字符
	 */
	 public static String htmlSimpleEscape(String input) {
		  if(isEmpty(input)){
	            return input;
	       }
	       input = input.replaceAll("&", "&amp;");
	       input = input.replaceAll("<", "&lt;");
	       input = input.replaceAll(">", "&gt;");
	       return input;
	 }
	 
	 /**
	  * 该方法用于给指定字符串首字母大写
	  * @Title: leadingToUpperCase
	  * @Description: 首字母大写
	  * @param str 字符串
	  * @return 首字母大写后的字符串
	  */
	 public static String leadingToUpperCase(String str){
	    	char[] temp = str.toCharArray();
			temp[0] = Character.toUpperCase(temp[0]);
			return new String(temp);
	 }
	
	/**
	 * 将数字转化为是和否
	 * @Title: number2String
	 * @Description: 将数字转化为是和否
	 * @param num 参数
     * @return 如果参数为0 返回否
	 * @throws
	 */
	 public static String number2String(int num){
		 if(num == 0) return NO ;
		 return YES;
	 }
	 /**
	  * 该方法用于将指定字符串转换为Unicode码
	  * @Title: string2Unicode
	  * @Description: 将指定字符串转换为Unicode码
	  * @param str  字符串
	  * @return Unicode字符串
	  */
	 public static String string2Unicode(String str) {
		 StringBuffer result = new StringBuffer();
		 for (int i = 0; i < str.length(); i++) {
			 // 取出每一个字符
			 char c = str.charAt(i);
			 if(c < 0xff){
				 result.append(c);
				 continue;
			 }
			 String unicode = Integer.toHexString(c);
			 while (unicode.length() < 4)
				 unicode = "0" + unicode;
			 // 转换为unicode
			 result.append("\\u" + unicode);
		 }
		 return result.toString();
	 }

}





