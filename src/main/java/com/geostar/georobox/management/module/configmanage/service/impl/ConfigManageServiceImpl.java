package com.geostar.georobox.management.module.configmanage.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.config.UploadFilePathConfig;
import com.geostar.georobox.management.common.utils.FileUtils;
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.configmanage.dao.ConfigManageBeanMapper;
import com.geostar.georobox.management.module.configmanage.model.ConfigManageBean;
import com.geostar.georobox.management.module.configmanage.service.ConfigManageService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ConfigManageServiceImpl implements ConfigManageService {
	
	@Autowired
	private ConfigManageBeanMapper configManageBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private RbFileUtils rbfileUtils;
	@Autowired
	private UploadFilePathConfig uploadFilePathConfig;
	/**
	 * 添加配置
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveConfigManage(ConfigManageBean configManageBean) {
		long version = sqlHelper.getSystemTep();
		configManageBean.setVersion(String.valueOf(version));
		int insert = configManageBeanMapper.insertSelective(configManageBean);
		return insert;
	}
	
	/**
	 * 根据分页获取配置文件列表
	 */
	@Override
	public List<ConfigManageBean> queryConfigManageList(ConfigManageBean configManageBean,RbParm rbParm) {
		// 开始分页
		sqlHelper.startPage(rbParm);
		Example example = new Example(ConfigManageBean.class);
		example.orderBy("datetime").desc();
		List<ConfigManageBean> configManageBeans = configManageBeanMapper.selectByExample(example);
		return configManageBeans;
	}
	
	/**
	 * 获取全部配置文件列表
	 */
	@Override
	public List<ConfigManageBean> queryConfigManageList() {
		Example example = new Example(ConfigManageBean.class);
		example.orderBy("datetime").desc();
		List<ConfigManageBean> configManageBeans = configManageBeanMapper.selectByExample(example);
		return configManageBeans;
	}
	
	/**
	 * 获取配置文件数量
	 */
	public int getCount() {
		Example example = new Example(ConfigManageBean.class);
		int selectCountByExample = configManageBeanMapper.selectCountByExample(example);
		return selectCountByExample;
	}
	
	/**
	 * 修改配置文件
	 */
	@Override
	public int changeConfigManage(ConfigManageBean configManageBean) {
		int insert = configManageBeanMapper.updateByPrimaryKeySelective(configManageBean);
		return insert;
	}
	
	/**
	 * 删除配置文件
	 */
	@Override
	public int deleteConfigManage(String id) {
		String fileName = getFileName(id);
		rbfileUtils.deleteServerFile(fileName);
		int insert = configManageBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 根据主键获取文件地址
	 */
	@Override
	public String getFileName(String id) {
		String fileName = configManageBeanMapper.getFileName(id);
		return fileName;
	}
	
	/**
	 * 配置文件转成XML格式
	 */
	@Override
	public String getConfigString(List<ConfigManageBean> configMangerBeans, String serverUrl) {
		StringBuffer stringBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		stringBuffer.append("<root>\n");
		for (ConfigManageBean configManageBean : configMangerBeans) {
			stringBuffer.append("\t<Resource>\n");
			stringBuffer.append("\t\t<Name>" + configManageBean.getName() + "</Name>\n");
			stringBuffer.append("\t\t<Type>" + configManageBean.getType() + "</Type>\n");
			stringBuffer.append("\t\t<URL>" + serverUrl + configManageBean.getUrl() + "</URL>\n");
			if (!StringUtils.isEmpty(configManageBean.getPackagename())) {
				stringBuffer.append("\t\t<PackageName>" + configManageBean.getPackagename() + "</PackageName>\n");
			}
			stringBuffer.append("\t\t<NativePath>" + configManageBean.getNativepath() + "</NativePath>\n");
			stringBuffer.append("\t\t<Version>" + configManageBean.getVersion() + "</Version>\n");
			stringBuffer.append("\t</Resource>\n");
		}
		stringBuffer.append("</root>\n");
		return stringBuffer.toString();
	}
	
	/**
	 * 保存配置到本地
	 * 
	 * @param serverUrl
	 * @param saveString
	 */
	public String CreateConfig(String saveString) {
		String configName = "mainList.xml";
		String ConfigUrl =  uploadFilePathConfig.getUploadFileServerPath("configResources", configName);
		String configPath = uploadFilePathConfig.getUploadFolder("configResources") +  configName;
		File configFile = FileUtils.myCreatFile(configPath);
		if (saveString != null) {
			try {
				FileOutputStream writerStream = new FileOutputStream(configFile);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
				writer.write(saveString);
				writer.close();
			} catch (Exception e) {
				System.err.println("写入失败");
				e.printStackTrace();
			}
		}
		return ConfigUrl;
	}
	
	
	
}
