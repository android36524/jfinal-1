package com.xiaoan.wlt.common.tag;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * 自定义jstl函数
 * 
 * @author LeeSin
 *
 */
public class MyFunctions {

	public static String max(String a, String b) {
		if (new BigDecimal(a).compareTo(new BigDecimal(b)) < 0) {
			return b;
		}
		return a;
	}

	public static String min(String a, String b) {
		if (new BigDecimal(a).compareTo(new BigDecimal(b)) > 0) {
			return b;
		}
		return a;
	}

	public static String max(String... values) {
		if (values == null || values.length == 0) {
			return "";
		}
		String result = values[0];
		for (String val : values) {
			if (new BigDecimal(result).compareTo(new BigDecimal(val)) < 0) {
				result = val;
			}
		}
		return result;
	}

	public static String min(String... values) {
		if (values == null || values.length == 0) {
			return "";
		}
		String result = values[0];
		for (String val : values) {
			if (new BigDecimal(result).compareTo(new BigDecimal(val)) > 0) {
				result = val;
			}
		}
		return result;
	}

	public static Object enumVal(String className, String enumName,
			String propName) throws Exception {
		Object result = null;
		Class<?> enumClazz = Class.forName(className);
		Field enumField = enumClazz.getDeclaredField(enumName);
		Field propField = enumClazz.getDeclaredField(propName);
		enumField.setAccessible(true);
		propField.setAccessible(true);
		result = propField.get(enumField.get(null));
		return result;
	}

	/**
	 * 比较两个数值字符串大小，如果第一个大于第二个，返回一个正值，如果相等，返回0，如果小于，返回负值
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int compare(String a, String b) {
		try {
			return Double.valueOf(a).compareTo(Double.parseDouble(b));
		} catch (NumberFormatException e) {
			return a.compareTo(b);
		}
	}

	public static boolean contains(Collection<?> c, Object obj) {
		if (c == null || obj == null)
			return false;
		return c.contains(obj);
	}

}
