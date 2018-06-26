package cn.younggus.security.core.social.qq.api.service;

import cn.younggus.security.core.social.qq.api.domain.QQUser;

/**
 * @author Glenn.Zheng
 * @date 2018/6/21 23:30
 * 用户获取第三方的用户信息
 */
public interface QQUserService {

    QQUser getUserInfo();
}
