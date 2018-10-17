package com.geostar.georobox.management.common.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.alibaba.druid.util.StringUtils;

/**
 * 
 * 	描述：自定义日期Date参数转化
 * 
 * @author  wangsr  
 * @date    2018年9月26日
 */
public class DateConverter implements Converter<String,Date>{
	private static String fomat1 =  "yyyy-MM-dd HH:mm:ss";
	private static String fomat2 =  "yyyy-MM-dd";

	@Override
	public Date convert(String source) {
		if(StringUtils.isEmpty(source)) {
			return null;
		}
		try {
			return new SimpleDateFormat(fomat1).parse(source);
		} catch (ParseException e) {
			try {
				return new SimpleDateFormat(fomat2).parse(source);
			} catch (ParseException e1) {
				return null;
			}
		}
	}

}
