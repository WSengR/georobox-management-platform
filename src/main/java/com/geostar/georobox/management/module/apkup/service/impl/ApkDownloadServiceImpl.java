package com.geostar.georobox.management.module.apkup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.apkup.dao.ApkDownloadBeanMapper;
import com.geostar.georobox.management.module.apkup.model.ApkDownloadBean;
import com.geostar.georobox.management.module.apkup.service.ApkDownloadService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ApkDownloadServiceImpl implements ApkDownloadService {
	@Autowired
	private ApkDownloadBeanMapper apkDownloadBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	
	/**
	 * 上传APK
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveApkDownload(ApkDownloadBean apkDownloadBean) {
		String version = apkDownloadBean.getVersionId();
		//获取当前版本下载次数
		long num = apkDownloadBeanMapper.getNum(version);
		num++;
		//添加新的下载数量
		apkDownloadBeanMapper.addNum(num, version);
		int insert = apkDownloadBeanMapper.insertSelective(apkDownloadBean);
		return insert;
	}
	
	/**
	 * 获取APK下载列表
	 * param version根据版本号筛选列表
	 */
	@Override
	public List<ApkDownloadBean> getApkDownloadList(ApkDownloadBean apkDownloadBean, RbParm rbParm, String version) {
		sqlHelper.startPage(rbParm);
		Example example = new Example(ApkDownloadBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(version)) {
			criteria.andEqualTo("versionId", version);
		}
		example.and(criteria);
		example.orderBy("datetime").desc();
		List<ApkDownloadBean> apkDownloadBeans = apkDownloadBeanMapper.selectByExample(example);
		return apkDownloadBeans;
	}
	
	/**
	 * 获取APK下载数量，根据version进行筛选
	 */
	@Override
	public int getCount(ApkDownloadBean apkDownloadBean, RbParm rbParm, String version) {
		Example example = new Example(ApkDownloadBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(version)) {
			criteria.andEqualTo("versionId", version);
		}
		example.and(criteria);
		int selectCountByExample = apkDownloadBeanMapper.selectCountByExample(example);
		return selectCountByExample;
	}

}
