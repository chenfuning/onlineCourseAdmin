package com.ning.service;

import com.ning.pojo.Comment;
import com.ning.result.Results;

public interface CommentService {
    Results<Comment> getAllCommentsByPage(Integer offset, Integer limit);

    void deletecommentBycommentid(String commentid);
}
