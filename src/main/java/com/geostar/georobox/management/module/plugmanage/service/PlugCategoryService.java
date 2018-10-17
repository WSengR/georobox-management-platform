package com.geostar.georobox.management.module.plugmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndCategory;
import com.geostar.georobox.management.module.plugmanage.model.PlugCategoryBean;
@Service
public interface PlugCategoryService {
	int save(PlugCategoryBean plugCategoryBean);

	int delete(String categoryName);
	
	int update(PlugCategoryBean plugCategoryBean);
	
	List<PlugCategoryBean> selectCategoryList(RbParm rbParm);
	
	PlugCategoryBean selectCategoryById(String categoryId);

	PlugCategoryBean selectCategoryByName(String categoryId,String categoryName);
	
	int savePAC(PlugAndCategory plugAndCategory);
	
	int selectPACCount(PlugAndCategory plugAndCategory);

	int deletePACByPlugId(String plugId);
	
	int deletePACByCategoryId(String categoryId);
	
	void savePACByCategorys(String plugId,String categorys);
	
	List<String> selectPACByPlugId(String plugId);
	
	int selectCategoryCount(RbParm rbParm);
	
}
