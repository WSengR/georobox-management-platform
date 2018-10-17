package com.geostar.georobox.management.common.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.module.apprunlog.service.AppRunService;
import com.geostar.georobox.management.module.apprunlog.service.impl.AppRunServiceImpl;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
//@WebAppConfiguration
public class RunConfigTest {

	@Test
	public void getRunChartData() {
		AppRunService appRunServiceImpl = new AppRunServiceImpl();
		List<DataInfoBean> runChartData = appRunServiceImpl.getRunChartData();
		System.out.println(runChartData.toString());
	}

}
