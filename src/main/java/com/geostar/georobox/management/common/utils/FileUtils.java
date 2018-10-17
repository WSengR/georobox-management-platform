package com.geostar.georobox.management.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class FileUtils {

	public static void main(String[] args) {
//		createFile("E:\\aaa\\test.txt");
		FileOutputStream writerStream;
		try {
			writerStream = new FileOutputStream("E:\\aaa\\test.txt");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
			writer.write("hahah a哈哈哈哈哈哈哈");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除服务器上的文件
	 * 
	 * @param serverPath
	 * @param fileUrls
	 */
	public static void deleteMoreFile(String serverPath, String fileUrls) {
		// getServletContext().getRealPath("./") + File.separator
		if (StringUtils.isEmpty(fileUrls)) {
			return;
		}
		String[] filephths = fileUrls.split(";");
		for (int i = 0; i < filephths.length; i++) {
			if (StringUtils.isEmpty(filephths[i]))
				continue;
			FileUtils.deleteFile(serverPath + filephths[i]);
		}
	}

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
			// System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
			// return false;
		}
		if (destFileName.endsWith(File.separator)) {
			System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				System.out.println("创建目标文件所在目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				System.out.println("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				System.out.println("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
			return false;
		}
	}

	/**
	 * 创建文件夹及目录
	 * 
	 * @param destFileName
	 * @return
	 */
	public static File myCreatFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			// 如果存在则替换
			file.delete();
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// 创建目标文件
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return file;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param filePath
	 * @return false 文件不存在或者删除失败  true 删除成功
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			return file.delete();
		} else {
			return false;
		}
	}
	
	/**
	 * 删除单个文件
	 * 
	 * @param filePath
	 * @return false 文件不存在或者删除失败  true 删除成功
	 */
	public static boolean deleteServerFile(String fileUrl) {
		File file = new File(fileUrl);
		if (file.exists() && file.isFile()) {
			return file.delete();
		} else {
			return false;
		}
	}
	

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param file 文件
	 * @param text 写入文件文本
	 */
	public static void inputStringForFile(File file, String text) {
		System.out.println(text);
		// 向文件写入内容(输出流)
		byte bt[] = new byte[1024];
		bt = text.getBytes();
		try {
			FileOutputStream in = new FileOutputStream(file);
			try {
				in.write(bt, 0, bt.length);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用OutputStreamWrite向文件写入内容
	 * 
	 * @param _destFile
	 * @throws IOException
	 */

	public static void writeByFileWrite(File _sDestFile, String _sContent) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(_sDestFile);
			fw.write(_sContent);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fw = null;
			}
		}

	}

	/**
	 * 读出文件
	 * 
	 * @param file
	 */
	public static void readFile(File file) {
		FileInputStream out = null;
		InputStreamReader isr = null;
		try {
			// 读取文件内容 (输入流)
			out = new FileInputStream(file);
			isr = new InputStreamReader(out);
			int ch = 0;
			while ((ch = isr.read()) != -1) {
				System.out.print((char) ch);
			}
			out.close();
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ==================================== 使用
	 * =============================================
	 */

	public static void SaveConfig(String serverPath) {
		// Map<String, String> arguments = new HashMap<String, String>();
		// arguments.put("permission_leader", "0");
		// arguments.put("permission_gridman", "0");
		// arguments.put("permission_subdistrict", "0");
		// GetConfigUrl(serverPath,"Androidconfig_permission_no", arguments);

		Map<String, String> arguments2 = new HashMap<String, String>();
		arguments2.put("permission_leader", "1");
		GetConfigUrl(serverPath, "Androidconfig_permission_leader", arguments2);

		Map<String, String> arguments3 = new HashMap<String, String>();
		arguments3.put("permission_gridman", "1");
		GetConfigUrl(serverPath, "Androidconfig_permission_gridman", arguments3);

		Map<String, String> arguments4 = new HashMap<String, String>();
		arguments4.put("permission_subdistrict", "1");
		GetConfigUrl(serverPath, "Androidconfig_permission_subdistrict", arguments4);

		Map<String, String> arguments5 = new HashMap<String, String>();
		arguments5.put("permission_leader", "1");
		arguments5.put("permission_gridman", "1");
		arguments5.put("permission_subdistrict", "1");
		GetConfigUrl(serverPath, "Androidconfig_permission_all", arguments5);

	}

	/**
	 * 生成配置文件并返回连接
	 * 
	 * @return
	 */
	public static String GetConfigUrl(String serverPath, Map<String, String> arguments) {

		/****** ☆ 坑啊 这里不能使 容易乱码 */
		// 写入配置到服务器
		String configName = "Androidconfig_" + System.currentTimeMillis() + ".xml";
		String ConfigUrl = "config/" + configName;
		String configPath = serverPath + File.separator + ConfigUrl;
		File configFile = FileUtils.myCreatFile(configPath);

		FileUtils.writeByFileWrite(configFile, getConfigString(arguments));
		return ConfigUrl;
	}

	/**
	 * 生成配置文件并返回连接
	 * 
	 * @return
	 */
	public static String GetConfigUrl(String serverPath, String configName, Map<String, String> arguments) {

		/****** ☆ 坑啊 这里不能使 容易乱码 */
		// 写入配置到服务器
		configName = configName + ".json";
		String ConfigUrl = "config/" + configName;
		String configPath = serverPath + File.separator + ConfigUrl;
		File configFile = FileUtils.myCreatFile(configPath);

		FileUtils.writeByFileWrite(configFile, getConfigString(arguments));
		return ConfigUrl;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(long fileS) {
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

	public static final int SIZETYPE_B = 1;// 获取文件大小单位为B的double值
	public static final int SIZETYPE_KB = 2;// 获取文件大小单位为KB的double值
	public static final int SIZETYPE_MB = 3;// 获取文件大小单位为MB的double值
	public static final int SIZETYPE_GB = 4;// 获取文件大小单位为GB的double值

	/**
	 * 转换文件大小,指定转换的类型
	 * 
	 * @param fileS
	 * @param sizeType
	 * @return
	 */
	public static double FormetFileSize(long fileS, int sizeType) {
		DecimalFormat df = new DecimalFormat("#.00");
		double fileSizeLong = 0;
		switch (sizeType) {
		case SIZETYPE_B:
			fileSizeLong = Double.valueOf(df.format((double) fileS));
			break;
		case SIZETYPE_KB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
			break;
		case SIZETYPE_MB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
			break;
		case SIZETYPE_GB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
			break;
		default:
			break;
		}
		return fileSizeLong;
	}

	public static String getConfigString(Map<String, String> arguments) {
		// JDBCHelper jdbcHelper = new JDBCHelper();
		// ArrayList<BackFileBean> upFileBeans =
		// jdbcHelper.quaryUrlByPermission(arguments);
		// Gson gson = new Gson();
		// String beanString= gson.toJson(upFileBeans);
		// // System.out.println(beanString);
		// jdbcHelper.close();
		return "";
	}

}
