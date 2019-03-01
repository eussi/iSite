package com.eussi.blog.modules.dao;

import com.eussi.blog.base.modules.BaseMapper;
import com.eussi.blog.modules.po.Options;

public interface OptionsMapper extends BaseMapper<Options>{

    Options findByKey(String key);

}