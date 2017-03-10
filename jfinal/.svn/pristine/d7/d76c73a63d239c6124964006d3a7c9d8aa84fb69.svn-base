package com.xiaoan.wlt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class Common {

	/**
	 * 校验LONG对象是否大于0
	 * 
	 * @param i
	 * @return:true Long是大于0的值
	 */
	public static final boolean checkLong(Long l) {
		if (l == null || l <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 校验IDS的有效性，全部通过，号分隔，并能转换成Long
	 * 
	 * @param ids
	 * @return
	 */
	public static final boolean checkIds(String ids) {
		if (StringUtils.isBlank(ids))
			return false;

		String[] tmp = StringUtils.split(ids, ",");
		Long l = null;
		try {
			for (String s : tmp) {
				l = new Long(s);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取真实的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public final static String getRealIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HelloWorld->HELLO_WORLD
	 * 
	 * @param name
	 *            转换前的驼峰式命名的字符串
	 * @return 转换后下划线大写方式命名的字符串
	 */
	public static String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			// 将第一个字符处理成大写
			result.append(name.substring(0, 1).toUpperCase());
			// 循环处理其余字符
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				// 在大写字母前添加下划线
				if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				// 其他字符直接转成大写
				result.append(s.toUpperCase());
			}
		}
		return result.toString();
	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HELLO_WORLD->HelloWorld
	 * 
	 * @param name
	 *            转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 处理真正的驼峰片段
			if (result.length() == 0) {
				// 第一个驼峰片段，全部字母都小写
				result.append(camel.toLowerCase());
			} else {
				// 其他的驼峰片段，首字母大写
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}
	
	  /** 默认的格式化方式 */
    private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String formatCurrentDate = dateFormat.format(currentDate).toString();

        return formatCurrentDate;
    }

    public static String getCurrentDate() {
        String format = "yyyy-MM-dd";
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        if (format == null || "".equals(format.trim())) {
            format = defaultFormat;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getCurrentTime() {
        String format = "yyyyMMddHHmmss";
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        if (format == null || "".equals(format.trim())) {
            format = defaultFormat;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
