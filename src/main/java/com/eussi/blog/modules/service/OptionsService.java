package com.eussi.blog.modules.service;

import com.eussi.blog.modules.po.Options;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;


/**
 * @author wangxm
 *
 */
public interface OptionsService {
	/**
	 * 查询所有配置
	 * @return list
	 */
	List<Options> findAll();

	/**
	 * 查询所有配置
	 * @return map
	 */
	Map<String, Options> findAll2Map();

	/**
	 * 添加或修改配置
	 * - 修改时根据key判断唯一性
	 * @param options
	 */
	void update(List<Options> options);

	/**
	 * 根据key查找相应的值
	 *
	 * @param key
	 * @return
	 */
	String findConfigValueByName(String key);

	void initSettings(Resource resource);
}
