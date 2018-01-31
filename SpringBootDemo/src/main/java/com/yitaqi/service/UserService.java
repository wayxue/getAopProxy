package com.yitaqi.service;

import com.yitaqi.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xue
 */
public interface UserService {

    /**
     * 根据年龄获取用户
     * @param age
     * @return
     */
    public List<User> getUser(Integer age);

    /**
     * 保存用户
     * @param name
     * @param age
     */
    public void save(String name, Integer age);

    /**
     * 内部方法
     */
    public void child();

    /**
     * 外部方法
     */
    public void father();

    /**
     * 外部方法2
     */
    public void father2();

    /**
     * 外部方法3
     */
    public void father3();
}
