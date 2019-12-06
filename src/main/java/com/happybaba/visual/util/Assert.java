package com.happybaba.visual.util;
/**
 * @author Pouee
 * @version 1.0
 * 类似于JUnit的Assert类 
 */
public class Assert {

	/**
	 * 判断指定的表达式是否为true
	 * 如果为false则抛出#IllegalArgumentException
	 * @param expression 指定表达式
	 * @param message 异常信息
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * 判断指定的表达式是否为true
	 * 如果为false则抛出#IllegalArgumentException
	 * @param expression 指定表达式
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
	
	/**
	 * 判断指定的对象是否为空
	 * 如果为不为空则抛出#IllegalArgumentException
	 * @param object 指定对象
	 * @param message 异常信息
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断指定的对象是否为空
	 * 如果为不为空则抛出#IllegalArgumentException
	 * @param object 指定对象
	 */
	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}

	/**
	 * 判断指定的对象是否为空
	 * 如果为为空则抛出#IllegalArgumentException
	 * @param object 指定对象
	 * @param message 异常信息
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * 判断指定的对象是否为空
	 * 如果为为空则抛出#IllegalArgumentException
	 * @param object 指定对象
	 */
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}
}
