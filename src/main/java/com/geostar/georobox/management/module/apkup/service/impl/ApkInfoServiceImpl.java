package com.geostar.georobox.management.module.apkup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.apkup.dao.ApkInfoBeanMapper;
import com.geostar.georobox.management.module.apkup.model.ApkInfoBean;
import com.geostar.georobox.management.module.apkup.service.ApkInfoService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ApkInfoServiceImpl implements ApkInfoService {
	@Autowired
	private ApkInfoBeanMapper apkInfoBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private RbFileUtils rbfileUtils;
	
	/**
	 * 上传APK信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveApkInfo(ApkInfoBean apkInfoBean) {
		int insert = apkInfoBeanMapper.insertSelective(apkInfoBean);
		return insert;
	}
	
	/**
	 * 获取APK列表
	 */
	@Override
	public List<ApkInfoBean> getApkInfoList(ApkInfoBean apkInfoBean, RbParm rbParm) {
		sqlHelper.startPage(rbParm);
		Example example = new Example(ApkInfoBean.class);
		example.orderBy("datetime").desc();
		List<ApkInfoBean> apkInfoBeans = apkInfoBeanMapper.selectByExample(example);
		return apkInfoBeans;
	}
	
	/**
	 * 删除APK历史版本
	 */
	@Override
	public int deleteApkInfo(String id) {
		//根据id获取APK版本号
		String version = apkInfoBeanMapper.getVersion(id);
		//根据版本号删除下载记录
		apkInfoBeanMapper.delDownloadList(version);
		String apkUrl = getApkUrl(id);
		rbfileUtils.deleteServerFile(apkUrl);
		int insert = apkInfoBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 获取APK列表数量
	 */
	@Override
	public int getCount(ApkInfoBean apkInfoBean, RbParm rbParm) {
		Example example = new Example(ApkInfoBean.class);
		int selectCountByExample = apkInfoBeanMapper.selectCountByExample(example);
		return selectCountByExample;
	}
	
	/**
	 * 根据ID获取APK地址
	 */
	@Override
	public String getApkUrl(String id) {
		String apkUrl = apkInfoBeanMapper.getApkUrl(id);
		return apkUrl;
	}
}
