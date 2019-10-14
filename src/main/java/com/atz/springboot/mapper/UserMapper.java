package com.atz.springboot.mapper;


import com.atz.springboot.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where token = #{token}" )
    User findByToken(@Param("token") String token);
    @Select("select * from user where id = #{id}" )
    User findByID(@Param("id") int id);
    @Select("select * from user where account_id = #{accountId}" )
    User findByAccountId(String accountId);
    @Update("update user set name = #{name}, token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id = #{id}")
    User updateUser(User user);
}
