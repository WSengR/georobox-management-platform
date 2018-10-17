package com.geostar.georobox.management.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
	public static final String TimeFormat = "yyyy-MM-dd HH:mm:ss";
	public static final String DataFormat = "yyyy-MM-dd";
	public static final String TimeFormat2 = "yyyy-MM-dd HH:mm";

	
	/**
	 * data的时间转换为 2017-09-04 转为 1504454400000
	 */
	public static String dataToLong(String timeString) {
		try {
			Date date = new SimpleDateFormat(DataFormat).parse(timeString);
			long unixTimestamp = date.getTime();
			return String.valueOf(unixTimestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * data的时间转换为 2017-09-04 转为 1504454400000
	 */
	public static String dataToLong(String timeString,String DataFormat) {
		try {
			Date date = new SimpleDateFormat(DataFormat).parse(timeString);
			long unixTimestamp = date.getTime();
			return String.valueOf(unixTimestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	   * 获取现在时间
	   * 
	   * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	   */
	public static Date getNowDate() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);
	   ParsePosition pos = new ParsePosition(8);
	   Date currentTime_2 = formatter.parse(dateString, pos);
	   return currentTime_2;
	}

	/**
	 * data的时间转换为 1504454400000 转为 2017-09-04
	 */
	public static String longToData(String timeString) {

		long start = Long.valueOf(timeString);
		Date date = new Date(start);
		SimpleDateFormat format = new SimpleDateFormat(DataFormat);
		return format.format(date);
	}
	
	/**
	 * data的时间转换为 1504454400000 转为 2017-09-04
	 */
	public static String longToData(String timeString,String DataFormat) {
		long start = Long.valueOf(timeString);
		Date date = new Date(start);
		SimpleDateFormat format = new SimpleDateFormat(DataFormat);
		return format.format(date);
	}

	public static String getCurrentDate(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

	/**
	 * 获取下一天的时间
	 * 
	 * @param timeString
	 * @return
	 */
	public static String getNextLoneData(String timeString) {
		try {
			Date date = new SimpleDateFormat(DataFormat).parse(timeString);
			long unixTimestamp = date.getTime() + 86400000;
			return String.valueOf(unixTimestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static int[] getDate(String timeString) {
		try {
			int[] reDates = new int[3];
			Date date = new SimpleDateFormat(DataFormat).parse(timeString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			reDates[0] = calendar.get(Calendar.YEAR);
			reDates[1] = calendar.get(Calendar.MONTH);
			reDates[2] = calendar.get(Calendar.DAY_OF_MONTH);
			System.out.println("年: " + calendar.get(Calendar.YEAR));
			System.out.println("月: " + calendar.get(Calendar.MONTH));
			System.out.println("日: " + calendar.get(Calendar.DAY_OF_MONTH));
			return reDates;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取前n天的时间
	 *
	 * @param timeString
	 * @return
	 */
	public static String getBefoerData(String timeString, int n) {
		try {
			Date date = new SimpleDateFormat(DataFormat).parse(timeString);
			long unixTimestamp = date.getTime() - 86400000l * n;
			System.out.println(unixTimestamp);
			date.setTime(unixTimestamp);
			SimpleDateFormat format = new SimpleDateFormat(DataFormat);
			return format.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前时间多少天前
	 * 
	 * @param n
	 */
	public static String getCurrentDateAgo(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, n);
		Date time = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DataFormat);
		return format.format(time);
	}

	/**
	 * 获取当前时间多少月前
	 * 
	 * @param n
	 */
	public static String getCurrentMonthAgo(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, n);
		Date time = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DataFormat);
		return format.format(time);
	}
	
	/**
	 * 设置为当前月的第�?�?
	 * @return
	 */
	public static String getCurrentMonthFirst() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);
		Date time = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DataFormat);
		return format.format(time);
	}
	
	/**
	 * 设置为当前周的第�?天（星期日）
	 * @return
	 */
	public static String getCurrentWeek() {
		Calendar preWeekSundayCal = Calendar.getInstance();
		preWeekSundayCal.set(Calendar.DAY_OF_WEEK,1);
		Date preWeekSunday = preWeekSundayCal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(preWeekSunday);
	}
	
	/**
	 * 设置为上周的星期�?
	 * @return
	 */
	public static String getFirstMonday() {
		Calendar preWeekMondayCal = Calendar.getInstance();
		preWeekMondayCal.set(Calendar.DAY_OF_WEEK,1);
		preWeekMondayCal.add(Calendar.DATE, -6);
		Date preWeekMonday = preWeekMondayCal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(preWeekMonday);
	}
	
	/**
	 * 设置为当前月的第�?�?
	 * @return
	 */
	public static String getCurrentUpMonthForYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,0);
		Date time = cal.getTime();
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy");
		SimpleDateFormat dateM = new SimpleDateFormat("MM");
		System.out.println(dateM.format(time));
		return dateFm.format(time);
	}
	
	
	
	
	public static void getS(){
		Calendar preWeekSundayCal = Calendar.getInstance();
		Calendar preWeekMondayCal = Calendar.getInstance();
		//设置时间成本周第�?�?(周日)
		preWeekSundayCal.set(Calendar.DAY_OF_WEEK,1);
		preWeekMondayCal.set(Calendar.DAY_OF_WEEK,1);
		//上周�?时间
		preWeekMondayCal.add(Calendar.DATE, -6);
		//上周日时�?
		preWeekSundayCal.add(Calendar.DATE, 0);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
		Date preWeekMonday = preWeekMondayCal.getTime();
		Date preWeekSunday = preWeekSundayCal.getTime();
		System.out.println(sdf.format(preWeekMonday));
		System.out.println( sdf.format(preWeekSunday));
	}
	
	
	

	// 发布时间<1502121600000 and 发布时间>1501689600000 and 项目分类='国土'
	public static void main(String[] args) throws ParseException {
//		System.out.println("上周�?"+getFirstMonday());
//		System.out.println("本周�?"+getCurrentWeek());
//
//		System.out.println(StringUtils.getTime(getFirstMonday()+";"+getCurrentWeek()));
		System.out.println(getCurrentUpMonthForYear());
		
		
		System.out.println(longToData("1517283097035"));
		
		System.out.println(longToData("1517740585444"));
		
		
		
		System.out.println(longToData("1517990405880"));
		
		Date date = new SimpleDateFormat(DataFormat).parse("2018-01-30");
		long unixTimestamp = date.getTime();
		
		Date date2 = new SimpleDateFormat(DataFormat).parse("2018-01-31");
		long  unixTimestamp2= date2.getTime();
		
		System.out.println(String.valueOf(unixTimestamp2-unixTimestamp));
		
//		System.out.println(getBefoerData("2017-09-1", 30));
//
//		System.out.println(getDate("2017-09-1")[0]);
//		System.out.println(dataToLong("2017-09-1"));// 1504368000000

		// System.out.println(dataToLong("2017-08-03"));// 1504368000000
		// System.out.println(dataToLong("2017-09-2"));// 1504368000000
		// System.out.println(dataToLong("2017-09-1"));// 1504368000000

		// System.out.println(getCurrentDate(DataFormat));
		// System.out.println(getCurrentDate(TimeFormat));

		// 86400000

		// System.out.println((1504281600000L - 1504195200000L) + "");
		// System.out.println(longToData("1505232000000"));//1505232000000

//		System.out.println(getCurrentDateAgo(-1));
//		System.out.println(getCurrentDateAgo(0));
//		System.out.println(getCurrentMonthAgo(-9));
//		System.out.println(getCurrentMonthFirst());
		
		
	}

}
