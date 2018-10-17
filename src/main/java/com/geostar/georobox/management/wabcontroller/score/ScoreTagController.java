package com.geostar.georobox.management.wabcontroller.score;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.score.model.ScoreTagBean;
import com.geostar.georobox.management.module.score.service.impl.ScoreTagServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("scorerun")
public class ScoreTagController {
	protected static Logger logger = LoggerFactory.getLogger(ScoreTagController.class);
	@Autowired
	private ScoreTagServiceImpl scoreTagService;
	
	@RequestMapping("/GetTagListServlet")
	public ListLimitBean GetTagListServlet() throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<ScoreTagBean> queryScoreTagList = scoreTagService.queryScoreTagList();
		listLimitBean.setData(queryScoreTagList);
		int count = scoreTagService.getCount();
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	@RequestMapping("/AddTagServlet")
	public RbResultBean AddTagServlet(ScoreTagBean scoreTagBean) throws Exception {
		int num = scoreTagService.saveScoreTag(scoreTagBean);
		return RbResultBean.getSuccess(num);
	}
	
	@RequestMapping("/DeleteTagServlet")
	public RbResultBean DeleteTagServlet(String tag) throws Exception {
		int num = scoreTagService.deleteScoreTag(tag);
		return RbResultBean.getSuccess(num);
	}
	
}
