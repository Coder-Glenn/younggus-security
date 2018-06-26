package cn.younggus.security.core.social.qq.connect;

import cn.younggus.security.core.social.qq.api.domain.QQUser;
import cn.younggus.security.core.social.qq.api.service.QQUserService;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author Glenn.Zheng
 * @date 2018/6/26 21:32
 */
public class QQApiAdapter implements ApiAdapter<QQUserService> {

    @Override
    public boolean test(QQUserService qqUserService) {
        return true;
    }

    @Override
    public void setConnectionValues(QQUserService qqUserService, ConnectionValues connectionValues) {
        QQUser qqUser = qqUserService.getUserInfo();

        connectionValues.setDisplayName(qqUser.getNickname());
        connectionValues.setImageUrl(qqUser.getFigureurl_qq_1());
        connectionValues.setProfileUrl("");
        //openId是服务商对用户的唯一标识
        connectionValues.setProviderUserId(qqUser.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQUserService qqUserService) {
        //绑定解绑
        return null;
    }

    @Override
    public void updateStatus(QQUserService qqUserService, String s) {
        //同步用户的状态（例如：用户新发表了微博）
    }
}
