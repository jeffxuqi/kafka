package com.springlearning.dao;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

@Repository("userDao")
public class UserDao
{
    private static Logger logger = Logger.getLogger(UserDao.class);
    //如果直接使用注解进行属性的修饰，可以不用set函数
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取匹配用户的个数
     * @param username
     * @param password
     * @return
     */
    public int getMatchCount(String username, String password)
    {
        String querysql = "select count(*) from t_user where user_name=? and password=?";

        int rows = jdbcTemplate.queryForObject(querysql, Integer.class, username, password);

        logger.info("rows=" + rows);
        return rows;
    }
}