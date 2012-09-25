/*
 * Created on 19-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package jin.collection.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtilsBean;

public class PropertyUtil {

	private static PropertyUtil instance = new PropertyUtil();

	public static boolean hasGetter(final String property, final Class type) {
		final String methodName = "get" + capitalize(property);
		try {
			final Method m = type.getMethod(methodName, null);
		} catch (final NoSuchMethodException e) {
			return false;
		}
		return true;
	}

	static Object getPropertyByField(final Object element, final String property) {
		try {
			final Field declaredField = element.getClass().getDeclaredField(property);
			declaredField.setAccessible(true);
			return declaredField.get(element);
		} catch (final Exception e) {
			throw new RuntimeException("", e);
		}
	}

	public static Object getProperty(final Object element, final String property) {
		return getInstance().getNestedProperty(element, property);
	}

	protected Object getNestedProperty(Object element, String property) {

		try {
			return BeanUtilsBean.getInstance().getPropertyUtils().getNestedProperty(element, property);
		} catch (final IllegalAccessException e) {
			throw new PropertyAccessException(e);
		} catch (final InvocationTargetException e) {
			throw new PropertyAccessException(e);
		} catch (final NoSuchMethodException e) {
			throw new PropertyAccessException(e);
		}

		// try {
		// return lookUpValue(element, property);
		// } catch (final Exception e) {
		// throw new RuntimeException(e);
		// }
	}

	private static PropertyUtil getInstance() {
		return instance;
	}

	private static Object lookUpValue(final Object element, final String property) throws Exception {
		try {
			final String propertyName = "get" + capitalize(property);
			return getMethodResult(element, propertyName);
		} catch (final NoSuchMethodException e1) {
			try {
				final String propertyName = "is" + capitalize(property);
				return getMethodResult(element, propertyName);
			} catch (final NoSuchMethodException e2) {
				try {
					return ReflectionUtil.getPrivateField(element, property);
				} catch (final NoSuchFieldException e3) {
					throw new RuntimeException("cannot find property " + property + " on object: " + element);
				}
			}
		}
	}

	private static String capitalize(final String property) {
		return Character.toUpperCase(property.charAt(0)) + property.substring(1);
	}

	static Object getMethodResult(final Object element, final String methodName) throws Exception {
		return getMethodResult(element, methodName, null);
	}

	static Object getMethodResult(final Object element, final String methodName, final Object param) throws Exception {

		Class[] classes = null;
		Object[] params = null;
		if (param != null) {
			classes = new Class[] { param.getClass() };
			params = new Object[] { param };
		}

		// TODO: sistemare
		final Method m = element.getClass().getMethod(methodName, classes);
		m.setAccessible(true);
		return m.invoke(element, params);
	}

	public static void setProperty(final Object element, final String toProperty, final Object param) {
		try {
			// TODO: sistemare
			Class[] classes = null;
			Object[] params = null;
			if (param != null) {
				classes = new Class[] { param.getClass() };
				params = new Object[] { param };
			}

			final String propertyName = "set" + capitalize(toProperty);
			final Method m = element.getClass().getMethod(propertyName, classes);
			m.invoke(element, params);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String nullSafeGet(final Object element, final String property) {
		String value = (String) getProperty(element, property);
		if (value == null) {
			value = "";
		}
		return value;
	}

	public static Double getDouble(final Object element, final String property) {
		return Double.valueOf((String) getProperty(element, property));
	}

	public static Boolean getBoolean(final Object element, final String property) {
		return Boolean.valueOf((String) getProperty(element, property));
	}

	public static Object getInteger(final Object element, final String property) {
		return Integer.valueOf((String) getProperty(element, property));
	}

	public static void setInstance(PropertyUtil propertyUtil) {
		instance = propertyUtil;
	}
}

/**
 * Copyright 2007, Lorenzo Bolzani
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/MPL-1.1.html
 **/
