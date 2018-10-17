package com.geostar.georobox.management.module.document.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.config.UploadFilePathConfig;
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.document.dao.DocumentBeanMapper;
import com.geostar.georobox.management.module.document.dao.provider.DocumentExample;
import com.geostar.georobox.management.module.document.model.DocumentBean;
import com.geostar.georobox.management.module.document.service.DocumentService;

import tk.mybatis.mapper.entity.Example;

@Service
public class DocumentServiceImpl implements DocumentService {
	protected static Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);
	@Autowired
	private DocumentBeanMapper documentBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private DocumentExample documentExample;
	@Autowired
	private RbFileUtils rbfileUtils;
	@Autowired
	private UploadFilePathConfig uploadFilePathConfig;
	
	/**
	 * 添加文档
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveDocument(DocumentBean documentBean) {
		int insert = documentBeanMapper.insertSelective(documentBean);
		return insert;
	}
	
	/**
	 * 获取文档列表
	 */
	@Override
	public List<DocumentBean> queryDocument(DocumentBean documentBean,RbParm rbParm) {
		// 开始分页
		sqlHelper.startPage(rbParm);
		Example documentFilter = documentExample.getDocumentFilter(documentBean, rbParm);
		documentFilter.orderBy("datetime").desc();
		List<DocumentBean> documentBeans = documentBeanMapper.selectByExample(documentFilter);
		return documentBeans;
	}
	
	/**
	 * 获取文档数量
	 */
	@Override
	public int getCount(DocumentBean documentBean, RbParm rbParm) {
		Example documentFilter = documentExample.getDocumentFilter(documentBean, rbParm);
		int selectCountByExample = documentBeanMapper.selectCountByExample(documentFilter);
		return selectCountByExample;
	}
	
	/**
	 * 删除文档
	 */
	@Override
	public int deleteDocument(String id) {
		String fileName = getFileName(id);
		rbfileUtils.deleteServerFile(fileName);
		int insert = documentBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 根据主键获取文档地址
	 */
	@Override
	public String getFileName(String id) {
		String fileName = documentBeanMapper.getFileName(id);
		return fileName;
	}
	
	/**
	 * 文档中心文件上传方法
	 */
	@Override
	public synchronized String saveToServer(MultipartFile file, String moduleName)
			throws IllegalStateException, IOException {
		DocumentBean documentBean = new DocumentBean();
		String uploadDir = uploadFilePathConfig.getUploadFolder(moduleName);
		logger.info(uploadDir);
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String fileName = file.getOriginalFilename();
		File serverFile = new File(uploadDir + fileName);
		if (serverFile.exists()) {
			return "1";
		}
		file.transferTo(serverFile);
		long fileSize = serverFile.length();
		String filePath = uploadFilePathConfig.getUploadFileServerPath(moduleName, fileName);
		documentBean.setFileName(fileName);
		documentBean.setFilePath(filePath);
		documentBean.setDatetime(new Date());
		String fileSizeString = FormetFileSize(fileSize);
		documentBean.setFileSize(fileSizeString);
		saveDocument(documentBean);
		return filePath;
	}
	
	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	@Override
	public String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		String wrongSize = "0B";
		if (fileS == 0) {
			return wrongSize;
		}
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "GB";
		}
		return fileSizeString;
	}

}
