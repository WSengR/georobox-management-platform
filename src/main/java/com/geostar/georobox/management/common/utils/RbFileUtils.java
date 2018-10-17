package com.geostar.georobox.management.common.utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.geostar.georobox.management.common.config.UploadFilePathConfig;

@Configuration
public class RbFileUtils {
	protected static Logger logger = LoggerFactory.getLogger(RbFileUtils.class);
	@Autowired
	private UploadFilePathConfig uploadFilePathConfig;

	/** ================================服务方案=============================== */
	/**
	 * 删除服务器上的文件
	 * 
	 * @param fileUrl 服务地址 api/file....
	 * @return
	 */
	public boolean deleteServerFile(String fileUrl) {
		String uploadFileAbsolutePath = uploadFilePathConfig.getUploadFileAbsolutePath(fileUrl);
		logger.info(uploadFileAbsolutePath);
		return deleteFile(uploadFileAbsolutePath);
	}

	/**
	 * 获取绝对地址
	 * 
	 * @param fileUrl 服务地址 api/file....
	 * @return 删除后的名字
	 */
	public String getUploadFileAbsolutePath(String fileUrl) {
		String uploadFileAbsolutePath = uploadFilePathConfig.getUploadFileAbsolutePath(fileUrl);
		return uploadFileAbsolutePath;
	}

	/**
	 * 删除服务器上的文件
	 * 
	 * @param fileUrl 服务地址 api/file....
	 * @return 删除后的名字
	 */
	public String deleteServerFileToRecycle(String fileUrl) {
		String uploadFileAbsolutePath = uploadFilePathConfig.getUploadFileAbsolutePath(fileUrl);
		logger.info(uploadFileAbsolutePath);
		String fixFileName = fixFileName(uploadFileAbsolutePath);
		return fixFileName;
	}

	/** ================================基础方法=============================== */
	/**
	 * 创建文件夹及目录
	 * 
	 * @param destFileName
	 * @return
	 */
	public boolean createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			// 如果存在则替换
			file.delete();
		}
		if (destFileName.endsWith(File.separator)) {
			logger.info("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			logger.info("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				logger.info("创建目标文件所在目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				logger.info("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				logger.info("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("创建单个文件" + destFileName + "失败！" + e.getMessage());
			return false;
		}
	}

	/**
	 * 根据路径删除指定的目录，无论存在与否
	 * 
	 * @param sPath 要删除的目录path
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除目录以及目录下的文件
	 * 
	 * @param sPath 被删除目录的路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath 被删除文件path
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 通过文件路径直接修改文件名
	 * 
	 * @param filePath    需要修改的文件的完整路径
	 * @param newFileName 需要修改的文件的名称(包含后缀)
	 * @return
	 */
	public String fixFileName(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) { // 判断原文件是否存在
			return null;
		}
		String fileName = file.getName();
		return fixFileName(filePath, "DELETE_" + System.currentTimeMillis() + "_" + fileName);
	}

	public String fixFileName(String filePath, String newFileName) {
		File file = new File(filePath);
		if (!file.exists()) { // 判断原文件是否存在
			return null;
		}
		newFileName = newFileName.trim();
		if ("".equals(newFileName) || newFileName == null) // 文件名不能为空
			return null;
		String newFilePath = null;
		newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName;
		File nf = new File(newFilePath);
		if (!file.exists()) { // 判断需要修改为的文件是否存在（防止文件名冲突）
			return null;
		}
		try {
			file.renameTo(nf); // 修改文件名
		} catch (Exception err) {
			err.printStackTrace();
			return null;
		}
		return newFilePath;
	}

	/**
	 * 判断文件是否为图片
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isImageFile(File file) {
		String fileName = file.getName();
		String suffix = "";
		// 获取文件名的后缀，可以将后缀定义为类变量，共后面的函数使用
		suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		// 声明图片后缀名数组
		if (!suffix.matches("^[(jpg)|(png)|(gif)|(JPEG)|(BMP)]+$")) {
			System.out.println("请输入png,jpg,gif格式的图片");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		RbFileUtils rbFileUtils = new RbFileUtils();
		System.out.println(rbFileUtils.fixFileName("C:\\geo_robox_uploadfiles/plug/福田分拨.txt"));
	}

}
