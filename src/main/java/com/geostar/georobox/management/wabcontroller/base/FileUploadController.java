package com.geostar.georobox.management.wabcontroller.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.geostar.georobox.management.common.bean.ImageBean;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.common.config.UploadFilePathConfig;
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.common.utils.ReadApkUtil;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.plugmanage.dao.provider.PlugExample;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 公共文件上传接口 描述：
 * 
 * @author wangsr
 * @date 2018年9月13日
 */
@Controller
@RequestMapping("/")
public class FileUploadController {

	@Autowired
	private UploadFilePathConfig uploadFilePathConfig;
	@Autowired
	private RbFileUtils rbfileUtils;
	@Autowired
	private SQLHelper sqlHelper;

	protected static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping("/templates")
	String test(HttpServletRequest request) {
		return "index";
	}

	@RequestMapping("/uploadimg")
	String uploadimg(HttpServletRequest request) {
		return "uploadimg.html";
	}

	@RequestMapping("/static")
	public String navigatorToStatic() {
		return "redirect:/fragments.html";
	}
	/**
	 * 配置开始启动
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/")
	public String login(HttpServletRequest request) {
		return "redirect:/LogSystem/page/login/login.html";
	}

//	@RequestMapping(value = "/")
//	public ModelAndView uploadFile(HttpServletRequest request) {
//		String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
//		logger.info(uploadDir);
//		logger.info(System.nanoTime() + "");
//		return new ModelAndView("/LogSystem/index.html");
//	}

	@Autowired
	private RbFileUtils rbFileUtils;

	/**
	 * 单个文件上传
	 */
	@RequestMapping(value = "/getVersionCode", method = RequestMethod.POST)
	@ResponseBody
	public String getVersionCode(String plugUrl) {
		String uploadFileAbsolutePath = rbFileUtils.getUploadFileAbsolutePath(plugUrl);
		String versionCode = ReadApkUtil.getVersionCode(System.currentTimeMillis() + "", uploadFileAbsolutePath);
		return versionCode;
	}

	/**
	 * 单个文件上传
	 * 
	 * String uploadDir = request.getServletContext().getRealPath("/");//该路径不是固定的
	 * logger.info(uploadDir);
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public RbResultBean uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String saveToServer = saveToServer(file, "");
			return RbResultBean.getSuccess(saveToServer);
		} catch (Exception e) {
			e.printStackTrace();
			return RbResultBean.getError();
		}
	}

	/**
	 * 自定义文件夹上传 单个文件上传
	 * 
	 * String uploadDir = request.getServletContext().getRealPath("/");//该路径不是固定的
	 * logger.info(uploadDir);
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/customUploadFile", method = RequestMethod.POST)
	@ResponseBody
	public RbResultBean customUploadFile(@RequestParam("file") MultipartFile file, String folder) {
		try {
			String saveToServer = saveToServer(file, folder);
			return RbResultBean.getSuccess(saveToServer);
		} catch (Exception e) {
			e.printStackTrace();
			return RbResultBean.getError();
		}
	}

	/**
	 * 自定义文件夹上传 单个文件上传
	 * 
	 * String uploadDir = request.getServletContext().getRealPath("/");//该路径不是固定的
	 * logger.info(uploadDir);
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/customUploadImage", method = RequestMethod.POST)
	@ResponseBody
	public RbResultBean customUploadImage(@RequestParam("file") MultipartFile file, String folder) {
		try {
			ImageBean saveToServer = saveImgToServer(file, folder);
			if (saveToServer == null) {
				return RbResultBean.getError("请上传图片类型的文件(jpg)|(png)|(gif)|(JPEG)|(BMP)");
			}
			return RbResultBean.getSuccess(saveToServer);
		} catch (Exception e) {
			e.printStackTrace();
			return RbResultBean.getError();
		}
	}

	/**
	 * 多文件上传 说明：文件必须使用同一的name
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	@ResponseBody
	public RbResultBean uploadFiles(MultipartHttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		try {
			ArrayList<String> fileUrls = new ArrayList<>();
			for (int i = 0; i < files.size(); i++) {
				fileUrls.add(saveToServer(files.get(i), "test"));
			}
			return RbResultBean.getSuccess(fileUrls);
		} catch (Exception e) {
			e.printStackTrace();
			return RbResultBean.getError("文件上传失败");
		}
	}

	/**
	 * 从电脑上移除
	 * 
	 * @param fileUrl
	 * @return
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public RbResultBean deleteFileFromServer(String fileUrl) {
		return RbResultBean.getSuccess(rbfileUtils.deleteServerFile(fileUrl));

	}

	/**
	 * 伪删除（修改名字为DELETE_TIME_...）以待后面删除
	 * 
	 * @param fileUrl
	 * @return
	 */
	@RequestMapping("/deleteFileToRecycle")
	@ResponseBody
	public RbResultBean deleteFileFromServerToRecycle(String fileUrl) {
		return RbResultBean.getSuccess(rbfileUtils.deleteServerFileToRecycle(fileUrl));

	}

	/**
	 * 文件上传封装类(如果文件相同就加上时间戳区分)
	 * 
	 * @param file       MultipartFile 文件类型
	 * @param moduleName 分模块存储
	 * @throws IOException
	 */
	private synchronized String saveToServer(MultipartFile file, String moduleName)
			throws IllegalStateException, IOException {
		String uploadDir = uploadFilePathConfig.getUploadFolder(moduleName);
		logger.info(uploadDir);
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String fileName = file.getOriginalFilename();
		File serverFile = new File(uploadDir + fileName);
		if (serverFile.exists()) {
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			// 如果文件相同就加上时间戳区分
			String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + sqlHelper.getSystemTep();
			fileName = newFileName + suffixName;
			serverFile = new File(uploadDir + fileName);
		}
		file.transferTo(serverFile);
		return uploadFilePathConfig.getUploadFileServerPath(moduleName, fileName);
	}

	/**
	 * 文件上传封装类(如果文件相同就加上时间戳区分)
	 * 
	 * @param file       MultipartFile 文件类型
	 * @param moduleName 分模块存储
	 * @throws IOException
	 */
	private synchronized ImageBean saveImgToServer(MultipartFile file, String moduleName)
			throws IllegalStateException, IOException {
		String uploadDir = uploadFilePathConfig.getUploadFolder(moduleName);
		logger.info(uploadDir);
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String fileName = file.getOriginalFilename();
		File serverFile = new File(uploadDir + fileName);
		if (!rbfileUtils.isImageFile(serverFile)) {
			return null;
		}
		if (serverFile.exists()) {
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			// 如果文件相同就加上时间戳区分
			String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + sqlHelper.getSystemTep();
			fileName = newFileName + suffixName;
			serverFile = new File(uploadDir + fileName);
		}
		file.transferTo(serverFile);

		Thumbnails.of(serverFile.getAbsolutePath()).scale(1f).outputQuality(0.7f)
				.toFile(uploadDir + "temp_0.7f_" + fileName);

		ImageBean imageBean = new ImageBean();
		imageBean.setImageUrl(uploadFilePathConfig.getUploadFileServerPath(moduleName, fileName));
		imageBean.setImageTempUrl(uploadFilePathConfig.getUploadFileServerPath(moduleName, "temp_0.7f_" + fileName));
		return imageBean;
	}

}
