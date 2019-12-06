package com.happybaba.visual.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 反射操作类
 * @author Pouee
 *
 */
public class ReflectionUtils {
	
	/**
	 * 从指定的类上的所有的定义字段中查找指定的字段
	 * @param clazz 指定类
	 * @param name 字段名称
	 * @return 字段
	 */
	public static Field findField(Class<?> clazz, String name) {
		return findField(clazz, name, null);
	}
	/**
	 * 从指定的类上的所有的定义字段中查找指定的字段
	 * @param clazz 指定类
	 * @param name 字段名称
	 * @param type 指定字段类型
	 * @return 字段
	 */
	public static Field findField(Class<?> clazz, String name, Class<?> type) {
		Assert.notNull(clazz, "Class must not be null");
		Assert.isTrue(name != null || type != null, "Either name or type of the field must be specified");
		Class<?> searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			Field[] fields = searchType.getDeclaredFields();
			for (Field field : fields) {
				if ((name == null || name.equals(field.getName())) && (type == null || type.equals(field.getType()))) {
					return field;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
	/**
	 * 从指定的类上查找指定的方法
	 * @param clazz 指定类
	 * @param name 方法名
	 * @return 方法
	 */
	public static Method findMethod(Class<?> clazz, String name) {
		return findMethod(clazz, name, new Class<?>[0]);
	}
	/**
	 * 从指定的类上查找指定的方法
	 * @param clazz 指定类
	 * @param name 方法名
	 * @return 方法
	 */
	public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(name, "Method name must not be null");
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
			for (Method method : methods) {
				if (name.equals(method.getName()) &&
						(paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes()))) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
	/**
	 * 使指定字段具有可访问权限
	 * @param field 指定字段
	 */
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
				Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}
	/**
	 * 使指定方法具有可访问权限
	 * @param method 指定方法
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}
	/**
	 * 使指定构造器具有可访问权限
	 * @param ctor 指定构造器
	 */
	public static void makeAccessible(Constructor<?> ctor) {
		if ((!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers()))
				&& !ctor.isAccessible()) {
			ctor.setAccessible(true);
		}
	}
	/**
	 * 根据指定对象调用get方法获取指定字段的值
	 * @param obj 指定对象 
	 * @param field 字段
	 * @return 指定对象的值
	 * 执行流程： <pre>
	 * 			Method method = obj.getClass().getDeclaredMethod("get"+StringUtils.leadingToUpperCase(field.getName()));
	 *			return method.invoke(obj);
	 *		  </pre>
	 * @throws SecurityException 详情见JDK文档
	 * @throws NoSuchMethodException 详情见JDK文档
	 * @throws IllegalArgumentException 详情见JDK文档
	 * @throws IllegalAccessException 详情见JDK文档
	 * @throws InvocationTargetException 详情见JDK文档
	 */
	public static Object callGet(Object obj,Field field) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = obj.getClass().getDeclaredMethod("get"+StringUtils.leadingToUpperCase(field.getName()));
		return method.invoke(obj);
	}
}

