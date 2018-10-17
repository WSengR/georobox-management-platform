package com.geostar.georobox.management.common.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {
	@Test
	public void testLogger() {
	    Logger logger = LoggerFactory.getLogger(MainTest.class);
	    logger.info("hhhhhh");
	}
	
}
