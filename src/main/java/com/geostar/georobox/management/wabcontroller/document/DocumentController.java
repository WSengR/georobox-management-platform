package com.geostar.georobox.management.wabcontroller.document;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.document.model.DocumentBean;
import com.geostar.georobox.management.module.document.service.impl.DocumentServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("documentrun")
public class DocumentController {
	protected static Logger logger = LoggerFactory.getLogger(DocumentController.class);
	@Autowired
	private DocumentServiceImpl documentService;
	

	/**
	 * 获取文档列表
	 * @param documentBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetDocumentListServlet")
	public ListLimitBean GetDocumentListServlet(DocumentBean documentBean,RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<DocumentBean> queryDocumentList = documentService.queryDocument(documentBean, rbParm);
		listLimitBean.setData(queryDocumentList);
		int count = documentService.getCount(documentBean, rbParm);
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	/**
	 * 删除文档
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteDocumentServlet")
	public RbResultBean DeleteDocumentServlet( String id) throws Exception {
		int num = documentService.deleteDocument(id);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/DocumentUpServlet", method = RequestMethod.POST)
	@ResponseBody
	public RbResultBean customUploadFile( @RequestParam("file") MultipartFile file) {
		try {
			String saveToServer = documentService.saveToServer(file, "documentCenter");
			if(saveToServer == "1") {
				return RbResultBean.getError();
			}
			return RbResultBean.getSuccess(saveToServer);
		} catch (Exception e) {
			e.printStackTrace();
			return RbResultBean.getError();
		}
	}
			
}
