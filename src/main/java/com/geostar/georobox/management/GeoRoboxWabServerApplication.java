package com.geostar.georobox.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.geostar.georobox.management.common.bean.DBConnectionInfo;

import tk.mybatis.spring.annotation.MapperScan;

//开启定时任务
//@EnableScheduling
//开启异步调用方法
//@EnableAsync
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.geostar.georobox.management.module")
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages = { "com.geostar.georobox" })
//入口注解
@SpringBootApplication
public class GeoRoboxWabServerApplication {

	private static final Logger log = LoggerFactory.getLogger(GeoRoboxWabServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GeoRoboxWabServerApplication.class, args);

	}
}
