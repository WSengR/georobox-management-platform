package com.geostar.georobox.management.wabcontroller.score;

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
import com.geostar.georobox.management.module.score.model.ScoreBean;
import com.geostar.georobox.management.module.score.service.impl.ScoreServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("scorerun")
public class ScoreController {
	protected static Logger logger = LoggerFactory.getLogger(ScoreController.class);
	@Autowired
	private ScoreServiceImpl scoreService;
	@Autowired
	private RbFileUtils rbfileUtils;
	
	/**
	 * 获取评分列表
	 * @param scoreBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetScoreListServlet")
	public ListLimitBean GetScoreListServlet(ScoreBean scoreBean, RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<ScoreBean> queryScoreList = scoreService.queryScoreList(scoreBean, rbParm);
		listLimitBean.setData(queryScoreList);
		int count = scoreService.getCount(scoreBean, rbParm);
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	/**
	 * 上传评分
	 * @param scoreBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/SendScoreServer")
	public RbResultBean SendScoreServer(ScoreBean scoreBean) throws Exception {
		scoreBean.setDatetime(new Date());
		int num = scoreService.saveScore(scoreBean);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 删除评分
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteScoreServer")
	public RbResultBean DeleteScoreServer(String id) throws Exception {
		String fileName = scoreService.getImageUrl(id);
		String[] fileNameStrArray = fileName.split(";");
		for (int i = 0; i < fileNameStrArray.length; i++) {
        	if(StringUtils.isEmpty(fileNameStrArray[i])) {
        		continue;
        	}
        	rbfileUtils.deleteServerFile(fileNameStrArray[i]);
        }
		int num = scoreService.deleteScore(id);
		return RbResultBean.getSuccess(num);
	}
	
}
