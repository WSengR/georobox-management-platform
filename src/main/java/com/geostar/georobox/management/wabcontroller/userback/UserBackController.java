package com.geostar.georobox.management.wabcontroller.userback;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.module.userback.model.UserBackBean;
import com.geostar.georobox.management.module.userback.service.impl.UserBackServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("userbackrun")
public class UserBackController {
	protected static Logger logger = LoggerFactory.getLogger(UserBackController.class);
	@Autowired
	private UserBackServiceImpl userBackService;
	@Autowired
	private RbFileUtils rbfileUtils;
	
	/**
	 * 获取反馈列表
	 * @param userBackBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetUserBackListServlet")
	public ListLimitBean GetUserBackListServlet(UserBackBean userBackBean,RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<UserBackBean> queryServerUrlList = userBackService.queryUserBackList(userBackBean, rbParm);
		listLimitBean.setData(queryServerUrlList);
		int count = userBackService.getCount(userBackBean, rbParm);
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	/**
	 * 上传反馈信息
	 * @param userBackBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/PostUserBackInfoServlet")
	public RbResultBean PostUserBackInfoServlet(UserBackBean userBackBean) throws Exception {
		userBackBean.setDatetime(new Date());
		int num = userBackService.saveUserBack(userBackBean);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 删除反馈信息（包括上传图片）
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteUserBackServlet")
	public RbResultBean DeleteUserBackServlet(String id) throws Exception {
		String fileName = userBackService.getFileName(id);
		String[] fileNameStrArray = fileName.split(";");
		for (int i = 0; i < fileNameStrArray.length; i++) {
        	if(StringUtils.isEmpty(fileNameStrArray[i])) {
        		continue;
        	}
        	rbfileUtils.deleteServerFile(fileNameStrArray[i]);
        }
		int num = userBackService.deleteUserBack(id);
		return RbResultBean.getSuccess(num);
	}
	
}
