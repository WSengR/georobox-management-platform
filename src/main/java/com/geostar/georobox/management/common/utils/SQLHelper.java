package com.geostar.georobox.management.common.utils;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.geostar.georobox.management.common.bean.RbParm;
import com.github.pagehelper.PageHelper;

/**
 * 
 * 描述：SQL工具类 【分页，模糊查询....】
 * 
 * @author wangsr
 * @date 2018年9月11日
 */
//@ConfigurationProperties(prefix = "com.geostar.georobox.management")
@PropertySource(value = "classpath:application.properties")
@Configuration
public class SQLHelper {

	private static final Logger logger = LoggerFactory.getLogger(SQLHelper.class);

	/**
	 * 开始一个分页
	 * 
	 * @param rbParm
	 */
	public void startPage(RbParm rbParm) {
		if (rbParm != null && rbParm.page != null && rbParm.limit != null) {
			PageHelper.startPage(rbParm.page, rbParm.limit);
		}
	}

	public String getUUID() {
//		UUID.randomUUID().toString().replace("-", "").toLowerCase()
		return UUID.randomUUID().toString().replace("-", "");
	}

	public synchronized long getSystemTep() {
		return System.currentTimeMillis();
	}

	
}
