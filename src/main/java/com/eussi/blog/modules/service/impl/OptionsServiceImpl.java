package com.eussi.blog.modules.service.impl;

import com.eussi.blog.modules.dao.OptionsMapper;
import com.eussi.blog.modules.po.Options;
import com.eussi.blog.modules.service.OptionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxueming on 2019/2/7.
 */
@Service
@Transactional(readOnly = false)
public class OptionsServiceImpl implements OptionsService {
    @Autowired
    private OptionsMapper optionsMapper;

    @Override
    public List<Options> findAll() {
        return optionsMapper.findAll();
    }

    @Override
    public Map<String, Options> findAll2Map() {
        List<Options> lists = optionsMapper.findAll();
        Map<String, Options> rets = new HashMap<String ,Options>();
        for(Options options : lists) {
            rets.put(options.getKey(), options);
        }
        return rets;
    }

    @Override
    public void update(List<Options> options) {
        if (options == null) {
            return;
        }

        for (Options opt :  options) {
            Options entity = optionsMapper.findByKey(opt.getKey());

            // 修改
            if (entity != null) {
                entity.setValue(opt.getValue());
                optionsMapper.updateByPrimaryKeySelective(entity);
            }
            // 添加
            else {
                entity = new Options();
                BeanUtils.copyProperties(opt, entity);
                optionsMapper.insert(entity);
            }
        }
    }

    @Override
    public String findConfigValueByName(String key) {
        return null;
    }

    @Override
    public void initSettings(Resource resource) {

    }
}
