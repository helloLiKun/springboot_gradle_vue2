package example.user.mapping;

import example.base.BaseMapping;

/**
 * Created by Administrator on 2019/1/13 0013.
 */
public interface UserMapping extends BaseMapping{
    String SYS=BASE+"/sys";
    String LOGIN=SYS+"/login";
    String LOGIN_SUBMIT=SYS+"/login-submit";

}
