package com.geostar.georobox.management.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * 描述：上传文件虚拟 Spring Boot上传文件设置绝对路径和访问绝对路径下的静态资源
 * 
 * @author wangsr
 * @date 2018年9月21日
 */
@Configuration
public class UploadFilePathConfig implements WebMvcConfigurer {
	/**
	 * 静态资源对外暴露的访问路径
	 */
	@Value("${com.geostar.georobox.management.file.staticAccessPath}")
	private String staticAccessPath;

	/**
	 * 物理路径
	 */
	@Value("${com.geostar.georobox.management.file.uploadFolder}")
	private String uploadFolder;
	/**
	 * 添加路径映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
	}
	/**
	 * 添加参数转换映射 Date
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DateConverter());
	}
	
	/**
	 * 指向地址的正则表达式 带有特殊**号
	 * @return
	 */
	public String getStaticAccessPath() {
		return staticAccessPath;
	}

	public String getUploadFolder() {
		return uploadFolder;
	}
	
	/**
	 * 服务路径
	 * @return
	 */
	public String getCustomServerPath() {
		return staticAccessPath.replace("**", "");
	}

	public String getModulePath(String moduleName) {
		String modulePath = "";
		switch (moduleName) {
		case "logManage":
			modulePath = "logManage";
		case "errorLog":
			modulePath = "errorLog";
			break;
		case "userBack":
			modulePath = "userBack";
			break;
		case "apkUp":
			modulePath = "apkUp";
			break;
		case "documentCenter":
			modulePath = "documentCenter";
			break;
		case "configResources":
			modulePath = "configResources";
			break;
		case "plug":
			modulePath = "plug";
			break;
		case "score":
			modulePath = "score";
			break;
		case "friendCricle":
			modulePath = "friendCricle";
			break;
		default:
			modulePath = "default";
			break;
		}
		return modulePath;
	}

	/**
	 * 根据模块名返回物理地址
	 * 
	 * @param moduleName
	 * @return
	 */
	public String getUploadFolder(String moduleName) {
		return getUploadFolder() + getModulePath(moduleName) + "/";
	}

	/**
	 * 上传完成后的服务路径
	 * 
	 * @param moduleName
	 * @param fileName
	 * @return
	 */
	public String getUploadFileServerPath(String moduleName, String fileName) {
		return getCustomServerPath() + getModulePath(moduleName) + "/" + fileName;
	}

	/**
	 * 更具路径获取文件物理地址
	 * @param moduleName
	 * @param fileName
	 * @return
	 */
	public String getUploadFileAbsolutePath(String fileUrl) {
		String fileAbsolutePath = fileUrl.replace(getCustomServerPath(), "");
		return getUploadFolder()+fileAbsolutePath;
	}
}
