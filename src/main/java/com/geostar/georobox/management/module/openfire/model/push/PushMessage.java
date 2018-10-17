package com.geostar.georobox.management.module.openfire.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 推送消息实体
 *
 * @author hanlyjiang on 2018/8/16-15:05.
 * @version 1.0
 */
public class PushMessage<T> {

	public enum PushType {
		/**
		 * 内部使用群组相关操作推送消息分类
		 */
		_GROUP,
		/**
		 * 通用类型PUSH
		 */
		COMMON,

	}

	/**
	 * 推送消息大分类
	 */
	@JsonProperty("type")
	private PushType type;

	/**
	 * 推送的数据
	 */
	@JsonProperty("data")
	private T data;

	public PushMessage(PushType type, T data) {
		this.type = type;
		this.data = data;
	}

	public PushType getType() {
		return type;
	}

	public T getData() {
		return data;
	}

}
