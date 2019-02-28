package com.eussi.blog.modules.service;

/**
 * @author wangxm on 2019/2/28.
 */
public interface SecurityCodeService {
    /**
     * 生成验证码
     * @param userId
     * @param target : email mobile
     * @return
     */
    String generateCode(long userId, int type, String target);

    /**
     * 检验验证码有效性
     * @param userId
     * @param code
     * @return token
     */
    boolean verify(long userId, int type, String code);
}
