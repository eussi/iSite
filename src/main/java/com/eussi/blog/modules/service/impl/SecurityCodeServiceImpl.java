package com.eussi.blog.modules.service.impl;

import com.eussi.blog.modules.service.SecurityCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangxueming on 2019/2/28.
 */
@Service
@Transactional(readOnly = true)
public class SecurityCodeServiceImpl implements SecurityCodeService {
    @Override
    public String generateCode(long userId, int type, String target) {
        return "";
    }

    //暂时不做实现
    @Override
    public boolean verify(long userId, int type, String code) {
        return true;
    }
}
