package com.geostar.georobox.management.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class MyApplicationRunner implements ApplicationRunner {
	
	private static final Logger log = LoggerFactory.getLogger(MyApplicationRunner.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("服务启动成功 .....");
	}

}
