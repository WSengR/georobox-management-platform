package com.geostar.georobox.management.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * 描述：使用Rest Http请求
 * 
 * @author wangsr
 * @date 2018年9月27日
 */
@Configuration
public class RestHttpUtils {

	@Autowired
	protected RestTemplate restTemplate;

	/**
	  * 向目的URL发送post请求
	 * 
	 * @param url    目的url
	 * @param params 发送的参数
	 * @return ResultVO
	 */
	public String sendPostRequest(String url, MultiValueMap<String, String> params) {
		HttpHeaders headers = new HttpHeaders();
		HttpMethod method = HttpMethod.POST;
		// 以表单的方式提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// 将请求头部和参数合成一个请求
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
		// 执行HTTP请求，将返回的结构使用ResultVO类格式化
		ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);
		return response.getBody();
	}

	/**
	 * 向目的URL发送Get请求
	 * 
	 * @param url    目的url
	 * @param params 发送的参数
	 * @return ResultVO
	 */
	public String sendGetRequest(String url, MultiValueMap<String, String> params) {
		
		HttpHeaders headers = new HttpHeaders();
		HttpMethod method = HttpMethod.GET;
		// 以表单的方式提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// 将请求头部和参数合成一个请求
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
		// 执行HTTP请求
		ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);
		return response.getBody();
	}

}
