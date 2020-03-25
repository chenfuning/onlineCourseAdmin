package com.ning.mapper;

import com.ning.pojo.Comment;
import com.ning.utils.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends MyMapper<Comment> {
    @Select("select count(*) from comment t")
    Long countAllComments();
    @Select("select * from comment t order by t.commentid limit #{startPosition},#{limit}")
    List<Comment> getAllCommentrByPage(Integer startPosition, Integer limit);
}