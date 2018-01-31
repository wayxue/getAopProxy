package com.yitaqi.dao;

import com.yitaqi.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @author xue
 */
@Mapper
public interface UserMapper {

    /**
     * 根据年龄查询用户
     * @param age
     * @return 用户集合
     */
    @Select("select * from t_user where age = #{age}")
    public List<User> getUser(Integer age);

    /**
     * 保存用户
     * @param name
     * @param age
     */
    @Insert("insert into t_user (name, age) values (#{name},#{age})")
    public void save(@Param("name") String name, @Param("age") Integer age);
}
