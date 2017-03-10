package com.xiaoan.wlt.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * 通用方法
 * 
 * @author LeeSin
 *
 */
public class CommUtil {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	static int totalFolder = 0;
	static int totalFile = 0;

	public static String first2low(String str) {
		String s = "";
		s = str.substring(0, 1).toLowerCase() + str.substring(1);
		return s;
	}

	public static String first2upper(String str) {
		String s = "";
		s = str.substring(0, 1).toUpperCase() + str.substring(1);
		return s;
	}

	public static List<String> str2list(String s) throws IOException {
		List<String> list = new ArrayList<String>();
		if ((s != null) && (!s.equals(""))) {
			StringReader fr = new StringReader(s);
			BufferedReader br = new BufferedReader(fr);
			String aline = "";
			while ((aline = br.readLine()) != null) {
				list.add(aline);
			}
		}
		return list;
	}

	public static Date formatDate(String s) {
		Date d = null;
		try {
			d = dateFormat.parse(s);
		} catch (Exception localException) {
		}
		return d;
	}

	public static Date formatDate(String s, String format) {
		Date d = null;
		try {
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			d = dFormat.parse(s);
		} catch (Exception localException) {
		}
		return d;
	}

	public static String formatTime(String format, Object v) {
		if (v == null)
			return null;
		if (v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	public static String formatLongDate(Object v) {
		if ((v == null) || (v.equals("")))
			return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(v);
	}

	public static String formatShortDate(Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(v);
	}

	public static String decode(String s) {
		String ret = s;
		try {
			ret = URLDecoder.decode(s.trim(), "UTF-8");
		} catch (Exception localException) {
		}
		return ret;
	}

	public static String encode(String s) {
		String ret = s;
		try {
			ret = URLEncoder.encode(s.trim(), "UTF-8");
		} catch (Exception localException) {
		}
		return ret;
	}

	public static String convert(String str, String coding) {
		String newStr = "";
		if (str != null)
			try {
				newStr = new String(str.getBytes("ISO-8859-1"), coding);
			} catch (Exception e) {
				return newStr;
			}
		return newStr;
	}

	public static boolean isImg(String extend) {
		boolean ret = false;
		List<String> list = new ArrayList<String>();
		list.add("jpg");
		list.add("jpeg");
		list.add("bmp");
		list.add("gif");
		list.add("png");
		list.add("tif");
		for (String s : list) {
			if (s.equals(extend))
				ret = true;
		}
		return ret;
	}

	public static boolean createFolder(String folderPath) {
		boolean ret = true;
		try {
			File myFilePath = new File(folderPath);
			if ((!myFilePath.exists()) && (!myFilePath.isDirectory())) {
				ret = myFilePath.mkdirs();
				if (!ret)
					System.out.println("创建文件夹出错");
			}
		} catch (Exception e) {
			System.out.println("创建文件夹出错");
			ret = false;
		}
		return ret;
	}

	public static List<?> toRowChildList(List list, int perNum) {
		List l = new ArrayList();
		if (list == null) {
			return l;
		}

		for (int i = 0; i < list.size(); i += perNum) {
			List cList = new ArrayList();
			for (int j = 0; j < perNum; j++)
				if (i + j < list.size())
					cList.add(list.get(i + j));
			l.add(cList);
		}
		return l;
	}

	public static List copyList(List list, int begin, int end) {
		List l = new ArrayList();
		if (list == null)
			return l;
		if (end > list.size())
			end = list.size();
		for (int i = begin; i < end; i++) {
			l.add(list.get(i));
		}
		return l;
	}

	public static boolean isNotNull(Object obj) {
		if ((obj != null) && (!obj.toString().equals(""))) {
			return true;
		}
		return false;
	}

	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];

				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错 ");
			e.printStackTrace();
		}
	}

	public static boolean deleteFolder(String path) {
		boolean flag = false;
		File file = new File(path);

		if (!file.exists()) {
			return flag;
		}

		if (file.isFile()) {
			return deleteFile(path);
		}
		return deleteDirectory(path);
	}

	public static boolean deleteFile(String path) {
		boolean flag = false;
		File file = new File(path);

		if ((file.isFile()) && (file.exists())) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public static boolean deleteDirectory(String path) {
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		File dirFile = new File(path);

		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			return false;
		}
		boolean flag = true;

		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			return false;
		}
		if (dirFile.delete()) {
			return true;
		}
		return false;
	}

	public static String showPageStaticHtml(String url, int currentPage,
			int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s + "<a href='" + url + "_1.htm'>首页</a> ";
				if (currentPage > 1) {
					s = s + "<a href='" + url + "_" + (currentPage - 1)
							+ ".htm'>上一页</a> ";
				}
			}
			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "第　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s + "<a class='this' href='" + url + "_" + i
								+ ".htm'>" + i + "</a> ";
					else
						s = s + "<a href='" + url + "_" + i + ".htm'>" + i
								+ "</a> ";
					i++;
				}

				s = s + "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s + "<a href='" + url + "_" + (currentPage + 1)
							+ ".htm'>下一页</a> ";
				}
				s = s + "<a href='" + url + "_" + pages + ".htm'>末页</a> ";
			}
		}
		return s;
	}

	public static String showPageHtml(String url, String params,
			int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s + "<a href='" + url + "?currentPage=1" + params
						+ "'>首页</a> ";
				if (currentPage > 1) {
					s = s + "<a href='" + url + "?currentPage="
							+ (currentPage - 1) + params + "'>上一页</a> ";
				}
			}
			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "第　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s + "<a class='this' href='" + url
								+ "?currentPage=" + i + params + "'>" + i
								+ "</a> ";
					else
						s = s + "<a href='" + url + "?currentPage=" + i
								+ params + "'>" + i + "</a> ";
					i++;
				}

				s = s + "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s + "<a href='" + url + "?currentPage="
							+ (currentPage + 1) + params + "'>下一页</a> ";
				}
				s = s + "<a href='" + url + "?currentPage=" + pages + params
						+ "'>末页</a> ";
			}
		}

		return s;
	}

	public static String showPageFormHtml(int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return gotoPage(1)'>首页</a> ";
				if (currentPage > 1) {
					s = s
							+ "<a href='javascript:void(0);' onclick='return gotoPage("
							+ (currentPage - 1) + ")'>上一页</a> ";
				}
			}
			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "第　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return gotoPage("
								+ i + ")'>" + i + "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return gotoPage("
								+ i + ")'>" + i + "</a> ";
					i++;
				}

				s = s + "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s
							+ "<a href='javascript:void(0);' onclick='return gotoPage("
							+ (currentPage + 1) + ")'>下一页</a> ";
				}
				s = s
						+ "<a href='javascript:void(0);' onclick='return gotoPage("
						+ pages + ")'>末页</a> ";
			}
		}

		return s;
	}

	public static String showPageAjaxHtml(String url, String params,
			int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			String address = url + "?1=1" + params;
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\",1,this)'>首页</a> ";
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + (currentPage - 1)
						+ ",this)'>上一页</a> ";
			}

			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "第　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return ajaxPage(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					i++;
				}

				s = s + "页　";
			}
			if (currentPage <= pages) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + (currentPage + 1)
						+ ",this)'>下一页</a> ";
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + pages + ",this)'>末页</a> ";
			}
		}

		return s;
	}

	public static char randomChar() {
		char[] chars = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f',
				'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l',
				'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r',
				'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x',
				'X', 'y', 'Y', 'z', 'Z' };
		int index = (int) (Math.random() * 52.0D) - 1;
		if (index < 0) {
			index = 0;
		}
		return chars[index];
	}

	public static String[] splitByChar(String s, String c) {
		String[] list = s.split(c);
		return list;
	}

	public static Object requestByParam(HttpServletRequest request, String param) {
		if (!request.getParameter(param).equals("")) {
			return request.getParameter(param);
		}
		return null;
	}

	public static String substring(String s, int maxLength) {
		if (isBlank(s))
			return s;
		if (s.length() <= maxLength) {
			return s;
		}
		return s.substring(0, maxLength) + "...";
	}

	public static String substringfrom(String s, String from) {
		if (s.indexOf(from) < 0)
			return "";
		return s.substring(s.indexOf(from) + from.length());
	}

	/**
	 * 将s转为Integer，失败返回-1
	 * 
	 * @param s
	 * @return
	 */
	public static Integer null2Integer(Object s) {
		Integer v = -1;
		if (s != null)
			try {
				v = Integer.valueOf(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	public static Integer zero2Integer(Object s) {
		Integer v = 0;
		if (s != null)
			try {
				v = Integer.valueOf(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	public static int null2Int(Object s) {
		int v = 0;
		if (s != null)
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 将类型强制转换为float，不能转的返回0F
	 * 
	 * @param s
	 * @return
	 */
	public static float null2Float(Object s) {
		float v = 0.0F;
		if (s != null)
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 将字符串转为Double，不能转换的返回0.0D
	 * 
	 * @param s
	 * @return
	 */
	public static double null2Double(Object s) {
		double v = 0.0D;
		if (s != null)
			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 将字符串转为Double，不能转换的返回0.0D
	 * 
	 * @param s
	 * @return
	 */
	public static double zero2Double(Object s) {
		return null2Double(s);
	}

	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if (s != null)
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 返回s的字符串，如果s为null，返回""空字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String null2String(Object s) {
		return s == null ? "" : s.toString();
	}

	public static String zero2String(Object s) {
		return s == null ? "0" : s.toString();
	}

	/**
	 * 将字符串解析成Long，如果解析失败，返回-1L
	 * 
	 * @param s
	 * @return
	 */
	public static Long null2Long(Object s) {
		Long v = Long.valueOf(-1L);
		if (s != null)
			try {
				v = Long.valueOf(Long.parseLong(s.toString()));
			} catch (Exception localException) {
			}
		return v;
	}

	public static Long zero2Long(Object s) {
		Long v = Long.valueOf(0L);
		if (s != null)
			try {
				v = Long.valueOf(Long.parseLong(s.toString()));
			} catch (Exception localException) {
			}
		return v;
	}

	public static Long[] null2LongArray(Object[] arr) {
		List<Long> list = new ArrayList<Long>();
		for (Object obj : arr) {
			Long v = null;
			if (obj != null) {
				try {
					v = Long.parseLong(obj.toString());
				} catch (Exception e) {
				}
			}
			list.add(v);
		}
		return list.toArray(new Long[] {});
	}

	public static Long[] null2LongArraySQL(Object[] arr) {
		List<Long> list = new ArrayList<Long>();
		for (Object obj : arr) {
			Long v = -1L;
			if (obj != null) {
				try {
					v = Long.parseLong(obj.toString());
				} catch (Exception e) {
				}
			}
			list.add(v);
		}
		return list.toArray(new Long[] {});
	}

	public static String getTimeInfo(long time) {
		int hour = (int) time / 3600000;
		long balance = time - hour * 1000 * 60 * 60;
		int minute = (int) balance / 60000;
		balance -= minute * 1000 * 60;
		int seconds = (int) balance / 1000;
		String ret = "";
		if (hour > 0)
			ret = ret + hour + "小时";
		if (minute > 0)
			ret = ret + minute + "分";
		else if ((minute <= 0) && (seconds > 0))
			ret = ret + "零";
		if (seconds > 0)
			ret = ret + seconds + "秒";
		return ret;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static int indexOf(String s, String sub) {
		return s.trim().indexOf(sub.trim());
	}

	public static Map cal_time_space(Date begin, Date end) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long l = end.getTime() - begin.getTime();
		long day = l / 86400000L;
		long hour = l / 3600000L - day * 24L;
		long min = l / 60000L - day * 24L * 60L - hour * 60L;
		long second = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L
				- min * 60L;
		Map map = new HashMap();
		map.put("day", Long.valueOf(day));
		map.put("hour", Long.valueOf(hour));
		map.put("min", Long.valueOf(min));
		map.put("second", Long.valueOf(second));
		return map;
	}

	public static final String randomString(int length) {
		char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				.toCharArray();
		if (length < 1) {
			return "";
		}
		Random randGen = new Random();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String randomInt(int length) {
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters = "0123456789".toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

	public static long getDateDistance(String time1, String time2) {
		long quot = 0L;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000L / 60L / 60L / 24L;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static double div(Object a, Object b) {
		double ret = 0.0D;
		if ((!null2String(a).equals("")) && (!null2String(b).equals(""))) {
			BigDecimal e = new BigDecimal(null2String(a));
			BigDecimal f = new BigDecimal(null2String(b));
			if (null2Double(f) > 0.0D)
				ret = e.divide(f, 3, 1).doubleValue();
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double subtract(Object a, Object b) {
		double ret = 0.0D;
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		ret = e.subtract(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double add(Object a, Object b) {
		double ret = 0.0D;
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		ret = e.add(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double mul(Object a, Object b) {
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		double ret = e.multiply(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double formatMoney(Object money) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(money)).doubleValue();
	}

	public static int M2byte(float m) {
		float a = m * 1024.0F * 1024.0F;
		return (int) a;
	}

	public static boolean convertIntToBoolean(int intValue) {
		return intValue != 0;
	}

	public static String getURL(HttpServletRequest request) {
		String contextPath = request.getContextPath().equals("/") ? ""
				: request.getContextPath();
		String url = "http://" + request.getServerName();
		if (null2Int(Integer.valueOf(request.getServerPort())) != 80)
			url = url + ":"
					+ null2Int(Integer.valueOf(request.getServerPort()))
					+ contextPath;
		else {
			url = url + contextPath;
		}
		return url;
	}

	public static int parseDate(String type, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (type.equals("y")) {
			return cal.get(1);
		}
		if (type.equals("M")) {
			return cal.get(2) + 1;
		}
		if (type.equals("d")) {
			return cal.get(5);
		}
		if (type.equals("H")) {
			return cal.get(11);
		}
		if (type.equals("m")) {
			return cal.get(12);
		}
		if (type.equals("s")) {
			return cal.get(13);
		}
		return 0;
	}

	public static boolean fileExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	public static int splitLength(String s, String c) {
		int v = 0;
		if (!s.trim().equals("")) {
			v = s.split(c).length;
		}
		return v;
	}

	public static double fileSize(File folder) {
		totalFolder += 1;

		long foldersize = 0L;
		File[] filelist = folder.listFiles();
		for (int i = 0; i < filelist.length; i++) {
			if (filelist[i].isDirectory()) {
				// 20150124
				foldersize = (long) (foldersize + fileSize(filelist[i]));
			} else {
				totalFile += 1;
				foldersize += filelist[i].length();
			}
		}
		return div(Long.valueOf(foldersize), Integer.valueOf(1024));
	}

	public static int fileCount(File file) {
		if (file == null) {
			return 0;
		}
		if (!file.isDirectory()) {
			return 1;
		}
		int fileCount = 0;
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				fileCount++;
			} else if (f.isDirectory()) {
				fileCount++;
				fileCount += fileCount(file);
			}
		}
		return fileCount;
	}

	public static String get_all_url(HttpServletRequest request) {
		String query_url = request.getRequestURI();
		if ((request.getQueryString() != null)
				&& (!request.getQueryString().equals(""))) {
			query_url = query_url + "?" + request.getQueryString();
		}
		return query_url;
	}

	public static Color getColor(String color) {
		if (color.charAt(0) == '#') {
			color = color.substring(1);
		}
		if (color.length() != 6)
			return null;
		try {
			int r = Integer.parseInt(color.substring(0, 2), 16);
			int g = Integer.parseInt(color.substring(2, 4), 16);
			int b = Integer.parseInt(color.substring(4), 16);
			return new Color(r, g, b);
		} catch (NumberFormatException nfe) {
		}
		return null;
	}

	public static Set<Integer> randomInt(int a, int length) {
		Set list = new TreeSet();
		int size = length;
		if (length > a) {
			size = a;
		}
		while (list.size() < size) {
			Random random = new Random();
			int b = random.nextInt(a);
			list.add(Integer.valueOf(b));
		}
		return list;
	}

	public static Double formatDouble(Object obj, int len) {
		Double ret = Double.valueOf(0.0D);
		String format = "0.0";
		for (int i = 1; i < len; i++) {
			format = format + "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		return Double.valueOf(df.format(obj));
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if ((ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
				|| (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS)
				|| (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A)
				|| (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
				|| (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION)
				|| (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)) {
			return true;
		}
		return false;
	}

	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0.0F;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count += 1.0F;
					System.out.print(c);
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4D) {
			return true;
		}
		return false;
	}

	public static String trimSpaces(String IP) {
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

	public static boolean isIp(String IP) {
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String[] s = IP.split("\\.");
			if ((Integer.parseInt(s[0]) < 255)
					&& (Integer.parseInt(s[1]) < 255)
					&& (Integer.parseInt(s[2]) < 255)
					&& (Integer.parseInt(s[3]) < 255))
				b = true;
		}
		return b;
	}

	public static String generic_domain(HttpServletRequest request) {
		String system_domain = "localhost";
		String serverName = request.getServerName();
		if (isIp(serverName))
			system_domain = serverName;
		else {
			system_domain = serverName.substring(serverName.indexOf(".") + 1);
		}

		return system_domain;
	}

	/**
	 * 返回第一个非空（对于Object是null，对于String是null或者空/空白字符串）的对象
	 * 
	 * @param objs
	 * @return
	 */
	public static <E> E coalesce(E... objs) {
		for (E e : objs) {
			if (e == null || (e instanceof String && isBlank((String) e))) {
				continue;
			}
			return e;
		}
		return null;
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 * @since 2.0
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * check if the object is the default value or not
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isDefault(Object obj) {
		if (obj instanceof Integer)
			return obj == null || (Integer) obj == 0;
		else if (obj instanceof Long)
			return obj == null || (Long) obj == 0L;
		else if (obj instanceof Float)
			return obj == null || (Float) obj == 0.0F;
		else if (obj instanceof Double)
			return obj == null || (Double) obj == 0.0D;
		else if (obj instanceof Boolean)
			return obj == null || (Boolean) obj == false;
		else
			return obj == null;
	}

	/**
	 * 将字符串数组转为Long列表，对空的字符串做跳过处理。 若字符串数组strs为空，返回空的Long列表
	 * 
	 * @param strs
	 * @return
	 */
	public static List<Long> strings2LongList(String[] strs) {
		List<Long> list = new ArrayList<Long>();
		for (String str : strs) {
			if (StringUtils.isBlank(str))
				continue;
			list.add(Long.valueOf(str));
		}
		return list;
	}

	/**
	 * 将字符串集合转为Long列表，对空字符串做跳过处理。若字符串集合strs为空，返回空的Long列表
	 * 
	 * @param strs
	 * @return
	 */
	public static List<Long> strings2LongList(Iterable<String> strs) {
		List<Long> list = new ArrayList<Long>();
		for (String str : strs) {
			if (StringUtils.isBlank(str))
				continue;
			list.add(Long.valueOf(str));
		}
		return list;
	}

	/**
	 * 将json格式的字符串解析成Map对象 <li>
	 * json格式：{"name":"admin","retries":"3fff","testname"
	 * :"ddd","testretries":"fffffffff"}
	 * 
	 * @author LeeSin
	 * @param jsonObject
	 *            可以转换成Json的JavaBean或者字符串
	 * @return 返回JSON转成的map。如果JSONObject为null或空字符串，返回一个空的map
	 */
	public static Map<String, Object> json2Map(Object jsonObject) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (jsonObject == null || jsonObject.equals("")) {
			return result;
		}
		JSONObject json = JSONObject.fromObject(jsonObject);
		for (Object obj : json.keySet()) {
			result.put(obj.toString(), json.get(obj));
		}
		return result;
	}

	/**
	 * 索引转换，count = arr.length; arr[-1] = arr[count-1];
	 * 
	 * @param idx
	 *            索引，正负，或超出数组长度的索引
	 * @param count
	 *            数组长度
	 * @return 真实索引
	 * 
	 * @author LeeSin
	 */
	public static int index(int idx, int count) {
		int tmp = idx % count;
		if (tmp >= 0)
			return tmp;
		else {
			return tmp + count;
		}
	}
}