package com.atz.springboot.mapper;

import com.atz.springboot.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question order by id desc")
    List<Question> list();
    @Select("select * from question where creator = #{creator}")
    List<Question> listByCreator(int creator);
    @Select("select * from question where id = #{id}")
    Question listById(Integer id);
}
