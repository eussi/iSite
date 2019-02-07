package com.eussi.blog.modules.service.impl;

import com.eussi.blog.modules.po.Options;
import com.eussi.blog.modules.service.OptionsService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = true)
public class OptionsServiceImpl implements OptionsService {
    @Override
    public List<Options> findAll() {
        return null;
    }

    @Override
    public Map<String, Options> findAll2Map() {
        return null;
    }

    @Override
    public void update(List<Options> options) {

    }

    @Override
    public String findConfigValueByName(String key) {
        return null;
    }

    @Override
    public void initSettings(Resource resource) {

    }
}
