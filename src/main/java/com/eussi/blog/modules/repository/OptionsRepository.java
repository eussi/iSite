package com.eussi.blog.modules.repository;


import com.eussi.blog.base.modules.BaseRepository;
import com.eussi.blog.modules.po.Options;

/**
 * @author wangxm
 */
public interface OptionsRepository extends BaseRepository<Options> {
	Options findByKey(String key);
}
