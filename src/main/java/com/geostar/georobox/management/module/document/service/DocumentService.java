package com.geostar.georobox.management.module.document.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.document.model.DocumentBean;

public interface DocumentService {

	public List<DocumentBean> queryDocument(DocumentBean documentBean,RbParm rbParm);
	
	public int getCount(DocumentBean documentBean, RbParm rbParm);
	
	public int deleteDocument(String id);
	
	public String getFileName(String id);

	public int saveDocument(DocumentBean documentBean);

	public String FormetFileSize(long fileS);

	public String saveToServer(MultipartFile file, String moduleName) throws IllegalStateException, IOException;

}
