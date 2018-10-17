package com.geostar.georobox.management.common.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.taskdefs.SQLExec.DelimiterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.maintv.model.NavConfigBean;
import com.geostar.georobox.management.module.maintv.service.impl.NavConfigServiceImpl;

public class DBConnectionInfo {

	private static final Logger log = LoggerFactory.getLogger(DBConnectionInfo.class);

	private Properties properties;
	private String url;
	private String username;
	private String password;
	private String driver;
	

	private static DBConnectionInfo dbConnectionInfo = new DBConnectionInfo();

	public static DBConnectionInfo getInstence() {
		return dbConnectionInfo;
	}

	public DBConnectionInfo() {
		this.properties = new Properties();
		InputStream inStream = SQLHelper.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			this.properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.url = properties.getProperty("spring.datasource.url");
		this.username = properties.getProperty("spring.datasource.username");

		this.password = properties.getProperty("spring.datasource.password");
		this.driver = properties.getProperty("spring.datasource.driver-class-name");
	}

	private boolean excute(String sqlPath) {
		log.info("初始化数据库脚本....." + sqlPath);
		if (StringUtils.isEmpty(sqlPath)) {
			System.out.println("excute exception sqlpath:" + sqlPath);
			return false;
		}

		File file = new File(sqlPath);
		if (!file.exists()) {
			System.out.println("文件不存在");
			return false;
		}

		final class SqlExecuter extends SQLExec {
			public SqlExecuter() {
				Project project = new Project();
				project.init();
				setProject(project);
				setTaskType("sql");
				setTaskName("sql");
			}
		}

		SqlExecuter sqlExec = new SqlExecuter();
		try {

			sqlExec.setDriver(driver);
			sqlExec.setUrl(url);
			sqlExec.setUserid(username);
			sqlExec.setPassword(password);
			sqlExec.setSrc(file);
			sqlExec.setEncoding("utf-8");
//			sqlExec.addText(sqlPath);
			DelimiterType dt = new DelimiterType();
			dt.setValue("row");
			sqlExec.setDelimiterType(dt);
			sqlExec.setDelimiter("/");
			sqlExec.setKeepformat(true);
//			sqlExec.setPrint(true);
//			sqlExec.setProject(new Project());
			sqlExec.execute();
		} catch (BuildException e) {
			System.out.println("excute exception DbConnectionInfo driver:" + driver);
			System.out.println("excute exception DbConnectionInfo url:" + url);
			System.out.println("excute exception DbConnectionInfo user:" + username);
			System.out.println("excute exception DbConnectionInfo pwd:" + password);
			System.out.println("excute exception sqlpath:" + sqlPath);
			e.printStackTrace();
			return false;
		}
		log.info("脚本执行完成...." + sqlPath);
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DBConnectionInfo [");
		builder.append("url=");
		builder.append(url);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", driver=");
		builder.append(driver);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * 创建宙斯默认表
	 * 
	 * @return
	 */
	public boolean createDefultTable() {
		return excute("src/main/java/com/geostar/georobox/management/common/config/sql/oracle/rb_create_table.sql");
	}

	/**
	 * 创建宙斯默认表
	 */
	public boolean insertDefultData() {
		return excute("src/main/java/com/geostar/georobox/management/common/config/sql/oracle/rb_insert_def.sql");
	}

	/**
	 * 初始化数据库
	 */
	public boolean initDB() {
//		NavConfigServiceImpl navConfigServiceImpl = new NavConfigServiceImpl();
//		List<NavConfigBean> queryNavConfigList = navConfigServiceImpl.queryNavConfigList(new NavConfigBean());
//		log.info("====================" + queryNavConfigList.toString());
		boolean createDefultTable = createDefultTable();
		log.info("创建默认数据库 : " + createDefultTable);
		boolean insertDefultData = insertDefultData();
		log.info("插入默认数据 : " + insertDefultData);
		return insertDefultData;
	}

}
