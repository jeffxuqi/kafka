package com.springlearning.service;

import com.springlearning.dao.LoginLogDao;
import com.springlearning.dao.UserDao;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Transactional
@Service("userService")
public class UserService
{
    //直接通过在属性上进行配置注入，就可以不用写set方法了
    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name="loginlogDao")
    private LoginLogDao loginLogDao;

    /**
     * 判断用户名与密码是否正确
     * @param useranme
     * @param password
     * @return
     */
    public boolean hasMatchUser(String useranme,String password)
    {
        int matchcount=userDao.getMatchCount(useranme,password);
        return matchcount>0;
    }
}
