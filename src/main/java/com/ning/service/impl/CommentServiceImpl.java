package com.ning.service.impl;

import com.ning.mapper.CommentMapper;
import com.ning.pojo.Comment;
import com.ning.result.Results;
import com.ning.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Results<Comment> getAllCommentsByPage(Integer offset, Integer limit) {
        return Results.success(commentMapper.countAllComments().intValue(),commentMapper.getAllCommentrByPage(offset,limit));
    }

    @Override
    public void deletecommentBycommentid(String commentid) {
        commentMapper.deleteByPrimaryKey(commentid);
    }
}
